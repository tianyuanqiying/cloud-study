package com.cloud.study.springsecurityjwtrestfullogin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.cloud.study.springsecurityjwtrestfullogin.dao")
@SpringBootApplication
public class SpringSecurityJwtRestfulLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityJwtRestfulLoginApplication.class, args);
	}

}
