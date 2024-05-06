package com.cloud.study.valid.aop;

import com.cloud.study.valid.base.ValidCheck;
import com.cloud.study.valid.base.Validation;
import com.cloud.study.valid.base.Validator;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * 校验AOP
 * @author user
 */
@Aspect
@Component
public class ValidAspect {
    @Pointcut("@annotation(com.cloud.study.valid.base.ValidCheck)")
    private void pointCut() {
    }

    @Around("pointCut()")
    public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取方法参数
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Parameter[] methodParameters = method.getParameters();
        Object[] args = joinPoint.getArgs();

        for (int index = 0; index < args.length; index++) {
            Object paramVal = args[index];
            Parameter parameter = methodParameters[index];
            Class<?> parameterType = parameter.getType();
            Class<?> paramValClass = paramVal.getClass();
            System.out.println(parameterType.getName());
            System.out.println(paramValClass.getName());
//            if (paramVal != null && paramVal.getClass().isAssignableFrom(ValidCheck.class)) {
//
//            }
        }



        // 继续执行方法
        return joinPoint.proceed();
    }

}
