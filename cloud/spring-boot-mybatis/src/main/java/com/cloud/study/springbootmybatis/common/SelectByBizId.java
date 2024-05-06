package com.cloud.study.springbootmybatis.common;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;


public class SelectByBizId extends AbstractMethod {
    private String TABLE = "${table}";
    private String SQL = "SELECT * FROM " + TABLE + " WHERE id = #{biz_id}";
    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        System.out.println("execute inject mapped statement");
        //String sql = SQL.replace(TABLE, tableInfo.getTableName()).toString();
        String sql = "SELECT %s FROM %s WHERE %s=#{%s} %s";

//        SqlSource sqlSource = super.languageDriver.createSqlSource(super.configuration, sql, modelClass);
        String format = String.format(sql,
                sqlSelectColumns(tableInfo, false),
                tableInfo.getTableName(), tableInfo.getKeyColumn(), tableInfo.getKeyProperty(), tableInfo.getLogicDeleteSql(true, true));

        SqlSource sqlSource = super.languageDriver.createSqlSource(configuration, format, modelClass);

        return super.addSelectMappedStatementForTable(mapperClass, "selectByBizId", sqlSource, tableInfo);
    }
}
