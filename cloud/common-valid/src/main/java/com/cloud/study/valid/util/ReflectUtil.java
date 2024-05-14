package com.cloud.study.valid.util;

import com.cloud.study.valid.base.ValidCheck;
import com.cloud.study.valid.base.Validator;

import java.lang.reflect.*;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class ReflectUtil {
    private static final Field[] EMPTY_FIELD_ARRAY = new Field[0];
    private static final Parameter[] EMPTY_PARAMETER_ARRAY = new Parameter[0];
    private static final Map<Class, Field[]> fieldsCacheMap = new ConcurrentHashMap<>();
    private static final Map<Method, Parameter[]> methodParameterCacheMap = new ConcurrentHashMap<>();

    /**
     * 反射工具类
     * @param rule
     * @param <T>
     * @return
     */
    public static <T> T newInstance(Class<T> rule) {
        try {
            T instance = rule.newInstance();
            return instance;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Method getDeclaredMethod(String getterMethodName, Class belongClazz, Class<?>... parameterTypes) {
        try {
            Method method = belongClazz.getDeclaredMethod(getterMethodName, parameterTypes);
            return method;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object invokeMethod(Method method, Object obj, Object... args) {
        try {
            Object returnVal = method.invoke(obj, args);
            return returnVal;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 判断类上是否存在注解
     * @param clazz 类
     * @param annoation 注解
     * @return
     */
    public static boolean isAnnotationPresent(AnnotatedElement clazz, Class annoation) {
        return clazz.isAnnotationPresent(annoation);
    }

    /**
     * 获取类声明的属性
     * @param clazz 类
     * @return
     */
    public static Field[] getDeclaredFields(Class<?> clazz) {
        Field[] fields = fieldsCacheMap.get(clazz);
        if (fields != null) {
            return fields;
        }

        try {
            fields = clazz.getDeclaredFields();
            fieldsCacheMap.put(clazz, fields.length == 0 ? EMPTY_FIELD_ARRAY : fields);
        }catch (Exception ex) {
            throw new IllegalStateException("Failed to introspect Class [" + clazz.getName() +
                    "] from ClassLoader [" + clazz.getClassLoader() + "]", ex);
        }
        return fields;
    }

    /**
     * 获取方法参数
     * @param method 方法
     * @return 参数类型数组
     */
    public static Parameter[] getParameters(Method method) {
        if (Objects.isNull(method)) {
            return EMPTY_PARAMETER_ARRAY;
        }

        Parameter[] parameters = methodParameterCacheMap.get(method);
        if (parameters != null) {
            return parameters;
        }
        Parameter[] methodParameters = method.getParameters();
        methodParameterCacheMap.put(method, methodParameters.length == 0 ? EMPTY_PARAMETER_ARRAY : methodParameters);
        return methodParameters;
    }

    /**
     *
     * 获取业务校验方法
     * @param clazz
     * @param methodName
     * @param parameterTypes
     * @return
     */
    public static Method getMethod(Class clazz, String methodName, Class<?>... parameterTypes) {
        try {
            Method method = clazz.getMethod(methodName, parameterTypes);
            return method;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
