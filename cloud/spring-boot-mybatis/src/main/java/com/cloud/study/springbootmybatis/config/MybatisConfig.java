package com.cloud.study.springbootmybatis.config;


import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author Fox
 *
 * 需要用到分布式事务的微服务都需要使用seata DataSourceProxy代理自己的数据源
 */
@Configuration
@MapperScan("com.cloud.study.*.mapper")
public class MybatisConfig {
    @Autowired
    BeanFactory beanFactory;

    /**
     * 从配置文件获取属性构造datasource，注意前缀，这里用的是druid，根据自己情况配置,
     * 原生datasource前缀取"spring.datasource"
     *
     * @return
     */

//    @Primary
//    @Bean("db1SqlSessionFactory")
//    public SqlSessionFactory db1SqlSessionFactory(DataSource dataSource) throws Exception {
//        /**
//         * 使用 mybatis plus 配置
//         */
//        MybatisSqlSessionFactoryBean b1 = new MybatisSqlSessionFactoryBean();
//        System.out.println("dataSourceLyz"+dataSource.toString());
//        b1.setDataSource(dataSource);
//        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        b1.setMapperLocations(resolver.getResources("classpath*:mybatis/**/*-mapper.xml"));
//        return b1.getObject();
//    }


}
