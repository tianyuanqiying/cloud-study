package com.cloud.study.springbootmybatis.common;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author user
 */
@Configuration
@MapperScan("com.cloud.study.*.mapper")
public class CommonConfig {
    @Bean
    public SqlInjector sqlInjector() {
        return new SqlInjector();
    }


}
