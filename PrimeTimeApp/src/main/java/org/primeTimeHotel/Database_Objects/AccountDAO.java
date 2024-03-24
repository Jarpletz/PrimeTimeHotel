package org.primeTimeHotel.Database_Objects;

import org.primeTimeHotel.Domain_Model_Objects.Account;
import org.primeTimeHotel.Domain_Model_Objects.Reservation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO extends  MasterDAO{

    public List<Account> fetchAllAccounts(){
        List<Account> accounts = new ArrayList<>();
        if (connection != null) {
            try {
                PreparedStatement statement =connection.prepareStatement("SELECT * FROM ACCOUNTS");
                ResultSet rs= statement.executeQuery();
                accounts = resultSetToAccountList(rs);
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
        return accounts;
    }


    private List<Account> resultSetToAccountList(ResultSet rs) throws SQLException {
        List<Reservation> reservations= new ArrayList<>();
        while (rs.next()) {

        }
        return reservations;
    }
}

