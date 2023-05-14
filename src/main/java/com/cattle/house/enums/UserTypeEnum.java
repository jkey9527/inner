package com.cattle.house.enums;

/**
 * 用户类型枚举
 *
 * @author niujie
 * @date 2023/5/14 11:46
 */
public enum UserTypeEnum {

    ORDINARY(1, "普通"),
    MANAGER(2, "管理员");

    private Integer value;
    private String name;

    UserTypeEnum(Integer value, String name) {
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
