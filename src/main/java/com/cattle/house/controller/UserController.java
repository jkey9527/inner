package com.cattle.house.controller;

import cn.hutool.core.util.ObjectUtil;
import com.cattle.house.bean.UserBean;
import com.cattle.house.response.Result;
import com.cattle.house.service.UserService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户
 *
 * @author niujie
 * @date 2023/4/21 22:43
 */
@Controller
@AllArgsConstructor
@RequestMapping(value = "/cattle/house/user", method = RequestMethod.POST)
public class UserController {
    private static final Logger LOGGER = LogManager.getLogger(UserController.class);

    private UserService userService;

    /**
     * 用户登录
     *
     * @param user user
     * @return java.lang.String
     * @author niujie
     * @date 2023/4/22
     */
    @ResponseBody
    @RequestMapping("/login")
    public String login(UserBean user) {
        try {
            UserBean userBean = userService.loginIn(user);
            if (ObjectUtil.isNull(userBean)) {
                return Result.fail("操作失败！");
            }
            return Result.success("操作成功！", userBean);
        } catch (Exception e) {
            LOGGER.error("操作异常！", e);
            return Result.fail(e.getMessage());
        }
    }

    /**
     * 保存用户
     * @param user user
     * @return java.lang.String
     * @author niujie
     * @date 2023/4/22
     */
    @ResponseBody
    @RequestMapping("/saveUser")
    public String saveUser(UserBean user) {
        try {
            userService.saveUser(user);
            return Result.success("操作成功！");
        } catch (Exception e) {
            LOGGER.error("操作异常！", e);
            return Result.fail(e.getMessage());
        }
    }

    /**
     * 修改用户信息
     * @param user user
     * @return java.lang.String
     * @author niujie
     * @date 2023/4/22
     */
    @ResponseBody
    @RequestMapping("/updateUser")
    public String updateUser(UserBean user) {
        try {
            userService.updateUser(user);
            return Result.success("操作成功！");
        } catch (Exception e) {
            LOGGER.error("操作异常！", e);
            return Result.fail(e.getMessage());
        }
    }

    /**
     * 删除用户
     * @param user user
     * @return java.lang.String
     * @author niujie
     * @date 2023/4/22
     */
    @ResponseBody
    @RequestMapping("/deleteUser")
    public String deleteUser(UserBean user) {
        try {
            userService.deleteUser(user);
            return Result.success("操作成功！");
        } catch (Exception e) {
            LOGGER.error("操作异常！", e);
            return Result.fail(e.getMessage());
        }
    }

}
