package com.cloud.study.springboottkmybatis.common;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;

import java.util.List;

/**
 * @author user
 */
@RegisterMapper
public interface CommonSelectMapper<T> {
    /**
     * 根据业务ID获取数据
     * @param bizId
     * @return
     */
    @SelectProvider(type = CommonSelectProvider.class, method = "dynamicSQL")
    List<T> selectByBizId(Object bizId);

    /**
     * 根据业务ID和uniqueid获取数据
     * @param bizId
     * @param uniqueId
     * @return
     */
    @SelectProvider(type = CommonSelectProvider.class, method = "dynamicSQL")
    T selectByBizIdAndUniqueId(@Param("bizId") Object bizId, @Param("uniqueId") Object uniqueId);


    /**
     * 根据业务ID和版本号查询数据
     * @param bizId
     * @param version
     * @return
     */
    @SelectProvider(type = CommonSelectProvider.class, method = "dynamicSQL")
    T selectByBizIdAndVersion(@Param("bizId") Object bizId, @Param("version") Object version);

    /**
     * 根据主键查询数据
     * @param id
     * @return
     */
    @SelectProvider(type = CommonSelectProvider.class, method = "dynamicSQL")
    T selectByPrimaryKey(Object id);
}
