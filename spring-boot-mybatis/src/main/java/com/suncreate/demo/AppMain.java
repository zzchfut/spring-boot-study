package com.suncreate.demo;

import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

// @EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class,DataSourceTransactionManagerAutoConfiguration.class, MybatisAutoConfiguration.class})
@ComponentScan(basePackages= {"com.suncreate.demo"})
public class AppMain {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(AppMain.class, args);
    }
}

