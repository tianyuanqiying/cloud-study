package com.cloud.study.common;

import com.cloud.study.dto.UserInfo;

/**
 * @author user
 */
public class UserKit {
    private static ThreadLocal<UserInfo> userInfoThreadLocal = new ThreadLocal<>();

    public static void set(UserInfo userInfo) {
        userInfoThreadLocal.set(userInfo);
    }

    public static String getUserName() {
        if (checkUserEmpty()) {
            return null;
        }
        return userInfoThreadLocal.get().getName();
    }
    public static String getUserId() {
        if (checkUserEmpty()) {
            return null;
        }
        return userInfoThreadLocal.get().getUserId();
    }

    private static boolean checkUserEmpty() {
        return userInfoThreadLocal.get() == null;
    }

}
