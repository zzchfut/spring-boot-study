package com.suncreate.demo.utils;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource(value = {"classpath:source.properties"})
@EnableTransactionManagement(order = 2) // 这里的事务管理采用最简便的方法 @EnableTransactionManagement + @Transactional
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
    private String defaultDBType;

    @Value("${spring.datasource.db2.jdbcUrl}")
    private String masterDBUrl;
    @Value("${spring.datasource.db2.username}")
    private String masterDBUser;
    @Value("${spring.datasource.db2.password}")
    private String masterDBPassword;
    @Value("${spring.datasource.db2.driverClassName}")
    private String masterDBDreiverName;
    @Value("${spring.datasource.db2.dbType}")
    private String masterDBType;

    @Bean(name = "activateDataSource")
    public DataSourceRouter dynamicDataSource() {
        DataSourceRouter dataSourceRouter = DataSourceRouter.getInstance();

        DruidDataSource defaultDataSource = new DruidDataSource();
        defaultDataSource.setUrl(defaultDBUrl);
        defaultDataSource.setUsername(defaultDBUser);
        defaultDataSource.setPassword(defaultDBPassword);
        defaultDataSource.setDriverClassName(defaultDBDreiverName);

        DruidDataSource masterDataSource = new DruidDataSource();
        masterDataSource.setDriverClassName(masterDBDreiverName);
        masterDataSource.setUrl(masterDBUrl);
        masterDataSource.setUsername(masterDBUser);
        masterDataSource.setPassword(masterDBPassword);

        // 动态数据源初始化，存储到上下文
        Map<Object,Object> map = new HashMap<>();
        map.put(defaultDBType, defaultDataSource);
        map.put(masterDBType, masterDataSource);

        dataSourceRouter.setTargetDataSources(map);
        dataSourceRouter.setDefaultTargetDataSource(defaultDataSource);

        return dataSourceRouter;
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("activateDataSource") DataSource dynamicDataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dynamicDataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath*:mapper/*.xml"));
        return bean.getObject();

    }

    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory)
            throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
