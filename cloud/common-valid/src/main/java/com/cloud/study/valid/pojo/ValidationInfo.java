package com.cloud.study.valid.pojo;

import com.cloud.study.valid.base.Validation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Validation 代表的所有信息
 * @author user
 */
public class ValidationInfo implements BaseDTO{
    private Validation validation;
    /**
     * @ValidCheck 注解修饰的方法参数类型
     */
    private Class belongClazz;

    /**
     * @ValidCheck 注解修饰的方法参数值；
     */
    private Object belongClazzValue;

    /**
     * @Validation 修饰的类字段
     */
    private Field field;
    /**
     * @Validation 修饰的类字段名称
     */
    private String fieldName;

    /**
     * @Validation 类字段值
     */
    private Object fieldValue;
    /**
     * @Validation 修饰的类字段Getter方法名
     */
    private String getterMethodName;
    /**
     * @Validation 修饰的类字段Getter方法
     */
    private Method method;

    public ValidationInfo() {
    }

    public ValidationInfo(Validation validation) {
        this.validation = validation;
    }

    public ValidationInfo(Validation validation, Field field, String fieldName, String getterMethodName, Method method) {
        this.validation = validation;
        this.field = field;
        this.fieldName = fieldName;
        this.getterMethodName = getterMethodName;
        this.method = method;
    }

    public Validation getValidation() {
        return validation;
    }

    public void setValidation(Validation validation) {
        this.validation = validation;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getGetterMethodName() {
        return getterMethodName;
    }

    public void setGetterMethodName(String getterMethodName) {
        this.getterMethodName = getterMethodName;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Class getBelongClazz() {
        return belongClazz;
    }

    public void setBelongClazz(Class belongClazz) {
        this.belongClazz = belongClazz;
    }

    public Object getBelongClazzValue() {
        return belongClazzValue;
    }

    public void setBelongClazzValue(Object belongClazzValue) {
        this.belongClazzValue = belongClazzValue;
    }

    public Object getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(Object fieldValue) {
        this.fieldValue = fieldValue;
    }
}
