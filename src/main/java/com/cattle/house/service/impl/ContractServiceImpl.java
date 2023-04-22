package com.cattle.house.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.cattle.house.bean.ContractBean;
import com.cattle.house.mapper.ContractMapper;
import com.cattle.house.service.ContractService;
import com.cattle.house.util.UuIdUtil;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 合同服务类
 *
 * @author niujie
 * @date 2023/4/21 22:40
 */
@Service
@AllArgsConstructor
public class ContractServiceImpl implements ContractService {
    private static final Logger LOGGER = LogManager.getLogger(ContractServiceImpl.class);

    private ContractMapper contractMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveContract(ContractBean contractBean) throws Exception {
        try {
            contractBean.setCon_id(UuIdUtil.getUUID());
            String conNo = Convert.toStr(contractBean.getCon_house_no()) + "@" + Convert.toStr(DateUtil.current());
            contractBean.setCon_no(conNo);
            contractMapper.saveContract(contractBean);
        }catch (Exception e){
            LOGGER.error(e);
            throw new Exception(e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteContract(ContractBean contractBean) throws Exception {
        try {
            String conId = contractBean.getCon_id();
            if (StrUtil.isBlank(conId)) {
                throw new Exception("参数{conId}异常");
            }
            ContractBean user = getContractByContractId(conId);
            if(ObjectUtil.isNull(user)){
                throw new Exception("删除失败，未查询到合同信息");
            }
            contractMapper.deleteContract(contractBean);
        }catch (Exception e){
            LOGGER.error(e);
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public ContractBean getContractByContractId(String conId) throws Exception {
        try {
            if (StrUtil.isBlank(conId)) {
                throw new Exception("参数{conId}异常");
            }
            return contractMapper.getContractByContractId(conId);
        }catch (Exception e){
            LOGGER.error(e);
            throw new Exception(e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateContract(ContractBean contractBean) throws Exception {
        try {
            String conId = contractBean.getCon_id();
            if (StrUtil.isBlank(conId)) {
                throw new Exception("参数{conId}异常");
            }
            ContractBean contract = getContractByContractId(conId);
            if(ObjectUtil.isNull(contract)){
                throw new Exception("修改失败，未查询到合同信息");
            }
            contractMapper.updateContract(contractBean);
        }catch (Exception e){
            LOGGER.error(e);
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<ContractBean> getContractList(ContractBean contract) throws Exception {
        try {
            return contractMapper.getContractList(contract);
        }catch (Exception e){
            LOGGER.error(e);
            throw new Exception(e.getMessage());
        }
    }
}
