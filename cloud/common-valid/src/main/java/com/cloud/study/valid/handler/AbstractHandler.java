package com.cloud.study.valid.handler;

import com.cloud.study.valid.base.IValidHandler;
import com.cloud.study.valid.pojo.ValidationInfo;

import java.util.HashSet;
import java.util.Set;

/**
 * 字段值校验的公共父类
 * @author user
 */
public abstract class AbstractHandler implements IValidHandler {
    protected Set<Class> supportTypes = new HashSet<>();
}
