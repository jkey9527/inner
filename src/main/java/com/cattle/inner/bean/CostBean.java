package com.cattle.inner.bean;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 费用
 *
 * @author niujie
 * @date 2023/8/6 13:51
 */
@Data
public class CostBean {

    /**
     * 费用ID
     */
    private String cost_id;

    /**
     * 金额
     */
    private BigDecimal cost_money;

    /**
     * 项目
     */
    private String cost_pro;

    /**
     * 支出人
     */
    private String cost_user_id;

    /**
     * 交易日期
     */
    private Date cost_date;

    /**
     * 支出类型
     */
    private String cost_type;

    /**
     * 开始日期
     */
    private Date startDay;

    /**
     * 结束日期
     */
    private Date endDay;

    /**
     * 开始金额
     */
    private BigDecimal startMoney;

    /**
     * 结束金额
     */
    private BigDecimal endMoney;

}
