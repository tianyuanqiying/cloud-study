package com.cloud.study.nacos;

import cn.hutool.core.thread.ThreadUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
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
}
