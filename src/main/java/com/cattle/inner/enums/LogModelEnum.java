package com.cattle.inner.enums;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;

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

    public static String getNameByValue(String value){
        if(StrUtil.isBlank(value)){
            return "";
        }
        for (LogModelEnum modelEnum : LogModelEnum.values()) {
            if(ObjectUtil.equals(value,modelEnum.getValue())){
                return modelEnum.getName();
            }
        }
        return "";
    }
}
