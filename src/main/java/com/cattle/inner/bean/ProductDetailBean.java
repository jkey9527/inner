package com.cattle.inner.bean;

import lombok.Data;

/**
 * 货品明细
 *
 * @author niujie
 * @date 2023/8/5 16:39
 */
@Data
public class ProductDetailBean {

    /**
     * 货品明细ID
     */
    private String pro_det_id;

    /**
     * 货品ID
     */
    private String pro_main_id;

    /**
     * 货品明细颜色
     */
    private String pro_det_color;

    /**
     * 货品明细尺码
     */
    private String pro_det_size;

    /**
     * 货品明细库存
     */
    private Integer pro_det_num;

    /**
     * 分页对象
     */
    private PageBean pageBean;

}
