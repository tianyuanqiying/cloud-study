package com.cloud.study.springbootvalid;

import com.cloud.study.valid.base.ValidCheck;
import com.cloud.study.valid.base.Validation;
import com.cloud.study.valid.pojo.AbstractValidDTO;
import com.cloud.study.valid.rule.Addr;
import com.cloud.study.valid.rule.Sex;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;

@RestController
public class ValidController {
    public ValidController() {
        System.out.println("ValidController");
    }

    @GetMapping("/valid")
    public Object valid(@ValidCheck ParamModel paramModel) {
        return paramModel;
    }
}

class ParamModel extends AbstractValidDTO {
    @Validation(rule = Addr.class)
    private String addr;

    @Validation(rule = Sex.class)
    private Integer sex;

    private Integer age;

    private Integer workAge;
    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getWorkAge() {
        return workAge;
    }

    public void setWorkAge(Integer workAge) {
        this.workAge = workAge;
    }

    @Override
    public void businessValidate(Map<String, Object> errorInfo) {
        if (Objects.nonNull(age) && age < workAge) {
            errorInfo.put("message", "age must be bigger then workAge, please check age and workAge");
        }
    }
}
