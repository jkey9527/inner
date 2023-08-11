package com.cattle.inner.enums;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;

/**
 * 日志类型
 *
 * @author niujie
 * @date 2023/8/6 16:11
 */
public enum LogTypeEnum {
    save("save","保存"),
    update("update","更新"),
    delete("delete","删除"),
    login_in("login_in","登录"),
    login_out("login_out","注销");

    private String value;
    private String name;

    LogTypeEnum(String value, String name) {
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
        for (LogTypeEnum logTypeEnum : LogTypeEnum.values()) {
            if(ObjectUtil.equals(value,logTypeEnum.getValue())){
                return logTypeEnum.getName();
            }
        }
        return "";
    }
}
