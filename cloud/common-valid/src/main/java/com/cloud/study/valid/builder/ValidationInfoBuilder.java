package com.cloud.study.valid.builder;

import com.cloud.study.valid.base.Validation;
import com.cloud.study.valid.pojo.ValidationInfo;
import com.cloud.study.valid.util.ReflectUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;

public class ValidationInfoBuilder {
    private Validation validation;

    /**
     * @ValidCheck 注解修饰的方法参数类型
     */
    private Class belongClazz;

    /**
     * @ValidCheck 注解修饰的方法参数值；
     */
    private Object belongClazzValue;

    private Field declaredField;
    private String fieldName;
    private Object fieldValue;
    private String getterMethodName;
    private Method getterMethod;
    public ValidationInfoBuilder() {
    }

    public ValidationInfoBuilder(Validation annotation) {
        this.validation = annotation;
    }



    private ValidationInfoBuilder getterMethod(String getterMethodName){
        Method method = ReflectUtil.getDeclaredMethod(getterMethodName, this.belongClazz);
        if (Objects.isNull(method)) {
            System.out.println(declaredField.getName() + "cannot find method, method Name" + getterMethodName);
            return this;
        }
        this.getterMethod = method;
        return this;
    }

    private String buildGetterMethodName(String fieldName) {
        StringBuilder methodNameBuilder = new StringBuilder();
        methodNameBuilder.append("get");
        methodNameBuilder.append(Character.toUpperCase(fieldName.charAt(0)));
        methodNameBuilder.append(fieldName.substring(1));
        return methodNameBuilder.toString();
    }

    public ValidationInfoBuilder belongClazz(Class<?> belongClazz) {
        this.belongClazz = belongClazz;
        return this;
    }

    public ValidationInfoBuilder field(Field declaredField) {
        this.declaredField = declaredField;
        this.fieldName = declaredField.getName();
        return this;
    }

    public ValidationInfoBuilder getterMethodName(String getterMethodName) {
        this.getterMethodName = getterMethodName;
        return this;
    }


    public ValidationInfo initAndBuild() {
        this.init();

        return this.build();
    }

    public ValidationInfoBuilder init() {
        this.initGetterMethod();
        this.initFieldValue();
        return this;
    }

    public ValidationInfo build() {
        ValidationInfo validationInfo = new ValidationInfo();
        validationInfo.setValidation(validation);

        validationInfo.setField(declaredField);
        validationInfo.setFieldName(fieldName);
        validationInfo.setFieldValue(fieldValue);

        validationInfo.setBelongClazz(belongClazz);
        validationInfo.setBelongClazzValue(belongClazzValue);

        validationInfo.setGetterMethodName(getterMethodName);
        validationInfo.setMethod(getterMethod);
        return validationInfo;
    }

    private ValidationInfoBuilder initGetterMethod() {
        if (belongClazz == null) {
            if (belongClazzValue == null) {
                throw new RuntimeException("please call belongClazz(Class<?> belongClazz) method to set belongClazz field");
            }
            this.belongClazz(belongClazzValue.getClass());
        }

        if (declaredField == null) {
            throw new RuntimeException("please call field(Field declaredField) method to set field");
        }

        this.getterMethodName(buildGetterMethodName(this.fieldName));
        this.getterMethod(this.getterMethodName);
        return this;
    }

    private ValidationInfoBuilder initFieldValue() {
        //设置方法可访问
        this.getterMethod.setAccessible(true);
        //反射调用getter方法；
        this.fieldValue = ReflectUtil.invokeMethod(this.getterMethod, belongClazzValue);
        return this;
    }

    public ValidationInfoBuilder paramVal(Object belongClazzValue) {
        this.belongClazzValue = belongClazzValue;
        return this;
    }
}
