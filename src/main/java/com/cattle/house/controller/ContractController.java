package com.cattle.house.controller;

import com.cattle.house.bean.ContractBean;
import com.cattle.house.response.Result;
import com.cattle.house.service.ContractService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 合同
 *
 * @author niujie
 * @date 2023/4/21 22:43
 */
@Controller
@AllArgsConstructor
@RequestMapping(value = "/cattle/house/contract", method = RequestMethod.POST)
public class ContractController {
    private static final Logger LOGGER = LogManager.getLogger(ContractController.class);

    private ContractService contractService;

    /**
     * 保存合同
     * @param contract contract
     * @return java.lang.String
     * @author niujie
     * @date 2023/4/22
     */
    @ResponseBody
    @RequestMapping("/saveContract")
    public String saveContract(ContractBean contract) {
        try {
            contractService.saveContract(contract);
            return Result.success("操作成功！");
        } catch (Exception e) {
            LOGGER.error("操作异常！", e);
            return Result.fail(e.getMessage());
        }
    }

    /**
     * 修改合同
     * @param contract contract
     * @return java.lang.String
     * @author niujie
     * @date 2023/4/22
     */
    @ResponseBody
    @RequestMapping("/updateContract")
    public String updateContract(ContractBean contract) {
        try {
            contractService.updateContract(contract);
            return Result.success("操作成功！");
        } catch (Exception e) {
            LOGGER.error("操作异常！", e);
            return Result.fail(e.getMessage());
        }
    }

    /**
     * 删除合同
     * @param contract contract
     * @return java.lang.String
     * @author niujie
     * @date 2023/4/22
     */
    @ResponseBody
    @RequestMapping("/deleteContract")
    public String deleteContract(ContractBean contract) {
        try {
            contractService.deleteContract(contract);
            return Result.success("操作成功！");
        } catch (Exception e) {
            LOGGER.error("操作异常！", e);
            return Result.fail(e.getMessage());
        }
    }

    /**
     * 查询合同列表
     * @param contract contract
     * @return java.lang.String
     * @author niujie
     * @date 2023/4/22
     */
    @ResponseBody
    @RequestMapping("/getContractList")
    public String getContractList(ContractBean contract) {
        try {
            List<ContractBean> contractBeanList = contractService.getContractList(contract);
            return Result.success("操作成功！",contractBeanList);
        } catch (Exception e) {
            LOGGER.error("操作异常！", e);
            return Result.fail(e.getMessage());
        }
    }

    /**
     * 根据合同ID查询合同信息
     * @param contract contract
     * @return java.lang.String
     * @author niujie
     * @date 2023/4/22
     */
    @ResponseBody
    @RequestMapping("/getContractByContractId")
    public String getContractByContractId(ContractBean contract) {
        try {
            ContractBean contractBean = contractService.getContractByContractId(contract.getCon_id());
            return Result.success("操作成功！",contractBean);
        } catch (Exception e) {
            LOGGER.error("操作异常！", e);
            return Result.fail(e.getMessage());
        }
    }

}
