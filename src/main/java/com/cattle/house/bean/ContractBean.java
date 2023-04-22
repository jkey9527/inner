package com.cattle.house.bean;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 合同实体bean
 *
 * @author niujie
 * @date 2023/4/21 22:33
 */
@Data
public class ContractBean {

    /**
     * 合同主键
     */
    private String con_id;

    /**
     * 合同编号
     */
    private String con_no;

    /**
     * 合同开始日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date con_start_date;

    /**
     * 合同结束日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date con_end_date;

    /**
     * 合同签订年限
     */
    private Integer con_year;

    /**
     * 房屋类型
     */
    private Integer con_house_type;

    /**
     * 房间号
     */
    private Integer con_house_no;

    /**
     * 每月租金
     */
    private BigDecimal con_month_money;

    /**
     * 押金
     */
    private BigDecimal con_deposit_money;

    /**
     * 已付租金
     */
    private BigDecimal con_paid_money;

    /**
     * 待付租金
     */
    private BigDecimal con_pay_money;

    /**
     * 总租金
     */
    private BigDecimal con_total_money;

    /**
     * 钥匙数
     */
    private Integer con_key_num;

    /**
     * 水单价
     */
    private BigDecimal con_w_price;

    /**
     * 电单价
     */
    private BigDecimal con_e_price;

    /**
     * 气单价
     */
    private BigDecimal con_g_price;

    /**
     * 合同状态
     */
    private Integer con_state;
}
