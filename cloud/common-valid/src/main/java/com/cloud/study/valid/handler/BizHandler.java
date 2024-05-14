package com.cloud.study.valid.handler;

import com.cloud.study.valid.base.IBizValid;
import com.cloud.study.valid.base.IValidHandler;
import com.cloud.study.valid.exception.ValidException;
import com.cloud.study.valid.pojo.BaseDTO;
import com.cloud.study.valid.pojo.BizInfo;
import com.cloud.study.valid.util.ReflectUtil;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.*;

/**
 * 负责调用IBizValid#businessValidate方法
 * @author user
 */
@Component
public class BizHandler extends AbstractHandler {
    {
        supportTypes.add(BizInfo.class);
    }

    @Override
    public Boolean support(BaseDTO baseDTO) {
        BizInfo bizInfo = (BizInfo) baseDTO;
        return IBizValid.class.isAssignableFrom(bizInfo.getParameterType());
    }

    @Override
    public Boolean handle(BaseDTO baseDTO) {
        BizInfo bizInfo = (BizInfo) baseDTO;
        Class parameterType = bizInfo.getParameterType();
        Method method = ReflectUtil.getMethod(parameterType, "businessValidate", Map.class);
        Map<String, Object> errorInfo = new HashMap<>();
        ReflectUtil.invokeMethod(method, bizInfo.getParameterValue(), errorInfo);
        if (!errorInfo.isEmpty() && errorInfo.get("message") != null) {
            Object message = errorInfo.get("message");
            throw new ValidException(message.toString());
        }
        return true;
    }

    @Override
    public Boolean typeMatch(Class clazz) {
        return supportTypes.contains(clazz);
    }
}
