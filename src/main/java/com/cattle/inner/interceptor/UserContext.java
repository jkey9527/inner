package com.cattle.inner.interceptor;

import com.cattle.inner.bean.UserBean;

/**
 * TODO
 *
 * @author niujie
 * @date 2023/8/6 16:55
 */
public class UserContext {

    private static ThreadLocal<UserBean> userThread = new ThreadLocal<>();

    public static void set(UserBean user) {
        userThread.set(user);
    }

    public static UserBean get() {
        return userThread.get();
    }

    /**
     * 获取当前登录用户的ID
     * 未登录返回null
     *
     * @return
     */
    public static String getUserId() {
        UserBean user = get();
        if (user != null && user.getUser_id() != null) {
            return user.getUser_id();
        }
        return null;
    }

    //防止内存泄漏
    public static void remove() {
        userThread.remove();
    }
}
