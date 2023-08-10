package com.cattle.inner.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.cattle.inner.bean.CostBean;
import com.cattle.inner.bean.RecordBean;
import com.cattle.inner.bean.RecordParam;
import com.cattle.inner.bean.SettlementBean;
import com.cattle.inner.enums.LogModelEnum;
import com.cattle.inner.enums.LogTypeEnum;
import com.cattle.inner.mapper.CostMapper;
import com.cattle.inner.mapper.RecordMapper;
import com.cattle.inner.service.CostService;
import com.cattle.inner.service.SystemService;
import com.cattle.inner.util.UuIdUtil;
import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

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

    private CostMapper costMapper;
    private RecordMapper recordMapper;
    private SystemService systemService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveCost(CostBean cost) throws Exception {
        try {
            cost.setCost_id(UuIdUtil.getUUID());
            String type = cost.getCost_type();
            if(ObjectUtil.equals("1",type)){
                // 支出
                cost.setCost_money(BigDecimal.ZERO.subtract(cost.getCost_money()));
            }
            costMapper.saveCost(cost);
            systemService.saveOptLog(LogModelEnum.cost.getValue(), LogTypeEnum.save.getValue(), JSONUtil.toJsonStr(cost));
        }catch (Exception e){
            LOGGER.error("操作异常",e);
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<CostBean> getCostList(CostBean cost) throws Exception {
        try {
            return costMapper.getCostList(cost);
        }catch (Exception e){
            LOGGER.error("操作异常",e);
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public SettlementBean getSettlement(SettlementBean settlementParam) throws Exception {
        try {
            String yearMonth = settlementParam.getS_month();
            if(StrUtil.isBlank(yearMonth)){
                throw new Exception("结算异常，未选择对应的结算年月！");
            }
            Date date = DateUtil.parse(yearMonth,"yyyy-MM");
            RecordParam recordParam = new RecordParam();
            DateTime startDate = DateUtil.beginOfMonth(date);
            DateTime endDate = DateUtil.endOfMonth(date);
            recordParam.setStartDay(startDate);
            recordParam.setEndDay(endDate);
            List<RecordBean> records = recordMapper.getRecord(recordParam);
            BigDecimal collTotalMoney = records.stream().map(RecordBean::getR_sell_price).reduce(BigDecimal.ZERO, BigDecimal::add);
            CostBean costBean = new CostBean();
            costBean.setStartDay(startDate);
            costBean.setEndDay(endDate);
            BigDecimal payTotalMoney = BigDecimal.ZERO;
            List<CostBean> costList = costMapper.getCostList(costBean);
            if(CollUtil.isNotEmpty(costList)){
                payTotalMoney = costList.stream().map(CostBean::getCost_money).reduce(BigDecimal.ZERO, BigDecimal::add);
            }
            BigDecimal subMoney = collTotalMoney.add(payTotalMoney);
            SettlementBean settlement = new SettlementBean();
            settlement.setS_month(yearMonth);
            settlement.setS_coll_money(collTotalMoney);
            settlement.setS_pay_money(payTotalMoney);
            settlement.setS_money(subMoney);
            BigDecimal divMoney = subMoney.divide(new BigDecimal(2));
            // 计算子金额信息
            Map<String, List<CostBean>> subCost = costList.stream().filter(e -> ObjectUtil.equals(e.getCost_type(), "1")).collect(Collectors.groupingBy(CostBean::getCost_user_id));
            if(subCost.containsKey("1")){
                List<CostBean> costBeans = subCost.get("1");
                BigDecimal subMoney1 = costBeans.stream().map(CostBean::getCost_money).reduce(BigDecimal.ZERO, BigDecimal::add);
                settlement.setS_sub_money1(divMoney.add(subMoney1.abs()));
            }else{
                settlement.setS_sub_money1(divMoney);
            }
            if(subCost.containsKey("2")){
                List<CostBean> costBeans = subCost.get("2");
                BigDecimal subMoney2 = costBeans.stream().map(CostBean::getCost_money).reduce(BigDecimal.ZERO, BigDecimal::add);
                settlement.setS_sub_money2(divMoney.add(subMoney2.abs()));
            }else{
                settlement.setS_sub_money2(divMoney);
            }
            return settlement;
        }catch (Exception e){
            LOGGER.error("操作异常",e);
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void saveSettlement(SettlementBean settlement) throws Exception {
        try {
            if(StrUtil.isBlank(settlement.getS_month())){
                throw new Exception("结算失败，月度信息为空！");
            }
            List<SettlementBean> settlementList = costMapper.getSettlementList(settlement);
            if(CollUtil.isNotEmpty(settlementList)){
                throw new Exception("结算失败，当前月度已完成结算！");
            }
            settlement.setS_id(UuIdUtil.getUUID());
            costMapper.saveSettlement(settlement);
            systemService.saveOptLog(LogModelEnum.cost.getValue(), LogTypeEnum.save.getValue(), JSONUtil.toJsonStr(settlement));
        }catch (Exception e){
            LOGGER.error("操作异常",e);
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<SettlementBean> getSettlementList(SettlementBean settlement) throws Exception {
        try {
            return costMapper.getSettlementList(settlement);
        }catch (Exception e){
            LOGGER.error("操作异常",e);
            throw new Exception(e.getMessage());
        }
    }
}
