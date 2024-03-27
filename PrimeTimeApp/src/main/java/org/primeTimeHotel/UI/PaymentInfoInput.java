package org.primeTimeHotel.UI;

import org.primeTimeHotel.Controllers.PaymentController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PaymentInfoInput extends  JPanel{

    PaymentController controller;

    private int paymentId;
    private JLabel errorLabel;
    private JTextField providerField;
    private JTextField cardNumberField;
    private JTextField expirationMonthField;
    private JTextField expirationYearField;
    private JTextField securityField;
    private JTextField zipcodeField;

    public int getPaymentId() {
        return paymentId;
    }
    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public JLabel getErrorLabel() {
        return errorLabel;
    }

    public JTextField getProviderField() {
        return providerField;
    }

    public JTextField getCardNumberField() {
        return cardNumberField;
    }

    public JTextField getExpirationMonthField() {
        return expirationMonthField;
    }

    public JTextField getExpirationYearField() {
        return expirationYearField;
    }

    public JTextField getSecurityField() {
        return securityField;
    }

    public JTextField getZipcodeField() {
        return zipcodeField;
    }

    public PaymentInfoInput(PaymentController pc){
        super();
        this.paymentId = -1;
        this.controller = pc;

        EmptyBorder labelBorder = new EmptyBorder(0,5,5,5);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel inputPanel = new JPanel();
        GroupLayout layout = new GroupLayout(inputPanel);

        providerField = new JTextField();
        JLabel providerLabel = new JLabel("Card Provider:");
        providerLabel.setLabelFor(providerField);
        providerLabel.setBorder(labelBorder);

        cardNumberField = new JTextField();
        JLabel cardNumberLabel = new JLabel("Card Number: ");
        cardNumberLabel.setLabelFor(cardNumberField);
        cardNumberLabel.setBorder(labelBorder);

        expirationMonthField = new JTextField();
        JLabel expirationMonthLabel = new JLabel("Expiration Date:");
        expirationMonthField.setColumns(2);
        expirationMonthLabel.setLabelFor(expirationMonthField);
        expirationMonthLabel.setBorder(labelBorder);
        expirationYearField = new JTextField();
        JLabel expirationYearLabel = new JLabel("/");
        expirationYearField.setColumns(2);
        expirationYearLabel.setLabelFor(expirationYearField);
        expirationYearLabel.setBorder(new EmptyBorder(2,5,5,5));


        securityField = new JTextField();
        securityField.setColumns(3);
        JLabel securityLabel = new JLabel("Security Code:");
        securityLabel.setLabelFor(securityField);
        securityLabel.setBorder(labelBorder);

        zipcodeField = new JTextField();
        zipcodeField.setColumns(5);
        JLabel zipcodeLabel = new JLabel("Zip Code:");
        zipcodeLabel.setLabelFor(zipcodeField);
        zipcodeLabel.setBorder(labelBorder);

        layout.setHorizontalGroup(
            layout.createParallelGroup()
                .addGroup(layout.createSequentialGroup()
                    .addComponent(providerLabel)
                    .addComponent(providerField))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(cardNumberLabel)
                    .addComponent(cardNumberField))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(expirationMonthLabel)
                        .addComponent(expirationMonthField)
                        .addComponent(expirationYearLabel)
                        .addComponent(expirationYearField)
                        .addComponent(securityLabel)
                        .addComponent(securityField)
                )
                .addGroup(layout.createSequentialGroup()
                        .addComponent(zipcodeLabel)
                        .addComponent(zipcodeField))

        );
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(providerLabel)
                    .addComponent(providerField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(cardNumberLabel)
                    .addComponent(cardNumberField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(expirationMonthLabel)
                        .addComponent(expirationMonthField)
                        .addComponent(expirationYearLabel)
                        .addComponent(expirationYearField)
                    .addComponent(securityLabel)
                    .addComponent(securityField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(zipcodeLabel)
                        .addComponent(zipcodeField))

        );
        inputPanel.setLayout(layout);
        add(inputPanel);

        JPanel errorPanel = new JPanel();

        errorLabel = new JLabel();
        errorLabel.setForeground(new Color(234, 77, 77));
        errorPanel.add(errorLabel);

        add(errorPanel,BorderLayout.AFTER_LAST_LINE);

        this.setBorder(new EmptyBorder(20,20,20,20));
    }


}
