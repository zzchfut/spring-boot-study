package com.suncreate.demo.utils;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource(value = {"classpath:source.properties"})
// @MapperScan(basePackages = "com.suncreate.demo.dao.impl")
public class DataSourceConfig {
    @Value("${spring.datasource.db1.jdbcUrl}")
    private String defaultDBUrl;
    @Value("${spring.datasource.db1.username}")
    private String defaultDBUser;
    @Value("${spring.datasource.db1.password}")
    private String defaultDBPassword;
    @Value("${spring.datasource.db1.driverClassName}")
    private String defaultDBDreiverName;
    @Value("${spring.datasource.db1.dbType}")
    private  String defaultDBType;

    @Value("${spring.datasource.db2.jdbcUrl}")
    private String masterDBUrl;
    @Value("${spring.datasource.db2.username}")
    private String masterDBUser;
    @Value("${spring.datasource.db2.password}")
    private String masterDBPassword;
    @Value("${spring.datasource.db2.driverClassName}")
    private String masterDBDreiverName;
    @Value("${spring.datasource.db2.dbType}")
    private  String masterDBType;

    @Bean(name = "dataSourceRouter")
    public DataSourceRouter dynamicDataSource() {

        DruidDataSource masterDataSource = new DruidDataSource();
        masterDataSource.setUrl(defaultDBUrl);
        masterDataSource.setUsername(defaultDBUser);
        masterDataSource.setPassword(defaultDBPassword);
        masterDataSource.setDriverClassName(defaultDBDreiverName);

        DruidDataSource slaveDataSource = new DruidDataSource();
        slaveDataSource.setDriverClassName(masterDBDreiverName);
        slaveDataSource.setUrl(masterDBUrl);
        slaveDataSource.setUsername(masterDBUser);
        slaveDataSource.setPassword(masterDBPassword);

        // 动态数据源初始化，存储到上下文
        Map<Object,Object> map = new HashMap<>();
        map.put(defaultDBType, masterDataSource);
        map.put(masterDBType, slaveDataSource);

        DataSourceRouter dataSourceRouter = DataSourceRouter.getInstance();
        //DataSourceRouter dataSourceRouter = new DataSourceRouter();
        dataSourceRouter.setTargetDataSources(map);
        dataSourceRouter.setDefaultTargetDataSource(slaveDataSource);

        return dataSourceRouter;
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSourceRouter") DataSource dynamicDataSource)
            throws Exception {
        SqlSessionFactoryBean sessionFactoryBean  = new SqlSessionFactoryBean();
        sessionFactoryBean .setDataSource(dynamicDataSource);
        sessionFactoryBean .setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath*:mapper/*.xml"));
        return sessionFactoryBean .getObject();

    }

    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory)
            throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
