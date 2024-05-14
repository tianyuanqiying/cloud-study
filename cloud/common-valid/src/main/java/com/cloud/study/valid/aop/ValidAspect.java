package com.cloud.study.valid.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 校验AOP
 * @author user
 */
@Aspect
@Component
public class ValidAspect {

    @Autowired
    CompositeHandler compositeHandler;

    @Pointcut("execution(* *.*(.., @com.cloud.study.valid.base.ValidCheck (*), ..))")
    private void pointCut() {
    }

    @Around("pointCut()")
    public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取方法参数
        AroundValidHandler aroundValidHandler = new AroundValidHandler(joinPoint, compositeHandler);
        aroundValidHandler.handle();

        // 继续执行方法
        return joinPoint.proceed();
    }

}
