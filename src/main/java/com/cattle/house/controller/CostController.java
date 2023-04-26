package com.cattle.house.controller;

import com.cattle.house.bean.CostBean;
import com.cattle.house.bean.UserBean;
import com.cattle.house.response.Result;
import com.cattle.house.service.CostService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 费用
 *
 * @author niujie
 * @date 2023/4/21 22:43
 */
@RestController
@AllArgsConstructor
@RequestMapping(value = "cattle/house/cost", method = RequestMethod.POST)
@CrossOrigin(origins = "*")
public class CostController {
    private static final Logger LOGGER = LogManager.getLogger(CostController.class);

    private CostService costService;

    /**
     * 查询所有的费用列表
     * @param cost cost
     * @return java.lang.String
     * @author niujie
     * @date 2023/4/22
     */
    @RequestMapping("/getAllCostList")
    public String getAllCostList(@RequestBody CostBean cost) {
        try {
            List<CostBean> costBeanList = costService.getAllCostList(cost);
            return Result.success("操作成功！", costBeanList);
        } catch (Exception e) {
            LOGGER.error("操作异常！", e);
            return Result.fail(e.getMessage());
        }
    }

    /**
     * 保存费用
     * @param cost cost
     * @return java.lang.String
     * @author niujie
     * @date 2023/4/22
     */
    @RequestMapping("/saveCost")
    public String saveCost(@RequestBody CostBean cost) {
        try {
            costService.saveCost(cost);
            return Result.success("操作成功！");
        } catch (Exception e) {
            LOGGER.error("操作异常！", e);
            return Result.fail(e.getMessage());
        }
    }

    /**
     * 计算费用
     * @param cost cost
     * @return java.lang.String
     * @author niujie
     * @date 2023/4/22
     */
    @RequestMapping("/calculateCost")
    public String calculateCost(@RequestBody CostBean cost) {
        try {
            CostBean costBean = costService.calculateCost(cost);
            return Result.success("操作成功！", costBean);
        } catch (Exception e) {
            LOGGER.error("操作异常！", e);
            return Result.fail(e.getMessage());
        }
    }

    /**
     * 初始化费用信息
     *
     * @param user user
     * @return java.lang.String
     * @author niujie
     * @date 2023/4/24
     */
    @RequestMapping("/initCost")
    public String initCost(@RequestBody UserBean user) {
        try {
            CostBean costBean = costService.initCost(user);
            return Result.success("操作成功！", costBean);
        } catch (Exception e) {
            LOGGER.error("操作异常！", e);
            return Result.fail(e.getMessage());
        }
    }

}
