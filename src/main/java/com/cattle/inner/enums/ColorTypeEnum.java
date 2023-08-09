package com.cattle.inner.enums;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;

/**
 * 颜色类型
 *
 * @author niujie
 * @date 2023/8/6 16:11
 */
public enum ColorTypeEnum {

    color1("1","黑"),
    color2("2","白"),
    color3("3","灰"),
    color4("4","咖"),
    color5("5","其他");

    private String value;
    private String name;

    ColorTypeEnum(String value, String name) {
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
        for (ColorTypeEnum productTypeEnum : ColorTypeEnum.values()) {
            if(ObjectUtil.equals(value,productTypeEnum.getValue())){
                return productTypeEnum.getName();
            }
        }
        return "";
    }
}
