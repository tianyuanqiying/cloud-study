package com.cloud.study.valid.builder;

import com.cloud.study.valid.pojo.BizInfo;

import java.lang.reflect.Parameter;

/**
 * BizInfo对象构造器
 * @author user
 */
public class BizInfoBuilder {
    private Parameter parameter;
    private Class parameterType;
    private Object parameterValue;

    public BizInfoBuilder() {
    }

    public BizInfoBuilder(Parameter parameter) {
        this.parameter = parameter;
        this.parameterType = parameter.getType();
    }

    public BizInfoBuilder parameterValue(Object parameterValue) {
        this.parameterValue = parameterValue;
        return this;
    }

    public BizInfoBuilder parameterType(Class parameterType) {
        this.parameterType = parameterType;
        return this;
    }

    public BizInfoBuilder parameter(Parameter parameter) {
        this.parameter = parameter;
        return this;
    }

    public BizInfo build() {
        BizInfo bizInfo = new BizInfo();
        bizInfo.setParameter(parameter);
        bizInfo.setParameterType(parameterType);
        bizInfo.setParameterValue(parameterValue);
        return bizInfo;
    }
}
