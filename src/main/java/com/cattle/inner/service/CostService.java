package com.cattle.inner.service;

import com.cattle.inner.bean.CostBean;
import com.cattle.inner.bean.SettlementBean;

import java.util.List;

/**
 * 费用服务接口
 *
 * @author niujie
 * @date 2023/4/21 22:38
 */
public interface CostService {

    /**
     * 费用
     * @param cost cost
     * @return void
     * @author niujie
     * @date 2023/8/6
     */
    void saveCost(CostBean cost) throws Exception;

    /**
     * 查询费用
     * @param cost cost
     * @return java.util.List<com.cattle.inner.bean.CostBean>
     * @author niujie
     * @date 2023/8/6
     */
    List<CostBean> getCostList(CostBean cost) throws Exception;

    /**
     * 结算
     * @return com.cattle.inner.bean.SettlementBean
     * @author niujie
     * @date 2023/8/6
     */
    SettlementBean getSettlement(SettlementBean settlement) throws Exception;

    /**
     * 保存结算
     * @param settlement settlement
     * @return void
     * @author niujie
     * @date 2023/8/6
     */
    void saveSettlement(SettlementBean settlement) throws Exception;

    /**
     * 查询结算
     * @param settlement settlement
     * @return java.util.List<com.cattle.inner.bean.SettlementBean>
     * @author niujie
     * @date 2023/8/6
     */
    List<SettlementBean> getSettlementList(SettlementBean settlement) throws Exception;
}
