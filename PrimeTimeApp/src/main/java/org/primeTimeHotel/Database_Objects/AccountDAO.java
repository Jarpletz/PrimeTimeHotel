package org.primeTimeHotel.Database_Objects;

import org.primeTimeHotel.Domain_Model_Objects.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO extends  MasterDAO{


    public Account fetchAccount(int account_id){
        try {
            PreparedStatement statement =connection.prepareStatement("SELECT * FROM ACCOUNTS WHERE ID = ?");
            statement.setInt(1,account_id);
            List<Account> accounts= fetchAccounts(statement);
            if(!accounts.isEmpty()) return accounts.getFirst();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public boolean insert(Account account){

        return true;
    }

    public boolean update(Account account){
        return true;
    }

    //fetches ALL accounts
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

    //fetches all the accounts with the given account type
    public List<Account> fetchAllByType(Account.Type type){
        try {
            PreparedStatement statement =connection.prepareStatement("SELECT * FROM ACCOUNTS WHERE ACCOUNT_TYPE = ?");
            statement.setInt(1,type.getCode());
            return fetchAccounts(statement);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }




    private List<Account> fetchAccounts(PreparedStatement statement){
        if (connection != null) {
            try {
                ResultSet rs= statement.executeQuery();
                return resultSetToAccountList(rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    private List<Account> resultSetToAccountList(ResultSet rs) throws SQLException {
        List<Account> accounts= new ArrayList<>();
        while (rs.next()) {
            Account.Type type = Account.Type.fromCode(rs.getInt("account_type"));
            Account newAccount;
            if(type == Account.Type.GUEST)
                newAccount = new GuestAccount();
            else if(type == Account.Type.CLERK)
                newAccount = new ClerkAccount();
            else
                newAccount = new AdminAccount();

            newAccount.setUsername(rs.getString("username"));
            newAccount.setPassword(rs.getString("password"));
            newAccount.setFirstName(rs.getString("first_name"));
            newAccount.setLastName(rs.getString("last_name"));
            newAccount.setPhoneNumber(rs.getString("phone_number"));
            newAccount.setEmail(rs.getString("email"));
        }
        return accounts;
    }
}

