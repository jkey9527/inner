package com.cattle.inner.bean;

import lombok.Data;

import java.util.Date;

/**
 * 销售
 *
 * @author niujie
 * @date 2023/8/6 11:49
 */
@Data
public class RecordParam {

    /**
     * 年
     */
    private String year;

    /**
     * 月
     */
    private String month;

    /**
     * 周
     */
    private String week;

    /**
     * 当天
     */
    private Date day;

    /**
     * 开始日期
     */
    private Date startDay;

    /**
     * 结束日期
     */
    private Date endDay;


}
