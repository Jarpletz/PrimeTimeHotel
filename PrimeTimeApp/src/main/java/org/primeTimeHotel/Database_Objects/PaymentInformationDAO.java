package org.primeTimeHotel.Database_Objects;

import org.primeTimeHotel.Domain_Model_Objects.PaymentInformation;
import org.primeTimeHotel.Domain_Model_Objects.Reservation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PaymentInformationDAO extends MasterDAO {
    public PaymentInformation fetchPaymentInformation(int id) {
        String sql = "SELECT * FROM paymentinfo WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next())
                return new PaymentInformation(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean insertPaymentInformation(PaymentInformation p){
        if (fetchPaymentInformation(p.getId()) == null) {
            String sql = "INSERT INTO Paymentinfo (PROVIDER, CARD_NUMBER, EXPIRATION_DATE, SECURITY_CODE, ZIPCODE) VALUES (?, ?, ?, ? ,?)";
            String[] returnValues = {"ID"};
            try (PreparedStatement statement = connection.prepareStatement(sql, returnValues)) {
                statement.setString(1,p.getProvider());
                statement.setLong(2, p.getCard_number());
                statement.setDate(3,p.getExpiration_date());
                statement.setInt(4,p.getSecurity_code());
                statement.setInt(5,p.getZipcode());
                if (statement.executeUpdate() > 0) {
                    try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            p.setId(generatedKeys.getInt(1));
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

    public boolean updatePaymentInformation(PaymentInformation p){
        if (fetchPaymentInformation(p.getId()) != null) {
            String sql = "UPDATE paymentinfo " +
                         "SET provider = ?, card_number = ?, expiration_date = ?, security_code = ?, zipcode = ? " +
                         "WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1,p.getProvider());
                statement.setLong(2, p.getCard_number());
                statement.setDate(3,p.getExpiration_date());
                statement.setInt(4,p.getSecurity_code());
                statement.setInt(5,p.getZipcode());
                statement.setInt(6, p.getId());
                return statement.executeUpdate() > 0;
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return false;
    }
    public boolean deletePaymentInformation(int id){
        if (fetchPaymentInformation(id) != null) {
            String sql = "DELETE FROM Reservations WHERE id = ?";
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
