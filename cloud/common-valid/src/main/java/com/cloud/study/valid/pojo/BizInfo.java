package com.cloud.study.valid.pojo;

import java.lang.reflect.Parameter;

/**
 * 业务验证DTO
 * @author user
 */
public class BizInfo implements BaseDTO {
    private Parameter parameter;
    private Class parameterType;
    private Object parameterValue;

    public Parameter getParameter() {
        return parameter;
    }

    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
    }

    public Class getParameterType() {
        return parameterType;
    }

    public void setParameterType(Class parameterType) {
        this.parameterType = parameterType;
    }

    public Object getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(Object parameterValue) {
        this.parameterValue = parameterValue;
    }

}
