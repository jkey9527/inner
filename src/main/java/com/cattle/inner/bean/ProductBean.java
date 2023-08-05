package com.cattle.inner.bean;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 货品
 *
 * @author niujie
 * @date 2023/8/5 16:13
 */
@Data
public class ProductBean {

    /**
     * 货品ID
     */
    private String pro_id;

    /**
     * 货品编号
     */
    private String pro_no;

    /**
     * 货品名称
     */
    private String pro_name;

    /**
     * 货品类型
     */
    private String pro_type;

    /**
     * 真实进价
     */
    private BigDecimal pro_real_price;

    /**
     * 进价
     */
    private BigDecimal pro_price;

    /**
     * 售价
     */
    private BigDecimal pro_sell_price;

    /**
     * 货品明细
     */
    private List<ProductDetailBean> productDetailBeans;

    /**
     * 分页对象
     */
    private PageBean pageBean;

}
