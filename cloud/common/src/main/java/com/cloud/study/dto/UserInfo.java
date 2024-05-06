package com.cloud.study.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 用户信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo implements Serializable{
    private String name;
    private String userId;
}
