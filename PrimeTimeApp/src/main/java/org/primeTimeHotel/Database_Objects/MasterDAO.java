package org.primeTimeHotel.Database_Objects;

import java.sql.*;

public class MasterDAO {
    static protected Connection connection = null;
    MasterDAO() {
        if (connection == null) {
            try {
                // Connect to the database
                String url = "jdbc:oracle:thin:@prime-time-hotel.c70qkmwawtvx.us-east-2.rds.amazonaws.com:1521:orcl";
                String username = "prime_time_admin";
                String password = "PTpass!!";
                connection = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                e.printStackTrace();
                close();
            }
        }
    }
    static public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                connection = null;
            }
        }
    }
}
