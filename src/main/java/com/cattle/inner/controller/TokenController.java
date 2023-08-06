package com.cattle.inner.controller;

import com.cattle.inner.bean.TokenBean;
import com.cattle.inner.response.Result;
import com.cattle.inner.service.TokenService;
import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

/**
 * toekn
 *
 * @author niujie
 * @date 2023/4/21 22:43
 */
@RestController
@AllArgsConstructor
@RequestMapping(value = "cattle/inner/token", method = RequestMethod.POST)
@CrossOrigin(origins = "*")
public class TokenController {
    private static final Logger LOGGER = Logger.getLogger(TokenController.class);

    private TokenService tokenService;

    /**
     * 获得token
     *
     * @param tokenBean tokenBean
     * @return java.lang.String
     * @author niujie
     * @date 2023/4/29
     */
    @RequestMapping("/getToken")
    public String getToken(@RequestBody TokenBean tokenBean) {
        try {
            String token = tokenService.getToken(tokenBean);
            return Result.success("操作成功！", token);
        } catch (Exception e) {
            LOGGER.error("操作异常！", e);
            return Result.fail(e.getMessage());
        }
    }


}
