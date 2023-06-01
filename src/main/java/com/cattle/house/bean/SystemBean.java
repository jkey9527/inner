package com.cattle.house.bean;

import lombok.Data;

import java.io.Serializable;

/**
 *  系统参数实体
 * @author niujie
 * @date 2023/6/1 16:51
 */
@Data
public class SystemBean implements Serializable {

    /**
     * 系统参数ID
     */
    private Integer sys_id;

    /**
     * 参数编码
     */
    private String sys_code;

    /**
     * 参数值
     */
    private String sys_value;

    /**
     * 描述
     */
    private String sys_label;
}
