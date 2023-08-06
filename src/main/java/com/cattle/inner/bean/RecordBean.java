package com.cattle.inner.bean;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 销售实体
 *
 * @author niujie
 * @date 2023/8/6 10:46
 */
@Data
public class RecordBean {

    /**
     * 销售ID
     */
    private String r_id;

    /**
     * 销售货品ID
     */
    private String r_pro_id;

    /**
     * 销售用户ID
     */
    private String r_user_id;

    /**
     * 销售日期
     */
    private Date r_date;

    /**
     * 销售周
     */
    private Integer r_week;

    /**
     * 销售月
     */
    private Integer r_month;

    /**
     * 销售年
     */
    private Integer r_year;

    /**
     * 收款方式
     */
    private String r_type;

    /**
     * 售价
     */
    private BigDecimal r_sell_price;

    /**
     * 销售数量
     */
    private Integer r_sell_num;

    /**
     * 货品明细集合
     */
    private List<ProductDetailBean> productDetailBeans;

    /**
     * 货品类型
     */
    private String r_pro_type;

}
