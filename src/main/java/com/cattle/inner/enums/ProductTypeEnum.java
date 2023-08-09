package com.cattle.inner.enums;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;

/**
 * 货品类型
 *
 * @author niujie
 * @date 2023/8/6 16:11
 */
public enum ProductTypeEnum {

    type1("1","上衣"),
    type2("2","基础打底"),
    type3("3","外套"),
    type4("4","套装"),
    type5("5","裤子"),
    type6("6","半裙"),
    type7("7","连衣裙");

    private String value;
    private String name;

    ProductTypeEnum(String value, String name) {
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
        for (ProductTypeEnum productTypeEnum : ProductTypeEnum.values()) {
            if(ObjectUtil.equals(value,productTypeEnum.getValue())){
                return productTypeEnum.getName();
            }
        }
        return "";
    }
}
