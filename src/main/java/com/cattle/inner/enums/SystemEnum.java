package com.cattle.inner.enums;

/**
 * 合同状态枚举
 *
 * @author niujie
 * @date 2023/5/14 11:46
 */
public enum SystemEnum {

    SUBMIT_COST(1,"submit_cost", "当月二次提交费用");

    private Integer value;
    private String code;
    private String name;

    SystemEnum(Integer value, String code, String name) {
        this.value = value;
        this.code = code;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
