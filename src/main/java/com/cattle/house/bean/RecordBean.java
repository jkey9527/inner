package com.cattle.house.bean;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 费用记录实体
 *
 * @author niujie
 * @date 2023/5/27 19:54
 */
@Data
public class RecordBean implements Serializable {

    /**
     * 明细主键
     */
    private String r_id;

    /**
     * 收支方向：0-收；1-支
     */
    private Integer r_type;

    /**
     * 金额
     */
    private BigDecimal r_money;

    /**
     * 交易日期
     */
    private Date r_date;

    /**
     * 交易描述
     */
    private String r_msg;

    /**
     * 创建时间
     */
    private Date r_create_time;

    /**
     * 分页对象
     */
    private PageBean pageBean;
}
