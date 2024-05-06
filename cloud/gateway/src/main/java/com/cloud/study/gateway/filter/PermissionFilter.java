package com.cloud.study.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 每个角色能访问不同的接口；
 * 判断当前登录用户所属角色是否拥有访问接口的权力；
 * @author user
 */
@Order(-2)
@Slf4j
@Component
public class PermissionFilter implements GlobalFilter{

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //
        return chain.filter(exchange);
    }
}
