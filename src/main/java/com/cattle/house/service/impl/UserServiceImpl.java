package com.cattle.house.service.impl;

import com.cattle.house.mapper.UserMapper;
import com.cattle.house.bean.UserBean;
import com.cattle.house.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author niujie
 * @date 2023/4/21 22:40
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserMapper userMapper;
    @Override
    public UserBean loginIn(String name, String password) {
        return userMapper.getUserBean(name,password);
    }
}
