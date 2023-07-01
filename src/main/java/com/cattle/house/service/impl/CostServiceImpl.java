package com.cattle.house.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.cattle.house.bean.*;
import com.cattle.house.enums.RecordTypeEnum;
import com.cattle.house.enums.SystemEnum;
import com.cattle.house.mapper.ContractMapper;
import com.cattle.house.mapper.CostMapper;
import com.cattle.house.service.CostService;
import com.cattle.house.service.RecordService;
import com.cattle.house.service.SystemService;
import com.cattle.house.service.UserService;
import com.cattle.house.util.PageUtil;
import com.cattle.house.util.UuIdUtil;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * 费用服务类
 *
 * @author niujie
 * @date 2023/4/21 22:40
 */
@Service
@AllArgsConstructor
public class CostServiceImpl implements CostService {
    private static final Logger LOGGER = Logger.getLogger(CostServiceImpl.class);

    private UserService userService;

    private ContractMapper contractMapper;

    private CostMapper costMapper;

    private RecordService recordService;

    private SystemService systemService;


    @Override
    public List<CostBean> getAllCostList(CostBean cost) throws Exception {
        try {
            return costMapper.getAllCostList(cost);
        }catch (Exception e){
            LOGGER.error(e);
            throw new Exception(e);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveCost(CostBean cost) throws Exception {
        try {
            Date curDate = new Date();
            cost.setCost_id(UuIdUtil.getUUID());
            cost.setCost_date(curDate);
            List<CostBean> costBeanList = costMapper.getCostListByContractNo(cost.getCost_contract_no());
            int times = 1;
            if (CollUtil.isNotEmpty(costBeanList)) {
                SystemBean system = systemService.getSystemByCode(SystemEnum.SUBMIT_COST.getCode());
                Boolean enableSubmitCost = Convert.toBool(system.getSys_value(), false);
                CostBean costBean = costBeanList.stream().max(Comparator.comparing(CostBean::getCost_times)).get();
                if (DateUtil.isSameMonth(curDate, costBean.getCost_date()) && !enableSubmitCost) {
                    throw new Exception("本月已完成一次费用提交，请勿重复提交！");
                }
                times = costBean.getCost_times() + 1;
            }
            cost.setCost_times(times);
            // 构建收入信息
            RecordBean record = buildCollRecord(cost);
            costMapper.saveCost(cost);
            if(ObjectUtil.isNotNull(record)){
                recordService.saveRecord(record);
            }
        } catch (Exception e) {
            LOGGER.error(e);
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public CostBean calculateCost(CostBean cost) throws Exception {
        try {
            ContractBean contractBean = new ContractBean();
            contractBean.setCon_no(cost.getCost_contract_no());
            List<ContractBean> contractBeanList = contractMapper.getContractList(contractBean);
            if (CollUtil.isEmpty(contractBeanList)) {
                throw new Exception("未查询到合同信息");
            }
            if (contractBeanList.size() > 1) {
                throw new Exception("查询到多条合同信息，核算费用失败，请联系管理员！");
            }
            List<CostBean> costList = getCostList(cost);
            //计算本期金额
            calculateCurCost(contractBeanList, costList, cost);
            return cost;
        } catch (Exception e) {
            LOGGER.error(e);
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<CostBean> getCostList(CostBean cost) throws Exception {
        try {
            return costMapper.getCostList(cost);
        } catch (Exception e) {
            LOGGER.error(e);
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public CostBean initCost(UserBean user) throws Exception {
        try {
            UserBean userBean = userService.getUserByUserId(user.getUser_id());
            if (ObjectUtil.isNull(userBean)) {
                throw new Exception("未查询到用户信息");
            }
            List<CostBean> costBeans = costMapper.getCostListByContractNo(userBean.getUser_contract_no());
            if (CollUtil.isEmpty(costBeans)) {
                return new CostBean();
            }
            CostBean costBean = costBeans.stream().sorted(Comparator.comparing(CostBean::getCost_times).reversed()).findFirst().get();
            initCostInfo(costBean);
            return costBean;
        } catch (Exception e) {
            LOGGER.error(e);
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public PageInfo<CostBean> getAllCostList4Page(CostBean cost) throws Exception {
        try {
            PageBean pageBean = cost.getPageBean();
            PageUtil.startPage(pageBean, "cost_times desc");
            List<CostBean> costList = costMapper.getAllCostList(cost);
            PageInfo<CostBean> pageInfo = new PageInfo<>(costList);
            return pageInfo;
        } catch (Exception e) {
            LOGGER.error(e);
            throw new Exception(e);
        }
    }

    @Override
    public PageInfo<CostBean> getCostListByContractNo4Page(CostBean cost) throws Exception {
        try {
            PageBean pageBean = cost.getPageBean();
            PageUtil.startPage(pageBean, "cost_times desc");
            List<CostBean> costList = costMapper.getCostList(cost);
            PageInfo<CostBean> pageInfo = new PageInfo<>(costList);
            return pageInfo;
        } catch (Exception e) {
            LOGGER.error(e);
            throw new Exception(e);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveStartCost(CostBean cost) throws Exception {
        try {
            costMapper.saveCost(cost);
        } catch (Exception e) {
            LOGGER.error(e);
            throw new Exception(e);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteCostByContractNo(String contractNo) throws Exception {
        try {
            costMapper.deleteCostByContractNo(contractNo);
        } catch (Exception e) {
            LOGGER.error(e);
            throw new Exception(e);
        }
    }

    /**
     * 初始化费用信息
     *
     * @param costBean costBean
     * @return void
     * @author niujie
     * @date 2023/4/25
     */
    private void initCostInfo(CostBean costBean) {
        costBean.setCost_w_s_number(costBean.getCost_w_e_number());
        costBean.setCost_e_s_number(costBean.getCost_e_e_number());
        costBean.setCost_g_s_number(costBean.getCost_g_e_number());
        costBean.setCost_w_e_number(BigDecimal.ZERO);
        costBean.setCost_e_e_number(BigDecimal.ZERO);
        costBean.setCost_g_e_number(BigDecimal.ZERO);
    }

    /**
     * 计算本期读数、本期金额、本期总金额
     *
     * @param contractBeanList contractList
     * @param costList     costList
     * @param cost         cost
     * @return void
     * @author niujie
     * @date 2023/4/22
     */
    private void calculateCurCost(List<ContractBean> contractBeanList, List<CostBean> costList, CostBean cost) throws Exception {
        if (CollUtil.isEmpty(costList)) {
            cost.setCost_w_s_number(BigDecimal.ZERO);
            cost.setCost_e_s_number(BigDecimal.ZERO);
            cost.setCost_g_s_number(BigDecimal.ZERO);
        } else {
            CostBean costBean = costList.stream().sorted(Comparator.comparing(CostBean::getCost_times).reversed()).findFirst().get();
            cost.setCost_w_s_number(costBean.getCost_w_e_number());
            cost.setCost_e_s_number(costBean.getCost_e_e_number());
            cost.setCost_g_s_number(costBean.getCost_g_e_number());
        }
        ContractBean contractBean = contractBeanList.get(0);
        //计算本期读数
        cost.setCost_w_number(Convert.toBigDecimal(cost.getCost_w_e_number(), BigDecimal.ZERO).subtract(cost.getCost_w_s_number()));
        if (cost.getCost_w_number().compareTo(BigDecimal.ZERO) <= 0) {
            throw new Exception("水表期末读数不正确，应大于期初数值");
        }
        cost.setCost_e_number(Convert.toBigDecimal(cost.getCost_e_e_number(), BigDecimal.ZERO).subtract(cost.getCost_e_s_number()));
        if (cost.getCost_e_number().compareTo(BigDecimal.ZERO) <= 0) {
            throw new Exception("电表期末读数不正确，应大于期初数值");
        }
        cost.setCost_g_number(Convert.toBigDecimal(cost.getCost_g_e_number(), BigDecimal.ZERO).subtract(cost.getCost_g_s_number()));
        if (cost.getCost_g_number().compareTo(BigDecimal.ZERO) <= 0) {
            throw new Exception("气表期末读数不正确，应大于期初数值");
        }
        //计算本期金额
        cost.setCost_w_money(contractBean.getCon_w_price().multiply(cost.getCost_w_number()));
        cost.setCost_e_money(contractBean.getCon_e_price().multiply(cost.getCost_e_number()));
        cost.setCost_g_money(contractBean.getCon_g_price().multiply(cost.getCost_g_number()));
        //计算合计金额
        cost.setCost_total_money(cost.getCost_w_money().add(cost.getCost_e_money()).add(cost.getCost_g_money()));
    }

    /**
     * 构建收入费用信息
     *
     * @param cost cost
     * @return com.cattle.house.bean.RecordBean
     * @author niujie
     * @date 2023/6/1
     */
    private RecordBean buildCollRecord(CostBean cost) throws Exception {
        String costContractNo = cost.getCost_contract_no();
        if (costContractNo.startsWith("601@") || costContractNo.startsWith("602@") || costContractNo.startsWith("801@")) {
            //管理员不进行收入登记
            return null;
        }
        ContractBean contract =
                contractMapper.getContractByNo(costContractNo);
        if (ObjectUtil.isNull(contract)) {
            throw new Exception("合同信息查询失败！请联系管理员！");
        }
        Integer houseNo = contract.getCon_house_no();
        RecordBean record = new RecordBean();
        record.setR_type(RecordTypeEnum.COLL.getValue());
        record.setR_money(cost.getCost_total_money());
        record.setR_date(cost.getCost_date());
        record.setR_msg(houseNo+"水电气（自缴）");
        return record;
    }
}
