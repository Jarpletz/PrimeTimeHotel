package org.primeTimeHotel.Domain_Model_Objects;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class PaymentInformation {
    int id;
    String provider;
    long card_number;
    Date expiration_date;
    int security_code;
    int zipcode;

    public PaymentInformation() {
        this(null,-1,null,-1,-1);
    }
    public PaymentInformation(String provider, long card_number, Date expiration_date, int security_code, int zipcode) {
        this.id = -1;
        this.provider = provider;
        this.card_number = card_number;
        this.expiration_date = expiration_date;
        this.security_code = security_code;
        this.zipcode = zipcode;
    }

    public PaymentInformation(ResultSet resultSet) throws SQLException {
        this(
            resultSet.getString("provider"),
            resultSet.getLong("card_number"),
            resultSet.getDate("expiration_date"),
            resultSet.getInt("security_code"),
            resultSet.getInt("zipcode")
        );
        this.id = resultSet.getInt("id");
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        PaymentInformation that = (PaymentInformation) object;
        return card_number == that.card_number && 
               security_code == that.security_code && 
               zipcode == that.zipcode && 
               Objects.equals(provider, that.provider) &&
               Objects.equals(expiration_date, that.expiration_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(provider, card_number, expiration_date, security_code, zipcode);
    }

    @Override
    public String toString() { // for debugging //
        final StringBuilder sb = new StringBuilder("PayementInformation{");
        sb.append("cardProvider='").append(provider).append('\'');
        sb.append(", cardNumber=").append(card_number);
        sb.append(", experationDate='").append(expiration_date).append('\'');
        sb.append(", securityCode=").append(security_code);
        sb.append(", zipCode=").append(zipcode);
        sb.append('}');
        return sb.toString();
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getProvider() {return provider;}
    public void setProvider(String provider) {this.provider = provider;}
    public long getCard_number() {return card_number;}
    public void setCard_number(long card_number) {this.card_number = card_number;}
    public Date getExpiration_date() {return expiration_date;}
    public void setExpiration_date(Date expiration_date) {this.expiration_date = expiration_date;}
    public int getSecurity_code() {return security_code;}
    public void setSecurity_code(int security_code) {this.security_code = security_code;}
    public int getZipcode() {return zipcode;}
    public void setZipcode(int zipcode) {this.zipcode = zipcode;}
}
