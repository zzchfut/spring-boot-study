package com.suncreate.demo.utils;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.HashMap;
import java.util.Map;

public class DataSourceRouter extends AbstractRoutingDataSource {
    private static DataSourceRouter instance;
    private static byte[] lock = new byte[0];
    private static Map<Object, Object> dataSourceMap = new HashMap<Object, Object>();

    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        super.setTargetDataSources(targetDataSources);
        dataSourceMap.putAll(targetDataSources);
        super.afterPropertiesSet();// 必须添加该句，否则新添加数据源无法识别到
    }

    public Map<Object, Object> getDataSourceMap() {
        return dataSourceMap;
    }

    public static synchronized DataSourceRouter getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new DataSourceRouter();
                }
            }
        }
        return instance;
    }

    //必须实现其方法：真实的路由点
    protected Object determineCurrentLookupKey() {
        return DataSourceHolder.getDBType();
    }
}
