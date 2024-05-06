package com.cloud.study.common;

import cn.hutool.core.codec.Base64;
import cn.hutool.json.JSONUtil;
import com.cloud.study.dto.UserInfo;
import feign.RequestInterceptor;
import feign.RequestTemplate;

import java.util.Random;

/**
 * Feign 拦截器
 * @author user
 */
public class FeignInterceptor implements RequestInterceptor{
    @Override
    public void apply(RequestTemplate requestTemplate) {
        //从上下文中获取登录的用户信息
        UserInfo userInfo = new UserInfo();
        userInfo.setName("zhengqinhao");
        userInfo.setUserId("12138");
        requestTemplate.header("user", Base64.encode(JSONUtil.toJsonStr(userInfo)));
    }
}
