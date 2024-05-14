package com.cloud.study.valid.rule;


import com.cloud.study.valid.base.RegexValidator;

/**
 * 通讯地址
 * @author user
 */
public class Addr implements RegexValidator {
    @Override
    public String getRegex() {
        return "^.{1,256}$";
    }

    @Override
    public String getMessage() {
        return "通讯地址最长256个字符";
    }

    @Override
    public String getErrorCode() {
        return null;
    }
}
