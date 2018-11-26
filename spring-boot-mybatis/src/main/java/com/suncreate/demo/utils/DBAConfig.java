package com.suncreate.demo.utils;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 *  在这里将数据源A下面：mybatis的mapper接口和mapper映射串联起来
 */
@Configuration
@MapperScan(basePackages = {"com.suncreate.demo.dao.one"}, sqlSessionFactoryRef = "sqlSessionFactory1")
// @MapperScan(basePackages = {"com.suncreate.demo.dao.one"}, sqlSessionTemplateRef = "sqlSessionTemplate1")
public class DBAConfig {

    @Autowired
    @Qualifier("ds1")
    private DataSource ds1;

    @Bean("sqlSessionFactory1")
    @Primary
    public SqlSessionFactory sqlSessionFactory1() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(ds1);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/one/*.xml"));
        return factoryBean.getObject();
    }

    @Bean(name = "sqlTransactionManager1")
    @Primary
    public DataSourceTransactionManager masterDataSourceTransactionManager(@Qualifier("ds1") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean("sqlSessionTemplate1")
    @Primary
    public SqlSessionTemplate sqlSessionTemplate1() throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory1());
        return template;
    }
}
