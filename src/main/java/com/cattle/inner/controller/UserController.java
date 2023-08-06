package com.cattle.inner.controller;

import cn.hutool.core.util.ObjectUtil;
import com.cattle.inner.bean.UserBean;
import com.cattle.inner.response.Result;
import com.cattle.inner.service.UserService;
import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

/**
 * 用户
 *
 * @author niujie
 * @date 2023/4/21 22:43
 */
@RestController
@AllArgsConstructor
@RequestMapping(value = "cattle/inner/user", method = RequestMethod.POST)
@CrossOrigin(origins = "*")
public class UserController {
    private static final Logger LOGGER = Logger.getLogger(UserController.class);

    private UserService userService;

    /**
     * 用户登录
     *
     * @param user user
     * @return java.lang.String
     * @author niujie
     * @date 2023/4/22
     */
    @RequestMapping("/login")
    public String login(@RequestBody UserBean user) {
        try {
            UserBean userBean = userService.loginIn(user);
            if (ObjectUtil.isNull(userBean)) {
                return Result.fail("用户名或密码错误！");
            }
            return Result.success("操作成功！", userBean);
        } catch (Exception e) {
            LOGGER.error("操作异常！", e);
            return Result.fail(e.getMessage());
        }
    }

    /**
     * 用户注销登录
     *
     * @param user user
     * @return java.lang.String
     * @author niujie
     * @date 2023/5/14
     */
    @RequestMapping("/loginOut")
    public String loginOut(@RequestBody UserBean user) {
        try {
            userService.loginOut(user);
            return Result.success("操作成功！");
        } catch (Exception e) {
            LOGGER.error("操作异常！", e);
            return Result.fail(e.getMessage());
        }
    }

}
