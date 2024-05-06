package com.cloud.study.feignprovider;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.thread.ThreadUtil;
import com.alibaba.nacos.common.utils.CollectionUtils;
import com.sun.xml.internal.ws.util.CompletedFuture;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;

//@Component
@RefreshScope
public class NacosConfig implements InitializingBean {
    @Value("${name}")
    private String name;

    @Override
    public void afterPropertiesSet() throws Exception {
        ThreadUtil.execute(() -> {
            while (true) {
                //无法刷新，因为只初始化一次，使用的是属性填充的值；
                System.out.println(name);
                ThreadUtil.sleep(10000);
            }
        });
    }

    public static void main(String[] args) {
        ArrayList<String> strings = CollectionUtil.newArrayList("1", "2", "3");
        ArrayList<String> list = CollectionUtil.newArrayList("3", "4", "5");
        Collection intersection = org.apache.commons.collections.CollectionUtils.intersection(strings, list);
        System.out.println(intersection);
    }
}
