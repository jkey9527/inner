package com.cattle.inner.response;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.cattle.inner.constant.ResponseStateConstant;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回结果
 *
 * @author niujie
 * @date 2023/4/22 11:02
 */
public class Result {

    /**
     * 返回成功
     *
     * @param message message
     * @param obj     obj
     * @return java.lang.String
     * @author niujie
     * @date 2023/4/22
     */
    public static String success(String message, Object obj) {
        Map result = new HashMap<>(3);
        result.put("code", ResponseStateConstant.SUCCESS);
        result.put("message", message);
        if (ObjectUtil.isNotNull(obj)) {
            result.put("data", obj);
        }
        return JSONUtil.toJsonStr(result);
    }

    /**
     * 返回成功
     *
     * @param message message
     * @return java.lang.String
     * @author niujie
     * @date 2023/4/22
     */
    public static String success(String message) {
        return success(message, null);
    }

    /**
     * 返回失败
     *
     * @param message message
     * @return java.lang.String
     * @author niujie
     * @date 2023/4/22
     */
    public static String fail(String message) {
        Map result = new HashMap<>(2);
        result.put("code", ResponseStateConstant.FAIL);
        result.put("message", message);
        return JSONUtil.toJsonStr(result);
    }

    /**
     * 重新登录
     *
     * @param message message
     * @return java.lang.String
     * @author niujie
     * @date 2023/4/29
     */
    public static String failToLogin(String message) {
        Map result = new HashMap<>(2);
        result.put("code", ResponseStateConstant.RE_LOGIN);
        result.put("message", message);
        return JSONUtil.toJsonStr(result);
    }
}
