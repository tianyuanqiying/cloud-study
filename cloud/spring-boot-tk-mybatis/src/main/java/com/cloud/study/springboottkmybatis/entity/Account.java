package com.cloud.study.springboottkmybatis.entity;


import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @author Fox
 */
@Data
@Table(name = "account_tbl")
public class Account {
    @Id
    private Integer id;
    
    private String userId;
    
    private Integer money;

    private Integer bizId;

    private Integer uniqueId;

    private Integer version;
}
