package org.primeTimeHotel.Database_Objects;

import org.primeTimeHotel.Domain_Model_Objects.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO extends RootDAO<Account> {
    AccountDAO() {
        super("accounts", new String[]{"username", "password", "first_name", "last_name", "phone_number", "email", "account_type"});
    }
    @Override
    protected void setStatement(PreparedStatement statement, Account account, int parameterIndex) throws SQLException {
        statement.setString(parameterIndex++, account.getUsername());
        statement.setString(parameterIndex++, account.getPassword());
        statement.setString(parameterIndex++, account.getFirstName());
        statement.setString(parameterIndex++, account.getLastName());
        statement.setString(parameterIndex++, account.getPhoneNumber());
        statement.setString(parameterIndex++, account.getEmail());
        statement.setInt(parameterIndex++, account.getType().getCode());
        if (account.getId() != -1)
            statement.setInt(parameterIndex, account.getId());
    }
    @Override
    protected Account initializeEntry(ResultSet resultSet) throws SQLException {
        Account.Type type = Account.Type.fromCode(resultSet.getInt("account_type"));
        Account account = switch(type) {
            case GUEST -> new GuestAccount();
            case CLERK -> new ClerkAccount();
            case ADMIN -> new AdminAccount();
        };
        account.setId(resultSet.getInt("id"));
        account.setUsername(resultSet.getString("username"));
        account.setPassword(resultSet.getString("password"));
        account.setFirstName(resultSet.getString("first_name"));
        account.setLastName(resultSet.getString("last_name"));
        account.setPhoneNumber(resultSet.getString("phone_number"));
        account.setEmail(resultSet.getString("email"));
        return account;
    }

    public Account authenticateAccount(String username, String password){
        try {
            PreparedStatement statement =connection.prepareStatement("SELECT * FROM ACCOUNTS WHERE USERNAME = ? AND PASSWORD = ? FETCH FIRST ROW ONLY");
            statement.setString(1,username);
            statement.setString(2,password);
            List<Account> accounts= fetchAccounts(statement);
            if(accounts!=null && !accounts.isEmpty()) return accounts.getFirst();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
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

