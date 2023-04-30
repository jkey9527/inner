package com.cattle.house.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户实体
 *
 * @author niujie
 * @date 2023/4/21 22:33
 */
@Data
public class UserBean implements Serializable {

    /**
     * 用户主键
     */
    private String user_id;

    /**
     * 用户编号
     */
    private String user_no;

    /**
     * 用户名称
     */
    private String user_name;

    /**
     * 用户手机号
     */
    private String user_phone;

    /**
     * 用户密码
     */
    private String user_password;

    /**
     * 用户类型：1-租客;2-房东
     */
    private Integer user_type;

    /**
     * 合同编号
     */
    private String user_contract_no;

    /**
     * 用户状态：0-停用
     */
    private Integer user_state;

    /**
     * 身份证号
     */
    private String user_id_card;

    /**
     * 车牌号
     */
    private String user_car_no;

    /**
     * 备注
     */
    private String user_notes;

    /**
     * 密码锁编号
     */
    private Integer user_key_no;
}
