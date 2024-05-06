package com.cloud.study.springsecurity.dao;

import com.cloud.study.springsecurity.pojo.SysUser;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author Fox
 */
@Repository
public interface UserMapper {

    @Select("select * from tb_user where username=#{username}")
    SysUser getByUsername(String username);
}
