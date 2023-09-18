package com.web.MySQLPool;

import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseOperationsWithPool {
    public static void main(String[] args) {
        HikariDataSource dataSource = DatabaseConnectionPool.getDataSource();

        try (Connection connection = dataSource.getConnection()) {
            if (connection != null) {
                String sql = "SELECT * FROM myemployees";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    // 处理查询结果
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    // 添加更多的字段处理
                    System.out.println("ID: " + id + ", Name: " + name);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
