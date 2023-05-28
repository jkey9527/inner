package com.cattle.house.enums;

/**
 * 用户状态枚举
 *
 * @author niujie
 * @date 2023/5/14 11:46
 */
public enum UserStateEnum {

    STOP(0, "停用"),
    USE(1, "启用");

    private Integer value;
    private String name;

    UserStateEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
