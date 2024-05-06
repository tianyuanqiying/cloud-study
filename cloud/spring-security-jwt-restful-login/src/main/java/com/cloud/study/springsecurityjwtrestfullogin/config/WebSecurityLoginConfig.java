package com.cloud.study.springsecurityjwtrestfullogin.config;

import com.cloud.study.springsecurityjwtrestfullogin.security.AppAccessDeniedHandler;
import com.cloud.study.springsecurityjwtrestfullogin.security.JwtAuthenticationEntryPoint;
import com.cloud.study.springsecurityjwtrestfullogin.security.JwtTokenManager;
import com.cloud.study.springsecurityjwtrestfullogin.security.ParseJwtAuthTokenFilter;
import com.cloud.study.springsecurityjwtrestfullogin.service.UserServiceInDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author user
 */
@Configuration
public class WebSecurityLoginConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    UserServiceInDB userServiceInDB;
    @Autowired
    JwtTokenManager jwtTokenManager;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServiceInDB);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

//    @Override
//    protected AuthenticationManager authenticationManager() throws Exception {
//        return super.authenticationManager();
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //自定义登录页面、登录成功，登录失败； /user/index必须是POST请求
//        http.formLogin().loginPage("/login.html").loginProcessingUrl("/user/login")
//                .successForwardUrl("/user/index") //重定向到index.html, 因此登录成功，无需放行
//                .failureForwardUrl("/user/toerror"); //重定向到error.html, 因此登录失败，需要放行/error.html，否则因为没有权限，再次调到登录页面

//        http.formLogin().permitAll();

        //登录的URL放行
//        http.authorizeRequests().antMatchers("/user/login","/login.html","/error.html").permitAll();
//        http.sessionManagement().invalidSessionStrategy(new EmptyInvalidSessionStrategy());

        //放开登录请求的权限
        http.authorizeRequests().antMatchers("/auth/login").permitAll();

        //非登录的URL需要认证
        http.authorizeRequests().anyRequest().access("@accessSecurityExpressionOperations.hasPermission(request)");

        //解析token的请求ParseJwtAuthTokenFilter执行顺序在UsernamePasswordAuthenticationFilter前面
        http.addFilterBefore(new ParseJwtAuthTokenFilter(jwtTokenManager), UsernamePasswordAuthenticationFilter.class);

        //rest方式下、会话策略为stateless
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        //出现异常的处理方式
        http.exceptionHandling().accessDeniedHandler(new AppAccessDeniedHandler()).authenticationEntryPoint(new JwtAuthenticationEntryPoint());

        //禁用csrf
        http.csrf().disable();
    }
}
