package com.cloud.study.springsecurityjwtrestfullogin.security;

import org.springframework.security.core.AuthenticationException;

import javax.servlet.http.HttpServletRequest;

public interface ISecurityExpressionOperations {
    /**
     * 检查用户是否拥有权限
     * @return 是否具有权限
     */
    boolean hasPermission(HttpServletRequest httpServletRequest) throws AuthenticationException;
}
