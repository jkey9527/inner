package com.cattle.house.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.cattle.house.bean.ContractBean;
import com.cattle.house.bean.CostBean;
import com.cattle.house.bean.PageBean;
import com.cattle.house.enums.ContractStateEnum;
import com.cattle.house.mapper.ContractMapper;
import com.cattle.house.service.ContractService;
import com.cattle.house.service.CostService;
import com.cattle.house.util.PageUtil;
import com.cattle.house.util.UuIdUtil;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
    private static final Logger LOGGER = Logger.getLogger(ContractServiceImpl.class);

    private ContractMapper contractMapper;
    private CostService costService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveContract(ContractBean contractBean) throws Exception {
        try {
            contractBean.setCon_id(UuIdUtil.getUUID());
            String contractNo = contractBean.getCon_no();
            ContractBean contract = contractMapper.getContractByNo(contractNo);
            if(ObjectUtil.isNotNull(contract)){
                throw new Exception("合同编号已存在！");
            }
            //构建费用期初信息
            CostBean cost = buildStartCostInfo(contractBean);
            contractMapper.saveContract(contractBean);
            costService.saveStartCost(cost);
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
            ContractBean contract = getContractByContractId(conId);
            if(ObjectUtil.isNull(contract)){
                throw new Exception("删除失败，未查询到合同信息");
            }
            if(ObjectUtil.equals(contract.getCon_state(), ContractStateEnum.USE.getValue())){
                throw new Exception("请先停用合同，再删除");
            }
            String contractNo = contract.getCon_no();
            // 删除合同信息
            contractMapper.deleteContract(contractBean);
            // 删除费用信息
            costService.deleteCostByContractNo(contractNo);
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
            contract = contractMapper.getContractByNo(contractBean.getCon_no());
            if(!ObjectUtil.equals(conId,contract.getCon_id())){
                throw new Exception("合同编号已存在！");
            }
            contractMapper.updateContract(contractBean);
        }catch (Exception e){
            LOGGER.error(e);
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<ContractBean> getContractList(ContractBean contractBean) throws Exception {
        try {
            return contractMapper.getContractList(contractBean);
        }catch (Exception e){
            LOGGER.error(e);
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public PageInfo<ContractBean> getContractList4Page(ContractBean contractBean) throws Exception {
        try {
            PageBean pageBean = contractBean.getPageBean();
            PageUtil.startPage(pageBean, "");
            List<ContractBean> contractList = contractMapper.getContractList(contractBean);
            PageInfo<ContractBean> pageInfo = new PageInfo<>(contractList);
            return pageInfo;
        } catch (Exception e) {
            LOGGER.error(e);
            throw new Exception(e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<ContractBean> getContractOptions() throws Exception {
        try {
            return contractMapper.getContractOptions();
        } catch (Exception e) {
            LOGGER.error(e);
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void updateContractState(ContractBean contractBean) throws Exception {
        try {
            String conId = contractBean.getCon_id();
            if (StrUtil.isBlank(conId)) {
                throw new Exception("参数{conId}异常");
            }
            ContractBean contract = getContractByContractId(conId);
            if (ObjectUtil.isNull(contract)) {
                throw new Exception("修改失败，未查询到合同信息");
            }
            contractMapper.updateContractState(contractBean);
        } catch (Exception e) {
            LOGGER.error(e);
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public ContractBean getContractByContractNo(String contractNo) throws Exception {
        try {
            if(StrUtil.isBlank(contractNo)){
                throw new Exception("参数错误！");
            }
            ContractBean contract = contractMapper.getContractByNo(contractNo);
            if(ObjectUtil.isNull(contract)){
                throw new Exception("合同信息不存在！");
            }
            return contract;
        } catch (Exception e) {
            LOGGER.error(e);
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 构建期初费用信息
     * @param contractBean contractBean
     * @return com.cattle.house.bean.CostBean
     * @author niujie
     * @date 2023/6/1
     */
    private CostBean buildStartCostInfo(ContractBean contractBean) {
        CostBean cost = new CostBean();
        cost.setCost_id(UuIdUtil.getUUID());
        cost.setCost_contract_no(contractBean.getCon_no());
        cost.setCost_times(0);
        cost.setCost_w_s_number(contractBean.getCon_w_start());
        cost.setCost_e_s_number(contractBean.getCon_e_start());
        cost.setCost_g_s_number(contractBean.getCon_g_start());
        cost.setCost_w_e_number(contractBean.getCon_w_start());
        cost.setCost_e_e_number(contractBean.getCon_e_start());
        cost.setCost_g_e_number(contractBean.getCon_g_start());
        cost.setCost_w_number(BigDecimal.ZERO);
        cost.setCost_e_number(BigDecimal.ZERO);
        cost.setCost_g_number(BigDecimal.ZERO);
        cost.setCost_w_money(BigDecimal.ZERO);
        cost.setCost_e_money(BigDecimal.ZERO);
        cost.setCost_g_money(BigDecimal.ZERO);
        cost.setCost_total_money(BigDecimal.ZERO);
        cost.setCost_date(contractBean.getCon_start_date());
        return cost;
    }
}
