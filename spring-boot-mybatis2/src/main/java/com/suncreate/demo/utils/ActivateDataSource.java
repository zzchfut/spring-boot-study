package com.suncreate.demo.utils;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.HashMap;
import java.util.Map;

public class ActivateDataSource extends AbstractRoutingDataSource {
    private static ActivateDataSource instance;
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

    public static synchronized ActivateDataSource getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new ActivateDataSource();
                }
            }
        }
        return instance;
    }

    //必须实现其方法
    protected Object determineCurrentLookupKey() {
        return DataSourceHolder.getDBType();
    }
}
