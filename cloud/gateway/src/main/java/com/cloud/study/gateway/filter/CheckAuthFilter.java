package com.cloud.study.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author Fox
 */
@Slf4j
@Order(-1)
@Component
public class CheckAuthFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //特殊处理：有些接口不需要token就能访问，需要放行。 匿名接口；

        //校验请求头中的token
        List<String> token = exchange.getRequest().getHeaders().get("token");
        log.info("token:"+ token);
        if (token == null || token.isEmpty()){
            //为空，则抛出异常；
            return chain.filter(exchange);
        }

        //不为空、调用认证服务进行校验token是否合法，并拿到用户信息；

        //用户信息转换成JSON字符串、再Base64放入请求头, 然后放行， 下游服务需要实现拦截器、将authInfo解析为用户信息；
        exchange.getRequest().getHeaders().add("authInfo", "{}");
        // TODO token校验
        return chain.filter(exchange);
    }
}
