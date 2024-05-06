package com.cloud.study.config;

import com.cloud.study.common.FeignFilter;
import com.cloud.study.common.FeignInterceptor;
import feign.Logger;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

/**
 * @author user
 */
@Configuration
public class FeignConfig {
    /**
     * feign调用日志显示：
     * FULL：详细日志，内容最齐全、包括URL、参数、返回值、响应时间；适合用于开发测试环境；
     * BASIC： 简略日志， 包括请求方法、URL、请求状态码、响应事件； 适合用于生产环境
     *
     * NONE：不记录日志；
     * HEADER：在BASIC基础上，增加请求头、响应头；
     * @return
     */
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }


    /**
     * 拦截器，用户信息透传；
     * @return
     */
    @Bean
    public FeignInterceptor sessionInterceptor() {
        return new FeignInterceptor();
    }

    /**
     * 注册Feign的拦截器,拦截请求头的用户信息放入上下文；
     * @return
     */
    @Bean
    public FilterRegistrationBean feignFilterBean() {
        FilterRegistrationBean<FeignFilter> feignFilterBean = new FilterRegistrationBean<>();
        feignFilterBean.setFilter(new FeignFilter());
        feignFilterBean.setUrlPatterns(Collections.singletonList("/*"));
        return feignFilterBean;
    }
}
