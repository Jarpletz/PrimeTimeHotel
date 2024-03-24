package org.primeTimeHotel.UI;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ReserveRoom {

    private JPanel reservePanel;
    private JFrame reserveFrame;

    // for the input pannel
    private JPanel inputPanel;
    private JTextField dateTextField;

    private JButton backButton;

    private JTextField roomTextField;
    private BufferedImage companyLogo;

    public ReserveRoom() {
        this.createUIComponents();
    }


    private void createInputPannel()
    {
        inputPanel = new JPanel(new FlowLayout());

        JLabel dateLabel = new JLabel("Date:");
        dateTextField = new JTextField(10);
    }

    private void createUIComponents() {
        reserveFrame = new JFrame();
        reservePanel = new JPanel();
        backButton = new JButton();
        try{
            companyLogo = ImageIO.read(new File("src/main/java/org/primeTimeHotel/UI/Prime Time Hotel Icon.png"));
        }catch (IOException e){
            companyLogo = null;
            System.out.println("Invalid image");
        }

        reserveFrame.setSize(960,640);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException |
                 InstantiationException e) {
            throw new RuntimeException(e);
        }
        reserveFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //Build back button

        //Build panel
        reservePanel.setBackground(Color.BLUE);
        reservePanel.setLayout(new GridLayout());
        reservePanel.setPreferredSize(new Dimension(50,50));
        reservePanel.setVisible(true);
//        reservePanel.setBorder(new EmptyBorder(100,10,10,10));


        reserveFrame.setIconImage(companyLogo);

        reserveFrame.setLayout(new FlowLayout());
        reserveFrame.add(reservePanel,Component.RIGHT_ALIGNMENT);
        reserveFrame.setVisible(true);

        // TODO: place custom component creation code here
    }
}
