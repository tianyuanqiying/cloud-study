package com.cloud.study.feign;

import com.cloud.study.common.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;

/**
 * @author user
 */
@FeignClient(name = "feign-provider", path = "/provider")
public interface ProviderFeignService {

    /**
     * 根据ID查询
     * * @param id
     * @return
     */
    @GetMapping("/findById")
    public R findById(@RequestParam(name = "id") Long id);

    /**
     * 测试远程超时时间
     * @param name
     * @return
     */
    @GetMapping("/findByName")
    public R findByName(@RequestParam("name") String name);
}
