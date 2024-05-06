package com.cloud.study.valid.base;

/**
 * 校验注解
 * @author user
 */
public @interface Validation {
    Class<? extends Validator> rule();
}
