package com.cloud.study.feignconsumer;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Configuration;
@AutoConfigureBefore(BeforeConfig.class)
@AutoConfigureAfter(AfterConfig.class)
@Configuration
public class MidConfig {
    public MidConfig() {
        System.out.println("current");
    }
}
