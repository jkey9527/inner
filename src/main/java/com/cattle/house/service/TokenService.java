package com.cattle.house.service;

import com.cattle.house.bean.TokenBean;

/**
 * token服务接口
 *
 * @author niujie
 * @date 2023/4/29 11:13
 */
public interface TokenService {

    /**
     * 获取token
     *
     * @return java.lang.String
     * @author niujie
     * @date 2023/4/29
     */
    String getToken(TokenBean tokenBean) throws Exception;

    /**
     * 验证token
     *
     * @param token
     * @return
     */
    String checkToken(String token) throws Exception;
}
