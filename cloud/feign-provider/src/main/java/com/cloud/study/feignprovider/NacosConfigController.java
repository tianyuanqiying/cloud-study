//package com.cloud.study.feignprovider;
//
//import cn.hutool.core.thread.ThreadUtil;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RefreshScope
//public class NacosConfigController  implements InitializingBean {
//    @Value("${name}")
//    private String name;
//
//    @GetMapping("/getName")
//    public String getName(){
//        System.out.println("Get Name : " + name);
//        return name;
//    }
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        ThreadUtil.execute(() -> {
//            while (true) {
//                //无法刷新，因为只初始化一次，使用的是属性填充的值；
//                System.out.println("controller name = " + name);
//                ThreadUtil.sleep(10000);
//            }
//        });
//    }
//}
