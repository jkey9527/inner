package com.cattle.house.controller;

import cn.hutool.json.JSONUtil;
import com.cattle.house.bean.UserBean;
import com.cattle.house.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * TODO
 *
 * @author niujie
 * @date 2023/4/21 22:43
 */
@Controller
@AllArgsConstructor
@RequestMapping("/cattle/house/user")
public class UserController {

    private UserService userService;
    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(String name,String password){
        UserBean userBean = userService.loginIn(name, password);
        return JSONUtil.toJsonStr(userBean);
    }
}
