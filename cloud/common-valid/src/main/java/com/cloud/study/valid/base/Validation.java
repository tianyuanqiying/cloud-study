package com.cloud.study.valid.base;

import com.cloud.study.valid.rule.Default;

import java.lang.annotation.*;

/**
 * 校验注解
 * @author user
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Documented
public @interface Validation {
    Class<? extends Validator> rule() default Default.class;

    /**
     * 是否允许为空
     * @return
     */
    boolean allowEmpty() default true;
}
