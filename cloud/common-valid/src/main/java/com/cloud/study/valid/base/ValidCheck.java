package com.cloud.study.valid.base;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Documented
public @interface ValidCheck {
}
