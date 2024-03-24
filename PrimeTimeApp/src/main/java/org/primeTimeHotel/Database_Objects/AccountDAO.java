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
            PreparedStatement statement =connection.prepareStatement("SELECT * FROM ACCOUNTS WHERE ID = ? FETCH FIRST ROW ONLY");
            statement.setInt(1,account_id);
            List<Account> accounts= fetchAccounts(statement);
            if(!accounts.isEmpty()) return accounts.getFirst();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public Account authenticateAccount(String username, String password){
        try {
            PreparedStatement statement =connection.prepareStatement("SELECT * FROM ACCOUNTS WHERE USERNAME = ? AND PASSWORD = ? FETCH FIRST ROW ONLY");
            statement.setString(1,username);
            statement.setString(2,password);
            List<Account> accounts= fetchAccounts(statement);
            if(!accounts.isEmpty()) return accounts.getFirst();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public boolean insert(Account account){
        if(connection== null) return false;
        if(fetchAccount(account.getId())==null){
            try{
                PreparedStatement stmt = connection.prepareStatement(
                        "INSERT INTO ACCOUNTS(USERNAME,PASSWORD,FIRST_NAME,LAST_NAME,PHONE_NUMBER,EMAIL,ACCOUNT_TYPE)" +  " VALUES (?,?,?,?,?,?,?)");
                stmt.setString(1,account.getUsername());
                stmt.setString(2, account.getPassword());
                stmt.setString(3,account.getFirstName());
                stmt.setString(4,account.getLastName());
                stmt.setString(5,account.getPhoneNumber());
                stmt.setString(6,account.getEmail());
                stmt.setInt(7,account.getType().getCode());
                stmt.executeUpdate();
                return true;
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean update(Account account){
        if(connection== null) return false;
        if(fetchAccount(account.getId())!=null) return false;
        try{
            PreparedStatement stmt = connection.prepareStatement(
                    "UPDATE  ACCOUNTS "+
                            "SET USERNAME=?,PASSWORD=?,FIRST_NAME=?,LAST_NAME=?,PHONE_NUMBER=?,EMAIL=?,ACCOUNT_TYPE=?" +
                            "WHERE id = ?");
            stmt.setString(1,account.getUsername());
            stmt.setString(2, account.getPassword());
            stmt.setString(3,account.getFirstName());
            stmt.setString(4,account.getLastName());
            stmt.setString(5,account.getPhoneNumber());
            stmt.setString(6,account.getEmail());
            stmt.setInt(7,account.getType().getCode());
            stmt.setInt(8,account.getId());
            stmt.executeUpdate();
            return true;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(Account account){
        if(connection== null) return false;
        if(fetchAccount(account.getId())!=null){
            try{
                PreparedStatement stmt = connection.prepareStatement(
                        "DELETE FROM ACCOUNTS WHERE ID = ?");
                stmt.setInt(1,account.getId());
                stmt.executeUpdate();
                return true;
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
        return false;
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

    public List<Account> fetchByUserInfo(String searchQuery){

        try {
            PreparedStatement statement =connection.prepareStatement("SELECT * FROM ACCOUNTS " +
                    "WHERE UPPER(EMAIL) LIKE ? OR UPPER(FIRST_NAME) LIKE ? OR UPPER(LAST_NAME) LIKE ? OR UPPER(PHONE_NUMBER) LIKE ?"+
                    "FETCH FIRST 10 ROWS ONLY");
            for(int i=1; i<= 4;i++){
                statement.setString(i,searchQuery.toUpperCase());
            }
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
            newAccount.setId(rs.getInt("id"));
            newAccount.setUsername(rs.getString("username"));
            newAccount.setPassword(rs.getString("password"));
            newAccount.setFirstName(rs.getString("first_name"));
            newAccount.setLastName(rs.getString("last_name"));
            newAccount.setPhoneNumber(rs.getString("phone_number"));
            newAccount.setEmail(rs.getString("email"));
            accounts.add(newAccount);
        }
        return accounts;
    }
}

