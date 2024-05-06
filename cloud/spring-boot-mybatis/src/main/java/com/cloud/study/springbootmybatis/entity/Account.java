package com.cloud.study.springbootmybatis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * @author Fox
 */
@Data
@TableName("account_tbl")
public class Account {
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;
    
    private String userId;
    
    private Integer money;
}
