package com.cloud.study.nacos;

import cn.hutool.core.thread.ThreadUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class NacosApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(NacosApplication.class, args);
		while (true) {
			String property = applicationContext.getEnvironment().getProperty("name");
			System.out.println("main env => " + property);
			System.out.println("");
			ThreadUtil.sleep(2000);
		}
	}

}
