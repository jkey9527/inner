package com.cattle.house.service;

import com.cattle.house.bean.UserBean;

/**
 * TODO
 *
 * @author niujie
 * @date 2023/4/21 22:38
 */
public interface UserService {

    UserBean loginIn(String name, String password);
}
