package com.suncreate.demo.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.lang.reflect.Method;

@Aspect
@Component
@ConditionalOnProperty(prefix = "spring.datasource", name = "dynamic", havingValue = "true")
public class DBSourceAaspect implements Ordered{

    @Value("${spring.datasource.db1.dbType}")
    private String defaultDBType;

    // 对所有添加@DBType注解的方法进行切面
    @Pointcut(value = "@annotation(com.suncreate.demo.utils.DBType)")
    private void cut() {
    }

    @Around("cut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {

        Signature signature = point.getSignature();
        MethodSignature methodSignature = null;
        if (!(signature instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        methodSignature = (MethodSignature) signature;

        Object target = point.getTarget();
        Method currentMethod = target.getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());

        DBType dbType = currentMethod.getAnnotation(DBType.class);
        if (dbType != null) {
            DataSourceHolder.setDBType(dbType.name());
        } else {
            DataSourceHolder.setDBType(defaultDBType);
        }
        try {
            return point.proceed();
        } finally {
            DataSourceHolder.clearDBType();
        }
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
