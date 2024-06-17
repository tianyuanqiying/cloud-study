package com.cloud.study.valid.base;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Documented
@Constraint(validatedBy = ValidCheckHandler.class)
public @interface ValidCheck {
}
