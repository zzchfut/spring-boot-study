package com.suncreate.demo.utils;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@PropertySource(value = {"classpath:source.properties"})
public class DataSourceConfig {
    /*@Bean(name="masterDataSource")
    @Primary
    @ConfigurationProperties(prefix="spring.datasource.db1")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name="slaveDataSource")
    @ConfigurationProperties(prefix="spring.datasource.db2")
    public DataSource slaveDataSource() {
        return DataSourceBuilder.create().build();
    }*/
    @Value("${spring.datasource.db1.jdbcUrl}")
    private String dbUrl1;

    @Value("${spring.datasource.db1.username}")
    private String username1;

    @Value("${spring.datasource.db1.password}")
    private String password1;

    @Value("${spring.datasource.db2.username}")
    private String username2;

    @Value("${spring.datasource.db2.password}")
    private String password2;

    @Value("${spring.datasource.db2.jdbcUrl}")
    private String dbUrl2;

    @Value("com.mysql.jdbc.Driver")
    private String driverClassName;

    @Value("5")
    private int initialSize;

    @Value("5")
    private int minIdle;

    @Value("20")
    private int maxActive;

    @Value("60000")
    private int maxWait;

    /**
     * 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
     */
    @Value("60000")
    private int timeBetweenEvictionRunsMillis;
    /**
     * 配置一个连接在池中最小生存的时间，单位是毫秒
     */
    @Value("300000")
    private int minEvictableIdleTimeMillis;

    @Value("SELECT 1 FROM DUAL")
    private String validationQuery;

    @Value("true")
    private boolean testWhileIdle;

    @Value("false")
    private boolean testOnBorrow;

    @Value("false")
    private boolean testOnReturn;

    /**
     * 打开PSCache，并且指定每个连接上PSCache的大小
     */
    @Value("true")
    private boolean poolPreparedStatements;

    @Value("20")
    private int maxPoolPreparedStatementPerConnectionSize;
    /**
     * 配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
     */
    //@Value("stat,wall,log4j")
    @Value("stat,wall")
    private String filters;
    /**
     * 通过connectProperties属性来打开mergeSql功能；慢SQL记录
     */
    @Value("druid.stat.mergeSql=true;druid.stat.slowSqlMillis=500")
    private String connectionProperties;

    @Bean(name = "masterDataSource")
    @Qualifier("masterDataSource")
    //@Primary
    public DataSource masterDataSource() {
        return buildDruidDataSource(username1, password1, dbUrl1);
        // return DataSourceBuilder.create().build();
    }

    @Bean(name = "slaveDataSource")
    @Qualifier("slaveDataSource")
    @Primary
    public DataSource slaveDataSource() {
        return buildDruidDataSource(username2, password2, dbUrl2);
        // return DataSourceBuilder.create().build();
    }

    private DruidDataSource buildDruidDataSource(String username, String password, String url) {
        DruidDataSource datasource = new DruidDataSource();

        datasource.setUrl(url);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);

        //configuration
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        datasource.setPoolPreparedStatements(poolPreparedStatements);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        try {
            datasource.setFilters(filters);
        } catch (SQLException e) {
            ;
        }
        datasource.setConnectionProperties(connectionProperties);

        return datasource;
    }

}
