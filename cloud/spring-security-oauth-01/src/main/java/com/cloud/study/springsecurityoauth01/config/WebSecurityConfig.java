package com.cloud.study.springsecurityoauth01.config;

import com.cloud.study.springsecurityoauth01.handle.CustomAccessDeniedHandler;
import com.cloud.study.springsecurityoauth01.handle.CustomAuthenticationFailureHandler;
import com.cloud.study.springsecurityoauth01.handle.CustomHeaderWriter;
import com.cloud.study.springsecurityoauth01.handle.CustomInvalidSessionStratey;
import com.cloud.study.springsecurityoauth01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.InvalidSessionStrategy;

/**
 * spring security配置
 *
 * @author user
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().permitAll();

        http.authorizeRequests()
                .antMatchers("/oauth/**").permitAll()
                .antMatchers("/order/**").permitAll()
                .anyRequest().authenticated();

        //访问拒绝处理器
        http.exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler()).accessDeniedPage("/403.html");

        //设置退出请求都可访问
        http.logout().permitAll();

        //会话非法、且最大会话数为1，则跳转到登录页面
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).invalidSessionStrategy(new CustomInvalidSessionStratey()).maximumSessions(1)
                .and().sessionAuthenticationFailureHandler(new CustomAuthenticationFailureHandler());


        //设置请求头
        http.headers().addHeaderWriter(new CustomHeaderWriter());

        //记住我
        http.rememberMe().alwaysRemember(true).userDetailsService(userDetailsService);


        //禁用csrf
        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }
}
