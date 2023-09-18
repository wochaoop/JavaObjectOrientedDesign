package com.web.MySQLPool;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseAccess {

    private final HikariDataSource dataSource;

    public DatabaseAccess(String jdbcUrl, String username, String password) {
        // 配置 HikariCP 数据源
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(username);
        config.setPassword(password);
        config.setMaximumPoolSize(10);
        dataSource = new HikariDataSource(config);
    }

    public void closeDataSource() {
        dataSource.close();
    }

    public void selectEmployeesByDepartment(int departmentId) {
        try (Connection connection = dataSource.getConnection()) {
            String selectQuery = "SELECT * FROM employees WHERE department_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, departmentId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int employeeId = resultSet.getInt("employee_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                // 其他字段类似

                System.out.println("Employee ID: " + employeeId);
                System.out.println("First Name: " + firstName);
                System.out.println("Last Name: " + lastName);
                // 打印其他字段
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertEmployee(String firstName, String lastName, String email) {
        try (Connection connection = dataSource.getConnection()) {
            String insertQuery = "INSERT INTO employees (first_name, last_name, email) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, email);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new employee was inserted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSalaryByLastName(String lastName, double newSalary) {
        try (Connection connection = dataSource.getConnection()) {
            String updateQuery = "UPDATE employees SET salary = ? WHERE last_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setDouble(1, newSalary);
            preparedStatement.setString(2, lastName);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Salary for employees with last name '" + lastName + "' updated successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEmployeeByLastName(String lastName) {
        try (Connection connection = dataSource.getConnection()) {
            String deleteQuery = "DELETE FROM employees WHERE last_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setString(1, lastName);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Employee with last name '" + lastName + "' was deleted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/myemployees";
        String username = "root";
        String password = "123456";

        DatabaseAccess dbAccess = new DatabaseAccess(jdbcUrl, username, password);

        // 使用封装的方法进行数据库操作
        dbAccess.selectEmployeesByDepartment(30);
        dbAccess.insertEmployee("John", "Doe", "johndoe@example.com");
//        dbAccess.updateSalaryByLastName("Doe", 7500.00);
//        dbAccess.deleteEmployeeByLastName("Doe");

        // 关闭数据源
        dbAccess.closeDataSource();
    }
}
