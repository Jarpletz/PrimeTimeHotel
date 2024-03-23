package org.primeTimeHotel.Database_Objects;

import java.sql.*;

public class MasterDAO {
    Connection connection = null;
    MasterDAO() {
        try {
            // Connect to the database
            String url = "jdbc:oracle:thin:@prime-time-hotel.c70qkmwawtvx.us-east-2.rds.amazonaws.com:1521:orcl";
            String username = "prime_time_admin";
            String password = "PTpass!!";
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            this.close();
        }
    }
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
