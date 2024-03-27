package org.primeTimeHotel.UI;

import org.primeTimeHotel.Controllers.PaymentController;
import org.primeTimeHotel.Database_Objects.RoomDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaymentInputExample {





    private static void createAndShowGUI() {
        PaymentController paymentController = new PaymentController();

        int accountId = 1;//using the Payment Info belonging to account 1

        //Create and set up the window.
        JFrame frame = new JFrame("Enter Card Information");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JPanel contentPlane = new JPanel();
        contentPlane.setOpaque(true);

        PaymentInfoInput paymentInfoInput = paymentController.generatePaymentInfoPanel(accountId);

        JButton saveInfoButton = new JButton("Confirm Payment Information");
        saveInfoButton.setBackground(new Color(109, 138, 92));
        saveInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paymentController.savePaymentInput(paymentInfoInput);
            }
        });

        contentPlane.add(paymentInfoInput);
        contentPlane.add(saveInfoButton);

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
