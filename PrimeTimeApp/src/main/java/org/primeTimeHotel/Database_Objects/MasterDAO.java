package org.primeTimeHotel.Database_Objects;

import org.primeTimeHotel.Domain_Model_Objects.AbstractDomainModelObject;
import org.primeTimeHotel.Domain_Model_Objects.Reservation;

import java.sql.*;

public abstract class MasterDAO<T extends AbstractDomainModelObject> {
    static protected Connection connection = null;
    protected String table_name;
    protected String[] attribute_names;

    MasterDAO(String table_name, String[] attribute_names) {
        this.table_name = table_name;
        this.attribute_names = attribute_names;
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



    public abstract T fetch(int id); // you cannot use the constructor of a generic so this is the workaround
    public ResultSet fetchResultSet(int id){
        String sql = "SELECT * FROM " + table_name + " WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next())
                return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean insert(T t){
        if (fetchResultSet(t.getId()) == null) {
            String sql =
                "INSERT INTO " + table_name +
                "(" + String.join(", ", attribute_names) + ") " +
                "VALUES (" + String.join(", ", java.util.Collections.nCopies(attribute_names.length, "?"))+ ")";
            String[] returnColumns = {"id"};
            try (PreparedStatement statement = connection.prepareStatement(sql, returnColumns)) {
                t.setId(-1); // should already be, but just incase
                t.setStatement(statement,1);
                if (statement.executeUpdate() > 0) {
                    try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            t.setId(generatedKeys.getInt(1));
                        }
                    }
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean update(T t){
        if (fetch(t.getId()) != null) {
            String[] items = new String[attribute_names.length];
            for (int i = 0; i < items.length; i++)
                items[i] = attribute_names[i] + " = ?";
            String sql =
                "UPDATE " + table_name + " " +
                "SET " + String.join(", ", items) + " " +
                "WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                t.setStatement(statement, 1);
                return statement.executeUpdate() > 0;
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return false;
    }
    public boolean delete(int id){
        if (fetch(id) != null) {
            String sql = "DELETE FROM " + table_name + " WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                return statement.executeUpdate() > 0;
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return false;
    }
}
