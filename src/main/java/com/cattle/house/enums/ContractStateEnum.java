package com.cattle.house.enums;

/**
 * 合同状态枚举
 *
 * @author niujie
 * @date 2023/5/14 11:46
 */
public enum ContractStateEnum {

    STOP(0, "停租"),
    USE(1, "在租");

    private Integer value;
    private String name;

    ContractStateEnum(Integer value, String name) {
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
