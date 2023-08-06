package com.cattle.inner.enums;

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
}
