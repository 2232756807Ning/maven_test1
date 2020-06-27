package com.nwq.utils;

import com.alibaba.druid.pool.DruidDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBUtil {
    private static DruidDataSource druidDataSource;

    static {
        Properties prop = new Properties();
        InputStream in = DBUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
        try {
            prop.load(in);
            druidDataSource = new DruidDataSource();
            druidDataSource.setUsername(prop.getProperty("jdbc.username"));
            druidDataSource.setPassword(prop.getProperty("jdbc.password"));
            druidDataSource.setUrl(prop.getProperty("jdbc.url"));
            druidDataSource.setDriverClassName(prop.getProperty("jdbc.driverClassName"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据源
     */
    public static DruidDataSource getDataSource() {
        return druidDataSource;
    }
}
