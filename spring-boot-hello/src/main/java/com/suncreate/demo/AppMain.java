package com.suncreate.demo;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages= {"com.suncreate.demo"})
public class AppMain {

    @RequestMapping(value="/hello",method = RequestMethod.GET)
    @ResponseBody
    String hello() {
        return "Hello";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(AppMain.class, args);
    }
}

