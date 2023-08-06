package com.cattle.inner.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.cattle.inner.bean.UserBean;
import com.cattle.inner.constant.RedisConstant;
import com.cattle.inner.enums.LogModelEnum;
import com.cattle.inner.enums.LogTypeEnum;
import com.cattle.inner.interceptor.UserContext;
import com.cattle.inner.mapper.UserMapper;
import com.cattle.inner.service.SystemService;
import com.cattle.inner.service.UserService;
import com.cattle.inner.util.RedisUtil;
import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * 用户服务类
 *
 * @author niujie
 * @date 2023/4/21 22:40
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

    private UserMapper userMapper;

    private RedisUtil redisUtil;

    private SystemService systemService;

    @Override
    public UserBean loginIn(UserBean userBean) throws Exception {
        String userNo = userBean.getUser_no();
        String userPhone = userBean.getUser_phone();
        String userPassword = userBean.getUser_password();
        if ((StrUtil.isBlank(userNo) && StrUtil.isBlank(userPhone)) || StrUtil.isBlank(userPassword)) {
            throw new Exception("用户名或密码不能为空！");
        }
        UserBean user = userMapper.getUserBean(userBean);
        if (ObjectUtil.isNull(user)) {
            return null;
        }
        //设置登录过期时间
        redisUtil.set(user.getUser_id(), user.getUser_name(), RedisConstant.LOGIN_TIME_OUT);
        String userId = UserContext.getUserId();
        systemService.saveOptLog(LogModelEnum.user.getValue(), LogTypeEnum.login_in.getValue());
        return user;
    }


    @Override
    public void loginOut(UserBean user) {
        if (ObjectUtil.isNull(user.getUser_id())) {
            return;
        }
        boolean hasKey = redisUtil.hasKey(user.getUser_id());
        if (hasKey) {
            redisUtil.deleteKey(user.getUser_id());
        }
        String userId = UserContext.getUserId();
        systemService.saveOptLog(LogModelEnum.user.getValue(), LogTypeEnum.login_out.getValue());
    }
}
