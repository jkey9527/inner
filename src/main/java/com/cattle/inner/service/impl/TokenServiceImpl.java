package com.cattle.inner.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.cattle.inner.bean.TokenBean;
import com.cattle.inner.constant.TokenConstant;
import com.cattle.inner.service.TokenService;
import com.cattle.inner.util.RedisUtil;
import com.cattle.inner.util.UuIdUtil;
import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * token服务类
 *
 * @author niujie
 * @date 2023/4/21 22:40
 */
@Service
@AllArgsConstructor
public class TokenServiceImpl implements TokenService {
    private static final Logger LOGGER = Logger.getLogger(TokenServiceImpl.class);

    private RedisUtil redisUtil;

    @Override
    public String getToken(TokenBean tokenBean) throws Exception {
        try {
            String privateKey = tokenBean.getPrivateKey();
            if (!TokenConstant.PRIVATE_KEYS.contains(privateKey)) {
                throw new Exception("秘钥错误！");
            }
            String token = Convert.toStr(redisUtil.get(privateKey));
            if (StrUtil.isBlank(token)) {
                token = UuIdUtil.getUUID();
            }
            redisUtil.set(privateKey, token, TokenConstant.TOKEN_TIME_OUT);
            redisUtil.set(token, token, TokenConstant.TOKEN_TIME_OUT);
            return token;
        } catch (Exception e) {
            LOGGER.error(e);
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public String checkToken(String token) throws Exception {
        try {
            if (StrUtil.isBlank(token)) {
                return "接口请求错误：token检查不通过！";
            }
            token = Convert.toStr(redisUtil.get(token));
            if (StrUtil.isBlank(token)) {
                return "token已过时，请重新登录！";
            }
            return "";
        } catch (Exception e) {
            LOGGER.error(e);
            throw new Exception(e.getMessage());
        }
    }
}
