package com.cattle.inner.bean;

import lombok.Data;

import java.util.Date;

/**
 * 操作日志
 *
 * @author niujie
 * @date 2023/8/6 15:56
 */
@Data
public class OptLog {

    /**
     * 日志ID
     */
    private String log_id;

    /**
     * 日志模块
     */
    private String log_model;

    /**
     * 操作类型
     */
    private String log_type;

    /**
     * 日志内容
     */
    private String log_info;

    /**
     * 日志备注
     */
    private String log_memo;

    /**
     * 日志时间
     */
    private Date log_date;

    /**
     * 开始日期
     */
    private Date startDay;

    /**
     * 结束日期
     */
    private Date endDay;

    /**
     * 用户ID
     */
    private String log_user_id;

}
