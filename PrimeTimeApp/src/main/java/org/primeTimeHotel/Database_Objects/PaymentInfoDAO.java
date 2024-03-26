package org.primeTimeHotel.Database_Objects;

import org.primeTimeHotel.Domain_Model_Objects.PaymentInformation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        p.setCardProvider(resultSet.getString("provider"));
        p.setCardNumber(resultSet.getLong("card_number"));
        p.setExpirationDate(resultSet.getDate("expiration_date"));
        p.setSecurityCode(resultSet.getInt("security_code"));
        p.setZipCode(resultSet.getInt("zipcode"));
        return p;
    }
}
