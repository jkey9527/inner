package com.cattle.inner.enums;

/**
 * 日志类型
 *
 * @author niujie
 * @date 2023/8/6 16:11
 */
public enum LogModelEnum {
    user("user","用户"),
    product("product","货品"),
    record("record","销售"),
    cost("cost","费用"),
    settlement("settlement","结算");

    private String value;
    private String name;

    LogModelEnum(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
