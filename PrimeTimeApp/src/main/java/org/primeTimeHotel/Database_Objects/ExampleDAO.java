package org.primeTimeHotel.Database_Objects;

import java.sql.*;

public class ExampleDAO extends RootDAO {
    ResultSet select() {
        ResultSet rs = null;
        if (connection != null) {
        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM test_table");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        }
        return rs;
    }
    public static void main(String[] args) {
        var ex = new ExampleDAO();
        try {
            ResultSet resultSet = ex.select();

            // Process the result set
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                System.out.println("id: " + id + ", name: " + name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ex.close();
        }
    }
}

