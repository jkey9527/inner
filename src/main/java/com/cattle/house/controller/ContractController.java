package com.cattle.house.controller;

import com.cattle.house.bean.ContractBean;
import com.cattle.house.response.Result;
import com.cattle.house.service.ContractService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 合同
 *
 * @author niujie
 * @date 2023/4/21 22:43
 */
@RestController
@AllArgsConstructor
@RequestMapping(value = "cattle/house/contract", method = RequestMethod.POST)
@CrossOrigin(origins = "*")
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
    @RequestMapping("/saveContract")
    public String saveContract(@RequestBody ContractBean contract) {
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
    @RequestMapping("/updateContract")
    public String updateContract(@RequestBody ContractBean contract) {
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
    @RequestMapping("/deleteContract")
    public String deleteContract(@RequestBody ContractBean contract) {
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
    @RequestMapping("/getContractList")
    public String getContractList(@RequestBody ContractBean contract) {
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
    @RequestMapping("/getContractByContractId")
    public String getContractByContractId(@RequestBody ContractBean contract) {
        try {
            ContractBean contractBean = contractService.getContractByContractId(contract.getCon_id());
            return Result.success("操作成功！",contractBean);
        } catch (Exception e) {
            LOGGER.error("操作异常！", e);
            return Result.fail(e.getMessage());
        }
    }

}
