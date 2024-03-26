package org.primeTimeHotel.Domain_Model_Objects;

import java.sql.Date;
import java.util.Objects;

public class PaymentInformation extends AbstractDomainModelObject {
    String cardProvider;
    long cardNumber;
    Date expirationDate;
    int securityCode;
    int zipCode;

    public PaymentInformation(){
        setId(-1);
        cardNumber=0;
        securityCode = 0;
        zipCode= 0;
        expirationDate =new Date(0);
        cardProvider ="";

    }
    public PaymentInformation(String cardProvider, long cardNumber, Date expirationDate, int securityCode, int zipCode) {
        this.cardProvider = cardProvider;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.securityCode = securityCode;
        this.zipCode = zipCode;
    }

    public String getCardProvider() {
        return cardProvider;
    }

    public void setCardProvider(String cardProvider) {
        this.cardProvider = cardProvider;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(int securityCode) {
        this.securityCode = securityCode;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        PaymentInformation that = (PaymentInformation) object;
        return id == that.getId() &&
               cardNumber == that.cardNumber &&
               securityCode == that.securityCode && 
               zipCode == that.zipCode && 
               Objects.equals(cardProvider, that.cardProvider) && 
               Objects.equals(expirationDate, that.expirationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,cardProvider, cardNumber, expirationDate, securityCode, zipCode);
    }

    @Override
    public String toString() { // for debugging //
        final StringBuilder sb = new StringBuilder("PayementInformation{");
        sb.append("ID ='").append(id).append('\'');
        sb.append("cardProvider ='").append(cardProvider).append('\'');
        sb.append(", cardNumber =").append(cardNumber);
        sb.append(", experationDate ='").append(expirationDate).append('\'');
        sb.append(", securityCode =").append(securityCode);
        sb.append(", zipCode =").append(zipCode);
        sb.append('}');
        return sb.toString();
    }
}
