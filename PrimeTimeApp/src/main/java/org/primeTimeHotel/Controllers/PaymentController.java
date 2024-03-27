package org.primeTimeHotel.Controllers;

import org.primeTimeHotel.Domain_Model_Objects.Account;
import org.primeTimeHotel.Domain_Model_Objects.PaymentInformation;
import org.primeTimeHotel.Services.PaymentService;
import org.primeTimeHotel.UI.PaymentInfoInput;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class PaymentController {

    Account account;//TODO: Remove once a way to persist the current user is implemented

    PaymentService paymentService;

    public PaymentController(){
        paymentService = new PaymentService();
    }

    public PaymentInfoInput generatePaymentInfoPanel(int accountId){
        PaymentInfoInput infoInput= new PaymentInfoInput(this);


        PaymentInformation currentInfo = paymentService.getPaymentInfo(accountId);
        System.out.println(currentInfo);
        if(currentInfo!=null){
            Calendar cal = Calendar.getInstance();
            cal.setTime(currentInfo.getExpirationDate());

            infoInput.setPaymentId(currentInfo.getId());
            infoInput.getProviderField().setText(currentInfo.getCardProvider());
            infoInput.getCardNumberField().setText(Long.toString(currentInfo.getCardNumber()));
            infoInput.getExpirationYearField().setText(Integer.toString(cal.get(Calendar.YEAR)));
            infoInput.getExpirationMonthField().setText(Integer.toString(cal.get(Calendar.MONTH)));
            infoInput.getSecurityField().setText(Integer.toString(currentInfo.getSecurityCode()));
            infoInput.getZipcodeField().setText(Integer.toString(currentInfo.getZipCode()));
        }

        return  infoInput;
    }

    public boolean savePaymentInput(PaymentInfoInput input){
        try{

            try{//validate the input
                this.verifyPaymentInput(input);
                input.getErrorLabel().setText("");
            }catch (Exception e){
                input.getErrorLabel().setText(e.getMessage());
                return false;
            }
            String provider = input.getProviderField().getText();
            Long cardNumber = Long.parseLong(
                    input.getCardNumberField().getText().replaceAll("\\D","")
            );
            java.util.Date cardUtilDate = (java.util.Date) new GregorianCalendar(
                    Integer.parseInt(input.getExpirationYearField().getText()),
                    Integer.parseInt(input.getExpirationMonthField().getText()),
                    1
            ).getTime();
            java.sql.Date cardDate = new java.sql.Date(cardUtilDate.getTime());

            int securityCode=Integer.parseInt( input.getSecurityField().getText());
            int zipCode =Integer.parseInt( input.getZipcodeField().getText());

            int id = input.getPaymentId();

            if(id>0){
                //if this payment info already exists, update it
                PaymentInformation info = paymentService.createUpdatedPaymentInfo(id,provider,cardNumber,cardDate,securityCode,zipCode);
                return paymentService.saveUpdatedPaymentInfo(info);
            }else{
                //else, make a new payment info and save that
                PaymentInformation info = paymentService.createNewPaymentInfo(provider,cardNumber,cardDate,securityCode,zipCode);
                return paymentService.saveNewPaymentInfo(info);
            }

        }catch(NumberFormatException e){
            input.getErrorLabel().setText("Error: Invalid Payment Information");
            return false;
        }
    }

    //returns empty string if payment valid, an error message otherwise
    private void verifyPaymentInput(PaymentInfoInput input) throws  Exception{
        try{
            String provider = input.getProviderField().getText();
            if(provider==null || provider.isEmpty()) throw new Exception("Please Enter A Card Provider!") ;

            Long cardNumber = Long.parseLong(
                    input.getCardNumberField().getText().replaceAll("\\D","")
            );
            if(cardNumber<1) throw new Exception( "Invalid Card Number!");

            int month  = Integer.parseInt(input.getExpirationMonthField().getText());
            if(month<1 || month >12 ) throw new Exception( "Invalid Expiration Month!");
            int year = Integer.parseInt(input.getExpirationYearField().getText());
            if(year<1900) throw new Exception( "Invalid Expiration Year!");

            int zipCode =Integer.parseInt( input.getZipcodeField().getText());
            if(zipCode<10000 || zipCode >99999) throw new Exception( "Invalid Zip Code!");
            int securityCode=Integer.parseInt( input.getSecurityField().getText());
            if(securityCode<100 || securityCode > 999) throw new Exception( "Invalid Security Code!");

        }catch(NumberFormatException e){
            throw new Exception( "Invalid Payment Information Input!");
        }
    }


}
