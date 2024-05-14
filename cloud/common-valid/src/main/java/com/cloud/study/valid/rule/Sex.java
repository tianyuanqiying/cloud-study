package com.cloud.study.valid.rule;

import com.cloud.study.valid.base.DictValidator;
import org.springframework.context.support.ResourceBundleMessageSource;

public class Sex implements DictValidator {
    @Override
    public String getDictName() {
        return "sex";
    }

    @Override
    public String getMessage() {
        return "性别: 0-男； 1-女";
    }

    @Override
    public String getErrorCode() {
        return null;
    }

}
