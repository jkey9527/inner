package com.cattle.inner.bean;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 结算
 *
 * @author niujie
 * @date 2023/8/6 14:56
 */
@Data
public class SettlementBean {

    /**
     * 结算ID
     */
    private String s_id;

    /**
     * 结算时间
     */
    private String s_month;

    /**
     * 收入金额
     */
    private BigDecimal s_coll_money;

    /**
     * 支出金额
     */
    private BigDecimal s_pay_money;

    /**
     * 纯利润
     */
    private BigDecimal s_money;

}
