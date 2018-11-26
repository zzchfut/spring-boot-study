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
 *  在这里将数据源B下面：mybatis的mapper接口和mapper映射串联起来
 *
 */
@Deprecated
@Configuration
// 注意！！！
@MapperScan(basePackages = {"com.suncreate.demo.dao.two"}, sqlSessionFactoryRef = "sqlSessionFactory2")
// @MapperScan(basePackages = {"com.suncreate.demo.dao.two"}, sqlSessionTemplateRef = "sqlSessionTemplate2")
public class DBBConfig {

    @Autowired
    @Qualifier("ds2")
    private DataSource ds2;

    @Bean("sqlSessionFactory2")
    public SqlSessionFactory sqlSessionFactory1() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(ds2);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath*:mapper/two/*.xml")); // 注意！！！
        return factoryBean.getObject();
    }

    @Bean(name = "sqlTransactionManager2")
    public DataSourceTransactionManager masterDataSourceTransactionManager(@Qualifier("ds2") DataSource dataSource) {
         return new DataSourceTransactionManager(dataSource);
    }

    @Bean("sqlSessionTemplate2")
    public SqlSessionTemplate sqlSessionTemplate1() throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory1());
        return template;
    }
}
