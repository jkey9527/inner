package com.cattle.inner.bean;

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
     * 分页对象
     */
    private PageBean pageBean;
}
