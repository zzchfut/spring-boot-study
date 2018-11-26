package com.suncreate.demo.utils;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 *  多数据源
 */
@Deprecated
@Configuration
// @EnableTransactionManagement
@PropertySource(value = {"classpath:source.properties"})
public class DBConfig {

    @Bean(name = "ds1")
    @Primary
    @ConfigurationProperties(prefix="spring.datasource.db1")
    public DataSource oneDataSource() {
        return DataSourceBuilder.create().build();
    }

    /*@Bean(name = "oneJdbcTemplate")
    public JdbcTemplate oneJdbcTemplate(@Qualifier("oneDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }*/

    @Bean(name = "ds2")
    @ConfigurationProperties(prefix="spring.datasource.db2")
    public DataSource twoDataSource() {
        return DataSourceBuilder.create().build();
    }

    /*@Bean(name = "twoJdbcTemplate")
    public JdbcTemplate twoJdbcTemplate(@Qualifier("twoDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }*/
}
