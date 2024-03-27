package org.primeTimeHotel.Services;

import org.primeTimeHotel.Database_Objects.AccountDAO;
import org.primeTimeHotel.Database_Objects.PaymentInfoDAO;
import org.primeTimeHotel.Domain_Model_Objects.Account;
import org.primeTimeHotel.Domain_Model_Objects.PaymentInformation;

import java.sql.Date;

public class PaymentService {
    PaymentInfoDAO dao;

    public PaymentService(){
        dao = new PaymentInfoDAO();
    }

    public PaymentInformation getPaymentInfo(int accountId){
        AccountDAO accountDAO = new AccountDAO();
        int paymentId = accountDAO.fetch(accountId).getPaymentInfoId();
        System.out.println("PAYMET ID:" + paymentId);
        return dao.fetch(paymentId);
    }

    public PaymentInformation createNewPaymentInfo(String provider, Long cardNumber, Date expirationDate,int securityCode,int zipCode){
        return new PaymentInformation(provider,cardNumber,expirationDate,securityCode,zipCode);
    }

    public boolean saveNewPaymentInfo(PaymentInformation p){
        return dao.insert(p);
    }

    public PaymentInformation createUpdatedPaymentInfo(int id, String provider, Long cardNumber, Date expirationDate,int securityCode,int zipCode){
        PaymentInformation info = new PaymentInformation(provider,cardNumber,expirationDate,securityCode,zipCode);
        info.setId(id);
        return info;
    }
    public boolean saveUpdatedPaymentInfo(PaymentInformation p){
        return dao.update(p);
    }


}
