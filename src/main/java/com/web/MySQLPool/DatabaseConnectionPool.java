package com.web.MySQLPool;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabaseConnectionPool {
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource dataSource;

    static {
        config.setJdbcUrl("jdbc:mysql://localhost:3306/your_database_name");
        config.setUsername("your_username");
        config.setPassword("your_password");
        config.setMaximumPoolSize(10); // 连接池中最大的连接数
        // 可根据需要配置其他连接池属性

        dataSource = new HikariDataSource(config);
    }

    public static HikariDataSource getDataSource() {
        return dataSource;
    }
}
