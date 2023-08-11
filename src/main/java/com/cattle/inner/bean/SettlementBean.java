package com.cattle.inner.bean;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

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
     * 退款金额
     */
    private BigDecimal s_coll_money;

    /**
     * 销售金额
     */
    private BigDecimal s_sell_money;

    /**
     * 支出金额
     */
    private BigDecimal s_pay_money;

    /**
     * 支出1
     */
    private BigDecimal s_pay_money1;

    /**
     * 支出2
     */
    private BigDecimal s_pay_money2;

    /**
     * 纯利润
     */
    private BigDecimal s_money;

    /**
     * 股东一
     */
    private BigDecimal s_sub_money1;

    /**
     * 股东二
     */
    private BigDecimal s_sub_money2;

    /**
     * 结算统计
     */
    private Map sellTypeMap;

}
