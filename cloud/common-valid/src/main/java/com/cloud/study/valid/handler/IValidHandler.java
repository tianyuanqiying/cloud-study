package com.cloud.study.valid.handler;

import com.cloud.study.valid.base.Validator;

public interface IValidHandler {
    Boolean support(Validator validator);
    Boolean handle(Validator validator);
}
