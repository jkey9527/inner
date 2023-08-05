package com.cattle.inner.util;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.UUID;

/**
 * UUID工具类
 *
 * @author niujie
 * @date 2023/4/22 11:39
 */
public class UuIdUtil {

    /**
     * 获取UUID
     * @return java.lang.String
     * @author niujie
     * @date 2023/4/22
     */
    public static String getUUID(){
        return Convert.toStr(UUID.randomUUID());
    }

    public static void main(String[] args) {
        System.out.println(getUUID());
    }
}
