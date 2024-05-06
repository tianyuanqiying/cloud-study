package com.cloud.study.springsecurity.handler;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author user
 */
public class AppAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        JSONObject respContent = JSONUtil.createObj();
        respContent.set("reason", accessDeniedException.getMessage());
        respContent.set("code", 403);
        respContent.set("data", request.getRequestURI().toString());
        response.getWriter().write(respContent.toString());
    }
}
