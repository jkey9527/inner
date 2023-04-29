package com.cattle.house.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.cattle.house.bean.ContractBean;
import com.cattle.house.bean.CostBean;
import com.cattle.house.bean.UserBean;
import com.cattle.house.mapper.ContractMapper;
import com.cattle.house.mapper.CostMapper;
import com.cattle.house.mapper.UserMapper;
import com.cattle.house.service.CostService;
import com.cattle.house.util.UuIdUtil;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private static final Logger LOGGER = LogManager.getLogger(CostServiceImpl.class);

    private UserMapper userMapper;

    private ContractMapper contractMapper;

    private CostMapper costMapper;


    @Override
    public List<CostBean> getAllCostList(CostBean cost) {
        return costMapper.getAllCostList(cost);
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
                CostBean costBean = costBeanList.stream().max(Comparator.comparing(CostBean::getCost_times)).get();
                if (DateUtil.isSameMonth(curDate, costBean.getCost_date())) {
                    throw new Exception("本月已完成一次费用提交，请勿重复提交！");
                }
                times = costBean.getCost_times() + 1;
            }
            cost.setCost_times(times);
            costMapper.saveCost(cost);
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
            List<ContractBean> contractList = contractMapper.getContractList(contractBean);
            if (CollUtil.isEmpty(contractList)) {
                throw new Exception("未查询到合同信息");
            }
            if (contractList.size() > 1) {
                throw new Exception("查询到多条合同信息，核算费用失败，请联系管理员！");
            }
            List<CostBean> costList = getCostList(cost);
            //计算本期金额
            calculateCurCost(contractList, costList, cost);
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
            UserBean userBean = userMapper.getUserByUserId(user.getUser_id());
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
     * @param contractList contractList
     * @param costList     costList
     * @param cost         cost
     * @return void
     * @author niujie
     * @date 2023/4/22
     */
    private void calculateCurCost(List<ContractBean> contractList, List<CostBean> costList, CostBean cost) throws Exception {
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
        ContractBean contractBean = contractList.get(0);
        //计算本期读数
        cost.setCost_w_number(cost.getCost_w_e_number().subtract(cost.getCost_w_s_number()));
        if (cost.getCost_w_number().compareTo(BigDecimal.ZERO) <= 0) {
            throw new Exception("水表期末读数不正确，应大于期初数值");
        }
        cost.setCost_e_number(cost.getCost_e_e_number().subtract(cost.getCost_e_s_number()));
        if (cost.getCost_e_number().compareTo(BigDecimal.ZERO) <= 0) {
            throw new Exception("电表期末读数不正确，应大于期初数值");
        }
        cost.setCost_g_number(cost.getCost_g_e_number().subtract(cost.getCost_g_s_number()));
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
}
