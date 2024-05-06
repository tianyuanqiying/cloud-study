package com.cloud.study.springbootmybatis.common;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;


public class SelectByBizIdAndUniqueId extends AbstractMethod {
    private String TABLE = "${table}";
    private String sql = "SELECT * FROM " + TABLE + " WHERE biz_id = #{bizId} AND unique_id = #{uniqueId}";

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        System.out.println("execute inject mapped statement");

        String formatSql = sql.replace(TABLE, tableInfo.getTableName());
        SqlSource sqlSource = super.languageDriver.createSqlSource(configuration, formatSql, modelClass);

        return super.addSelectMappedStatementForTable(mapperClass, "selectByBizIdAndUniqueId", sqlSource, tableInfo);
    }
}
