package com.cloud.study.feignprovider;

import cn.hutool.core.thread.ThreadUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.cloud.study.*")
@SpringBootApplication
@RefreshScope
public class FeignProviderApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(FeignProviderApplication.class, args);
	}

}
