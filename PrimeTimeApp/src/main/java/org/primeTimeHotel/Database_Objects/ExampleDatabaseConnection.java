package org.primeTimeHotel.Database_Objects;

import java.sql.*;

public class ExampleDatabaseConnection {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            // Connect to the database
            String url = "jdbc:oracle:thin:@prime-time-hotel.c70qkmwawtvx.us-east-2.rds.amazonaws.com:1521:orcl";
            String username = "prime_time_admin";
            String password = "PTpass!!";
            connection = DriverManager.getConnection(url, username, password);

            // Create a statement
            Statement statement = connection.createStatement();

            // Execute a statement to create a table
            String createTableSql = "CREATE TABLE test_table (id NUMBER, name VARCHAR2(50))";
            //statement.execute(createTableSql);

            // Execute a statement to insert data into the table
            String insertDataSql = "INSERT INTO test_table (id, name) VALUES (1, 'Test')";
            //statement.execute(insertDataSql);

            // Execute a query to select data from the table
            String selectDataSql = "SELECT * FROM test_table";
            ResultSet resultSet = statement.executeQuery(selectDataSql);

            // Process the result set
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                System.out.println("id: " + id + ", name: " + name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

