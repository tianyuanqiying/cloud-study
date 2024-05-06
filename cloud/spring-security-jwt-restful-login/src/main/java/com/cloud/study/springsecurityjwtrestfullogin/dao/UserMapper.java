package com.cloud.study.springsecurityjwtrestfullogin.dao;

import com.cloud.study.springsecurityjwtrestfullogin.pojo.SysUser;
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
