package com.cloud.study.common;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author user
 */
public class ContextKit {
    private static ConcurrentHashMap<String, String> context = new ConcurrentHashMap<>();

    public static void set(String key, String val) {
        context.put(key, val);
    }

    public static String get(String key) {
        return context.get(key);
    }
}
