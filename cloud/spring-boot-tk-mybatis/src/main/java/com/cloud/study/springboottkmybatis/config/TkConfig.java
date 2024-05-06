package com.cloud.study.springboottkmybatis.config;

import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author user
 */
@Configuration
@MapperScan("com.cloud.study.*.mapper")
public class TkConfig {

}

