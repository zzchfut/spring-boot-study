package com.suncreate.demo.utils;

import java.lang.annotation.*;

/**
 * 注解会触发切面流程  add by zhuzhichao 2018-11-23
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface DBType {
   String name() default "";
   String value() default "";
}
