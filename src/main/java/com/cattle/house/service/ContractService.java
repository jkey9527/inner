package com.cattle.house.service;

import com.cattle.house.bean.ContractBean;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 合同服务接口
 *
 * @author niujie
 * @date 2023/4/21 22:38
 */
public interface ContractService {

    /**
     * 保存合同
     * @param contractBean contractBean
     * @return void
     * @author niujie
     * @date 2023/4/22
     */
    void saveContract(ContractBean contractBean) throws Exception;

    /**
     * 删除合同
     * @param contractBean contractBean
     * @return void
     * @author niujie
     * @date 2023/4/22
     */
    void deleteContract(ContractBean contractBean) throws Exception;

    /**
     * 通过合同ID查询合同信息
     * @param contractId contractId
     * @return com.cattle.house.bean.ContractBean
     * @author niujie
     * @date 2023/4/22
     */
    ContractBean getContractByContractId(String contractId) throws Exception;

    /**
     * 修改合同信息
     * @param contractBean contractBean
     * @return void
     * @author niujie
     * @date 2023/4/22
     */
    void updateContract(ContractBean contractBean) throws Exception;

    /**
     * 查询合同列表
     * @param contractBean contract
     * @return java.util.List<com.cattle.house.bean.ContractBean>
     * @author niujie
     * @date 2023/4/22
     */
    List<ContractBean> getContractList(ContractBean contractBean) throws Exception;

    /**
     * 分页查询合同列表
     *
     * @param contractBean contractBean
     * @return com.github.pagehelper.PageInfo<com.cattle.house.bean.ContractBean>
     * @author niujie
     * @date 2023/4/30
     */
    PageInfo<ContractBean> getContractList4Page(ContractBean contractBean) throws Exception;

    /**
     * 查询所有合同下拉信息
     *
     * @return java.util.List<com.cattle.house.bean.ContractBean>
     * @author niujie
     * @date 2023/5/14
     */
    List<ContractBean> getContractOptions() throws Exception;

    /**
     * 修改合同状态
     *
     * @param contractBean contractBean
     * @return void
     * @author niujie
     * @date 2023/5/27
     */
    void updateContractState(ContractBean contractBean) throws Exception;
}
