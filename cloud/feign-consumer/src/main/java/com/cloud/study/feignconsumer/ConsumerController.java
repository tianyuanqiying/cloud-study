package com.cloud.study.feignconsumer;

import com.cloud.study.common.R;
import com.cloud.study.feign.ProviderFeignService;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author user
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerController {
    @Resource
    ProviderFeignService feignService;

    @GetMapping("/getProviderById")
    public R getProviderById(@RequestParam(name = "id") Long id) {
        R service = feignService.findById(id);
        System.out.println(service);
        return service;
    }

    @GetMapping("/getProviderByName")
    public R getProviderByName(@RequestParam(name = "name") String name) {
        R service = feignService.findByName(name);
        System.out.println(service);
        return service;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            long nextLong = RandomUtils.nextLong(0, TimeUnit.SECONDS.toMillis(15L));
            System.out.println(nextLong);
            System.out.println(TimeUnit.MILLISECONDS.toMillis(500L));
        }

    }
}
