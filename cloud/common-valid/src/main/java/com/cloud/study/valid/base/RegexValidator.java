package com.cloud.study.valid.base;

/**
 * 正则校验
 * @author user
 */
public interface RegexValidator extends Validator {
    /**
     * 获取校验的正则表达式
     * @return
     */
    String getRegex();
}
