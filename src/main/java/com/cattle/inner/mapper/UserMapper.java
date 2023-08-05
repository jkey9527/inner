package com.cattle.inner.mapper;

import com.cattle.inner.bean.UserBean;
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
     * @return com.cattle.inner.bean.UserBean
     * @author niujie
     * @date 2023/4/22
     */
    UserBean getUserBean(UserBean userBean);
}
