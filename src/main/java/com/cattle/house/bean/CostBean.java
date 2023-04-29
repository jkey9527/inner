package com.cattle.house.bean;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 费用实体bean
 *
 * @author niujie
 * @date 2023/4/21 22:33
 */
@Data
public class CostBean implements Serializable {

    /**
     * 费用主键
     */
    private String cost_id;

    /**
     * 合同编号
     */
    private String cost_contract_no;

    /**
     * 抄表次数
     */
    private Integer cost_times;

    /**
     * 水期初读数
     */
    private BigDecimal cost_w_s_number;

    /**
     * 水期末读数
     */
    private BigDecimal cost_w_e_number;

    /**
     * 电期初读数
     */
    private BigDecimal cost_e_s_number;

    /**
     * 电期末读数
     */
    private BigDecimal cost_e_e_number;

    /**
     * 气期初读数
     */
    private BigDecimal cost_g_s_number;

    /**
     * 气期末读数
     */
    private BigDecimal cost_g_e_number;

    /**
     * 水本期费用
     */
    private BigDecimal cost_w_money;

    /**
     * 电本期费用
     */
    private BigDecimal cost_e_money;

    /**
     * 气本期费用
     */
    private BigDecimal cost_g_money;

    /**
     * 本期费用合计
     */
    private BigDecimal cost_total_money;

    /**
     * 抄表日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date cost_date;

    /**
     * 水本期读数
     */
    private BigDecimal cost_w_number;

    /**
     * 电本期读数
     */
    private BigDecimal cost_e_number;

    /**
     * 气本期读数
     */
    private BigDecimal cost_g_number;

}
