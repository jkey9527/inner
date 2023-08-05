package com.cattle.inner.enums;

/**
 * 明细类型枚举
 *
 * @author niujie
 * @date 2023/5/14 11:46
 */
public enum RecordTypeEnum {

    PAY(1, "付款"),
    COLL(0, "收款");

    private Integer value;
    private String name;

    RecordTypeEnum(Integer value, String name) {
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
