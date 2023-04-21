package com.cattle.house.mapper;

import com.cattle.house.bean.UserBean;
import org.apache.ibatis.annotations.Mapper;

/**
 * TODO
 *
 * @author niujie
 * @date 2023/4/21 22:35
 */
@Mapper
public interface UserMapper {
    UserBean getUserBean(String name,String password);
}
