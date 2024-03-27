package org.primeTimeHotel.Database_Objects;

import org.primeTimeHotel.Domain_Model_Objects.PaymentInformation;
import org.primeTimeHotel.Domain_Model_Objects.Reservation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PaymentInfoDAO extends RootDAO<PaymentInformation>{
    public PaymentInfoDAO() {
        super("PAYMENTINFO", new String[]{"provider", "card_number", "expiration_date", "security_code", "zipcode"});
    }
    @Override
    protected void setStatement(PreparedStatement statement, PaymentInformation paymentInfo, int parameterIndex) throws SQLException {
        statement.setString(parameterIndex++, paymentInfo.getCardProvider());
        statement.setLong(parameterIndex++, paymentInfo.getCardNumber());
        statement.setDate(parameterIndex++, paymentInfo.getExpirationDate());
        statement.setInt(parameterIndex++, paymentInfo.getSecurityCode());
        statement.setInt(parameterIndex++, paymentInfo.getZipCode());
        if (paymentInfo.getId() != -1)
            statement.setInt(parameterIndex, paymentInfo.getId());
    }

    @Override
    protected PaymentInformation initializeEntry(ResultSet resultSet) throws SQLException {
        PaymentInformation p = new PaymentInformation();
        p.setId(resultSet.getInt("id"));
        p.setCardProvider(resultSet.getString("PROVIDER"));
        p.setCardNumber(resultSet.getLong("CARD_NUMBER"));
        p.setExpirationDate(resultSet.getDate("EXPIRATION_DATE"));
        p.setSecurityCode(resultSet.getInt("SECURITY_CODE"));
        p.setZipCode(resultSet.getInt("ZIPCODE"));
        return p;
    }


}
