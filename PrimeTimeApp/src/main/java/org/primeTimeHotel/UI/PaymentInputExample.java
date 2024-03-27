package org.primeTimeHotel.UI;

import org.primeTimeHotel.Controllers.PaymentController;
import org.primeTimeHotel.Database_Objects.RoomDAO;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaymentInputExample {
    private static void createAndShowGUI() {
        final boolean[] showConfirmation = {false};
        PaymentController paymentController = new PaymentController();
        int accountId = 1;//using the Payment Info belonging to account 1

        //Create and set up the window.
        JFrame frame = new JFrame("Enter Card Information");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JPanel contentPlane = new JPanel();
        contentPlane.setOpaque(true);
        if(!showConfirmation[0]) {

            //Have the paymentController generate the paymentInfo input, using the id of the account in question
            PaymentInfoInput paymentInfoInput = paymentController.generatePaymentInfoPanel(accountId);
            //paymentInfoInput is extended from JPanel, so you can do JPanel things (like resetting a border)
            paymentInfoInput.setBorder(new CompoundBorder(new LineBorder(new Color(200,200,200),2),new EmptyBorder(5,5,5,5)));

            //just a label to show that the payment info has been saved
            JLabel confirmationLabel = new JLabel("Payment Info Updated!");
            confirmationLabel.setVisible(false);

            //form submit button - NOT a part of the paymentInfo component. This allows for greater freedom on what
            //the button does (ex. both save the paymentInfo and go to the next page
            JButton saveInfoButton = new JButton("Confirm Payment Information");
            saveInfoButton.setBackground(new Color(109, 138, 92));
            saveInfoButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean paymentSuccess;

                    //tell the payment controller to save the payment info updated
                    //This method returns true only if it was successful (false if there was bad input)
                    paymentSuccess= paymentController.savePaymentInput(paymentInfoInput);
                    if(paymentSuccess){
                        showConfirmation[0] = true;
                        paymentInfoInput.setVisible(false);
                        saveInfoButton.setVisible(false);
                        confirmationLabel.setVisible(true);
                    }
                }
            });

            contentPlane.add(paymentInfoInput);
            contentPlane.add(confirmationLabel);
            contentPlane.add(saveInfoButton);
        }



        frame.setContentPane(contentPlane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
        //RoomDAO.close();
    }
}
