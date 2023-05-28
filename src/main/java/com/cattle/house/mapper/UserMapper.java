package com.cattle.house.mapper;

import com.cattle.house.bean.UserBean;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户映射类
 *
 * @author niujie
 * @date 2023/4/21 22:35
 */
@Mapper
public interface UserMapper {

    /**
     * 用户登录
     * @param userBean userBean
     * @return com.cattle.house.bean.UserBean
     * @author niujie
     * @date 2023/4/22
     */
    UserBean getUserBean(UserBean userBean);

    /**
     * 保存用户
     * @param userBean userBean
     * @return void
     * @author niujie
     * @date 2023/4/22
     */
    void saveUser(UserBean userBean);

    /**
     * 删除用户
     * @param userBean userBean
     * @return void
     * @author niujie
     * @date 2023/4/22
     */
    void deleteUser(UserBean userBean);

    /**
     * 通过用户ID查询用户信息
     * @param userId userId
     * @return com.cattle.house.bean.UserBean
     * @author niujie
     * @date 2023/4/22
     */
    UserBean getUserByUserId(String userId);

    /**
     * 修改用户信息
     * @param userBean userBean
     * @return void
     * @author niujie
     * @date 2023/4/22
     */
    void updateUser(UserBean userBean);

    /**
     * 查询用户列表信息
     *
     * @param user user
     * @return java.util.List<com.cattle.house.bean.UserBean>
     * @author niujie
     * @date 2023/4/22
     */
    List<UserBean> getUserList(UserBean user);

    /**
     * 修改用户状态
     *
     * @param userBean userBean
     * @return void
     * @author niujie
     * @date 2023/5/27
     */
    void updateUserState(UserBean userBean);

    /**
     * 查询用户列表信息
     *
     * @return java.util.List<com.cattle.house.bean.UserBean>
     * @author niujie
     * @date 2023/5/27
     */
    List<UserBean> getUserOptions();
}
