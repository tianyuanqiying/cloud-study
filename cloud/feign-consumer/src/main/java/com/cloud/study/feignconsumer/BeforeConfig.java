package com.cloud.study.feignconsumer;

import org.springframework.context.annotation.Configuration;

@Configuration
public class BeforeConfig {
    public BeforeConfig() {
        System.out.println("before");
    }
}
