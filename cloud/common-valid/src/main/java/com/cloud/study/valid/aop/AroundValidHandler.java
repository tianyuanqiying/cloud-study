package com.cloud.study.valid.aop;

import com.cloud.study.valid.base.IBizValid;
import com.cloud.study.valid.base.ValidCheck;
import com.cloud.study.valid.base.Validation;
import com.cloud.study.valid.builder.BizInfoBuilder;
import com.cloud.study.valid.builder.ValidationInfoBuilder;
import com.cloud.study.valid.pojo.BizInfo;
import com.cloud.study.valid.pojo.ValidationInfo;
import com.cloud.study.valid.util.ReflectUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Field;
import java.lang.reflect.Parameter;

/**
 * 处理AOP校验逻辑入口
 * @author user
 */
public class AroundValidHandler {
    private ProceedingJoinPoint joinPoint;

    private CompositeHandler compositeHandler;

    public AroundValidHandler(ProceedingJoinPoint joinPoint, CompositeHandler compositeHandler) {
        this.joinPoint = joinPoint;
        this.compositeHandler = compositeHandler;
    }


    public void handle() {
        Object[] args = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        Parameter[] parameters = ReflectUtil.getParameters(signature.getMethod());

        for (int index = 0; index < args.length; index++) {
            Object paramVal = args[index];
            Parameter parameter = parameters[index];
            //判断方法参数上是否存在@ValidCheck注解
            if (!ReflectUtil.isAnnotationPresent(parameter, ValidCheck.class)) {
                continue;
            }

            //校验带有@Validation注解的字段值；
            Field[] fields = ReflectUtil.getDeclaredFields(paramVal.getClass());
            for (Field declaredField : fields) {
                if (!ReflectUtil.isAnnotationPresent(declaredField, Validation.class)) {
                    continue;
                }

                Validation annotation = declaredField.getAnnotation(Validation.class);
                ValidationInfoBuilder validationInfoBuilder = new ValidationInfoBuilder(annotation);
                ValidationInfo validationInfo = validationInfoBuilder.paramVal(paramVal).belongClazz(paramVal.getClass()).field(declaredField).initAndBuild();

                //校验
                compositeHandler.resolveFiled(validationInfo);
            }

            //校验业务数据、是否实现了IBizValid接口
            BizInfo bizInfo = new BizInfoBuilder(parameter).parameterValue(paramVal).build();
            compositeHandler.resolveBiz(bizInfo);
        }

    }
}
