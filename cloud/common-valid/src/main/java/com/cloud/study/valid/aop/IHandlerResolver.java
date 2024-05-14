package com.cloud.study.valid.aop;

import com.cloud.study.valid.base.Validation;
import com.cloud.study.valid.pojo.BizInfo;
import com.cloud.study.valid.pojo.ValidationInfo;

import java.lang.reflect.Parameter;

public interface IHandlerResolver {

    /**
     * 字段验证入口
     * @param validation
     */
    void resolveFiled(ValidationInfo validation);

    /**
     * 业务验证入口
     * @param parameter
     */
    void resolveBiz(BizInfo parameter);
}
