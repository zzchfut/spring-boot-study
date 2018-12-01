package com.suncreate.demo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef="slaveEntityManagerFactory",
        transactionManagerRef="slaveTransactionManager",
        basePackages= { "com.suncreate.demo.dao.slave" }) //设置Repository所在位置
public class SlaveDataSourceConfig {

    @Autowired
    private JpaProperties jpaProperties;

    @Resource(name = "slaveDataSource")
    private DataSource slaveDataSource;

    private Map<String, Object> getVendorProperties() {
        return jpaProperties.getHibernateProperties(new HibernateSettings());
    }

    @Bean(name = "slaveEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean slaveEnityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setDataSource(slaveDataSource);
        localContainerEntityManagerFactoryBean.setPackagesToScan(new String[]{"com.suncreate.demo.domain.slave"});
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        localContainerEntityManagerFactoryBean.setJpaPropertyMap(getVendorProperties());
        return localContainerEntityManagerFactoryBean;
    }

    @Bean(name = "slaveTransactionManager")
    @Primary
    public PlatformTransactionManager writeTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(slaveEnityManagerFactoryBean().getObject());
        return transactionManager;
    }
}
