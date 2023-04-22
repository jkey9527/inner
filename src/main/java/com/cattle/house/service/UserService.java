package com.cattle.house.service;

import com.cattle.house.bean.UserBean;

/**
 * 用户服务类
 *
 * @author niujie
 * @date 2023/4/21 22:38
 */
public interface UserService {

    /**
     * 用户登录
     * @param userBean userBean
     * @return com.cattle.house.bean.UserBean
     * @author niujie
     * @date 2023/4/22
     */
    UserBean loginIn(UserBean userBean) throws Exception;

    /**
     * 保存用户
     * @param userBean userBean
     * @return void
     * @author niujie
     * @date 2023/4/22
     */
    void saveUser(UserBean userBean) throws Exception;

    /**
     * 删除用户
     * @param userBean userBean
     * @return void
     * @author niujie
     * @date 2023/4/22
     */
    void deleteUser(UserBean userBean) throws Exception;

    /**
     * 通过用户ID查询用户信息
     * @param userId userId
     * @return com.cattle.house.bean.UserBean
     * @author niujie
     * @date 2023/4/22
     */
    UserBean getUserByUserId(String userId) throws Exception;

    /**
     * 修改用户信息
     * @param userBean userBean
     * @return void
     * @author niujie
     * @date 2023/4/22
     */
    void updateUser(UserBean userBean) throws Exception;
}
