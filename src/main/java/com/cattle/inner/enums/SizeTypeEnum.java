package com.cattle.inner.enums;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;

/**
 * 颜色类型
 *
 * @author niujie
 * @date 2023/8/6 16:11
 */
public enum SizeTypeEnum {

    size1("1","均码"),
    size2("2","S"),
    size3("3","M"),
    size4("4","L");

    private String value;
    private String name;

    SizeTypeEnum(String value, String name) {
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
        for (SizeTypeEnum productTypeEnum : SizeTypeEnum.values()) {
            if(ObjectUtil.equals(value,productTypeEnum.getValue())){
                return productTypeEnum.getName();
            }
        }
        return "";
    }
}
