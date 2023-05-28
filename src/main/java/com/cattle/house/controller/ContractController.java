package com.cattle.house.controller;

import com.cattle.house.bean.ContractBean;
import com.cattle.house.response.Result;
import com.cattle.house.service.ContractService;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
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
    private static final Logger LOGGER = Logger.getLogger(ContractController.class);

    private ContractService contractService;

    /**
     * 保存合同
     * @param contractBean contract
     * @return java.lang.String
     * @author niujie
     * @date 2023/4/22
     */
    @RequestMapping("/saveContract")
    public String saveContract(@RequestBody ContractBean contractBean) {
        try {
            contractService.saveContract(contractBean);
            return Result.success("操作成功！");
        } catch (Exception e) {
            LOGGER.error("操作异常！", e);
            return Result.fail(e.getMessage());
        }
    }

    /**
     * 修改合同
     * @param contractBean contract
     * @return java.lang.String
     * @author niujie
     * @date 2023/4/22
     */
    @RequestMapping("/updateContract")
    public String updateContract(@RequestBody ContractBean contractBean) {
        try {
            contractService.updateContract(contractBean);
            return Result.success("操作成功！");
        } catch (Exception e) {
            LOGGER.error("操作异常！", e);
            return Result.fail(e.getMessage());
        }
    }

    /**
     * 删除合同
     * @param contractBean contract
     * @return java.lang.String
     * @author niujie
     * @date 2023/4/22
     */
    @RequestMapping("/deleteContract")
    public String deleteContract(@RequestBody ContractBean contractBean) {
        try {
            contractService.deleteContract(contractBean);
            return Result.success("操作成功！");
        } catch (Exception e) {
            LOGGER.error("操作异常！", e);
            return Result.fail(e.getMessage());
        }
    }

    /**
     * 分页查询合同列表
     * @param contractBean contract
     * @return java.lang.String
     * @author niujie
     * @date 2023/4/22
     */
    @RequestMapping("/getContractList4Page")
    public String getContractList4Page(@RequestBody ContractBean contractBean) {
        try {
            PageInfo<ContractBean> pageInfo = contractService.getContractList4Page(contractBean);
            return Result.success("操作成功！", pageInfo);
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
            return Result.success("操作成功！", contractBean);
        } catch (Exception e) {
            LOGGER.error("操作异常！", e);
            return Result.fail(e.getMessage());
        }
    }

    /**
     * 查询所有合同下拉信息
     *
     * @return java.lang.String
     * @author niujie
     * @date 2023/5/14
     */
    @RequestMapping("/getContractOptions")
    public String getContractOptions() {
        try {
            List<ContractBean> contractBeans = contractService.getContractOptions();
            return Result.success("操作成功！", contractBeans);
        } catch (Exception e) {
            LOGGER.error("操作异常！", e);
            return Result.fail(e.getMessage());
        }
    }

    /**
     * 修改合同状态
     *
     * @param contractBean contractBean
     * @return java.lang.String
     * @author niujie
     * @date 2023/5/27
     */
    @RequestMapping("/updateContractState")
    public String updateContractState(@RequestBody ContractBean contractBean) {
        try {
            contractService.updateContractState(contractBean);
            return Result.success("操作成功！");
        } catch (Exception e) {
            LOGGER.error("操作异常！", e);
            return Result.fail(e.getMessage());
        }
    }

}
