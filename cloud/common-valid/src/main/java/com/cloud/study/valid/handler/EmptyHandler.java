package com.cloud.study.valid.handler;

import com.cloud.study.valid.base.Validation;
import com.cloud.study.valid.exception.ValidException;
import com.cloud.study.valid.pojo.BaseDTO;
import com.cloud.study.valid.pojo.ValidationInfo;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 字段值的空处理
 * @author user
 */
@Order(Integer.MIN_VALUE)
@Component
public class EmptyHandler extends AbstractFieldHandler {

    private static String FIELD_NAME = "#{FIELD_NAME}";

    private static String MESSAGE = "field name : [" + FIELD_NAME + "] is not allows empty";

    @Override
    public Boolean support(BaseDTO baseDTO) {
        return Boolean.TRUE;
    }

    @Override
    public Boolean handle(BaseDTO baseDTO) {
        ValidationInfo validationInfo = (ValidationInfo) baseDTO;

        Validation validation = validationInfo.getValidation();
        if (!validation.allowEmpty() && Objects.isNull(validationInfo.getFieldValue())) {
            String message = MESSAGE.replace(FIELD_NAME, validationInfo.getFieldName());
            throw new ValidException(message);
        }
        return true;
    }

    @Override
    public Boolean typeMatch(Class clazz) {
        return supportTypes.contains(clazz);
    }
}
