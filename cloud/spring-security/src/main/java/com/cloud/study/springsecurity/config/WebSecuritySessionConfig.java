package com.cloud.study.springsecurity.config;

import com.cloud.study.springsecurity.handler.DownSessionInformationExpiredStrategy;
import com.cloud.study.springsecurity.service.UserServiceInDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * @author user
 */
//@Configuration
public class WebSecuritySessionConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    UserServiceInDB userServiceInDB;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServiceInDB);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //自定义登录页面、登录成功，登录失败； /user/index必须是POST请求
        http.formLogin().loginPage("/login.html").loginProcessingUrl("/user/login")
                .successForwardUrl("/user/index") //重定向到index.html, 因此登录成功，无需放行
                .failureForwardUrl("/user/toerror"); //重定向到error.html, 因此登录失败，需要放行/error.html，否则因为没有权限，再次调到登录页面

        //默认情况下，Spring Security会为每个登录成功的用户会新建一个Session，就是ifRequired 。
        // 在执行认证过程之前，spring security将运行SecurityContextPersistenceFilter过滤器负责存储安全请求上下文，上下文根据策略进行存储，
        // 默认为HttpSessionSecurityContextRepository ，其使用http session作为存储器
        http.sessionManagement().invalidSessionUrl("/session/invalid").maximumSessions(1).expiredSessionStrategy(new DownSessionInformationExpiredStrategy());

        //登录的URL放行
        http.authorizeRequests().antMatchers("/user/login","/login.html","/error.html","/session/invalid").permitAll();

        //非登录的URL需要认证
        http.authorizeRequests().anyRequest().authenticated();

        http.csrf().disable();
    }
}
