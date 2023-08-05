package com.cattle.inner.controller;

import com.cattle.inner.bean.SystemBean;
import com.cattle.inner.response.Result;
import com.cattle.inner.service.SystemService;
import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统参数
 *
 * @author niujie
 * @date 2023/4/21 22:43
 */
@RestController
@AllArgsConstructor
@RequestMapping(value = "cattle/house/system", method = RequestMethod.POST)
@CrossOrigin(origins = "*")
public class SystemController {
    private static final Logger LOGGER = Logger.getLogger(SystemController.class);

    private SystemService systemService;

    /**
     * 查询系统参数
     * @return java.lang.String
     * @author niujie
     * @date 2023/6/1
     */
    @RequestMapping("/initSystem")
    public String initSystem() {
        try {
            List<SystemBean> systemList = systemService.getSystem();
            return Result.success("操作成功！", systemList);
        } catch (Exception e) {
            LOGGER.error("操作异常！", e);
            return Result.fail(e.getMessage());
        }
    }

    /**
     * 保存系统参数
     * @param systemList systemList
     * @return java.lang.String
     * @author niujie
     * @date 2023/6/1
     */
    @RequestMapping("/saveSystem")
    public String saveSystem(@RequestBody List<SystemBean> systemList) {
        try {
            systemService.saveSystem(systemList);
            return Result.success("操作成功！");
        } catch (Exception e) {
            LOGGER.error("操作异常！", e);
            return Result.fail(e.getMessage());
        }
    }

}
