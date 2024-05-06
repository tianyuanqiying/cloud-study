package com.cloud.study.feignconsumer;

import org.springframework.context.annotation.Configuration;

@Configuration
public class AfterConfig {
    public AfterConfig() {
        System.out.println("after");
    }
}
