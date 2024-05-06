package com.cloud.study.springbootmybatis.common;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;

import java.util.List;

/**
 * SQL扩展方法注入器
 * @author user
 */

public class SqlInjector extends DefaultSqlInjector {
    public SqlInjector() {
        System.out.println("init sql injector");
    }

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        System.out.println("execute sql inject");
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
        methodList.add(new SelectByBizId());
        methodList.add(new SelectByBizIdAndUniqueId());
        return methodList;
    }
}
