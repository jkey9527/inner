package com.cattle.house.service;

import com.cattle.house.bean.UserBean;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 用户服务接口
 *
 * @author niujie
 * @date 2023/4/21 22:38
 */
public interface UserService {

    /**
     * 用户登录
     *
     * @param userBean userBean
     * @return com.cattle.house.bean.UserBean
     * @author niujie
     * @date 2023/4/22
     */
    UserBean loginIn(UserBean userBean) throws Exception;

    /**
     * 注销登录
     *
     * @param user user
     * @return void
     * @author niujie
     * @date 2023/5/14
     */
    void loginOut(UserBean user);

    /**
     * 保存用户
     *
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

    /**
     * 查询用户列表
     *
     * @param user user
     * @return java.util.List<com.cattle.house.bean.UserBean>
     * @author niujie
     * @date 2023/4/22
     */
    List<UserBean> getUserList(UserBean user) throws Exception;

    /**
     * 分页查询
     *
     * @param user user
     * @return com.github.pagehelper.PageInfo<com.cattle.house.bean.UserBean>
     * @author niujie
     * @date 2023/4/30
     */
    PageInfo<UserBean> getUserList4Page(UserBean user) throws Exception;

    /**
     * 修改用户状态
     *
     * @param user user
     * @return void
     * @author niujie
     * @date 2023/5/27
     */
    void updateUserState(UserBean user) throws Exception;

    /**
     * 查询用户列表
     *
     * @return java.util.List<com.cattle.house.bean.UserBean>
     * @author niujie
     * @date 2023/5/27
     */
    List<UserBean> getUserOptions() throws Exception;
}
