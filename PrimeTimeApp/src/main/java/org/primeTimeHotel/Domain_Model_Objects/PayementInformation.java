package org.primeTimeHotel.Domain_Model_Objects;

import java.util.Objects;

public class PayementInformation {
    String cardProvider;
    long cardNumber;
    String expirationDate;
    int securityCode;
    int zipCode;

    public PayementInformation(String cardProvider, long cardNumber, String expirationDate, int securityCode, int zipCode) {
        this.cardProvider = cardProvider;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.securityCode = securityCode;
        this.zipCode = zipCode;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        PayementInformation that = (PayementInformation) object;
        return cardNumber == that.cardNumber && 
               securityCode == that.securityCode && 
               zipCode == that.zipCode && 
               Objects.equals(cardProvider, that.cardProvider) && 
               Objects.equals(expirationDate, that.expirationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardProvider, cardNumber, expirationDate, securityCode, zipCode);
    }

    @Override
    public String toString() { // for debugging //
        final StringBuilder sb = new StringBuilder("PayementInformation{");
        sb.append("cardProvider='").append(cardProvider).append('\'');
        sb.append(", cardNumber=").append(cardNumber);
        sb.append(", experationDate='").append(expirationDate).append('\'');
        sb.append(", securityCode=").append(securityCode);
        sb.append(", zipCode=").append(zipCode);
        sb.append('}');
        return sb.toString();
    }
}
