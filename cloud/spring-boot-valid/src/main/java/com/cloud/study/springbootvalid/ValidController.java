package com.cloud.study.springbootvalid;

import com.cloud.study.valid.base.ValidCheck;
import com.cloud.study.valid.base.Validation;
import com.cloud.study.valid.rule.Addr;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author user
 */
@RestController
public class ValidController{
    @GetMapping("/valid")
    public Object valid(@ValidCheck ParamModel paramModel) {
        return paramModel;
    }
}
class ParamModel {
    @Validation(rule = Addr.class)
    private String addr;

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}