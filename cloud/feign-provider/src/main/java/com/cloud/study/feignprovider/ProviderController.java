package com.cloud.study.feignprovider;

import cn.hutool.core.thread.ThreadUtil;
import com.cloud.study.common.R;
import com.cloud.study.common.UserKit;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author user
 */
@RestController
@RequestMapping("/provider")
public class ProviderController{

    /**
     * 测试远程调用
     * @param id
     * @return
     */
    @GetMapping("/findById")
    public R findById(@RequestParam("id") Long id) {
        System.out.println("context user id :" + UserKit.getUserId());
        System.out.println("context user name : " + UserKit.getUserName());
        R r = new R();
        HashMap<Object, Object> hashMap = new HashMap();
        hashMap.put("id", id);
        hashMap.put("name", "provider");
        r.put("user", hashMap);
        return r;
    }

    /**
     * 测试远程超时时间
     * @param name
     * @return
     */
    @GetMapping("/findByName")
    public R findByName(@RequestParam("name") String name) {
        System.out.println("context user id :" + UserKit.getUserId());
        System.out.println("context user name : " + UserKit.getUserName());
        R r = new R();
        HashMap<Object, Object> hashMap = new HashMap();
        hashMap.put("name", name);
        hashMap.put("name", "provider");
        r.put("user", hashMap);

        //睡眠6s, 超时时间是3s
        ThreadUtil.sleep(6000);
        return r;
    }


}
