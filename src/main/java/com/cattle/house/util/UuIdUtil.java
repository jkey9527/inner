package com.cattle.house.util;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.UUID;

/**
 * TODO
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
}
