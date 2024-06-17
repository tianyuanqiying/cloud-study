package com.cloud.study.valid.base;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidCheckHandler implements ConstraintValidator {
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return false;
    }


}
