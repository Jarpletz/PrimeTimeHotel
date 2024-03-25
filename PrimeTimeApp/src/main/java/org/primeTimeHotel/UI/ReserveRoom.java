package org.primeTimeHotel.UI;

import org.w3c.dom.css.Rect;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.plaf.basic.ComboPopup;
import java.awt.*;
import java.awt.dnd.Autoscroll;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Flow;

public class ReserveRoom {
    private static int NUM_RESERVATIONS = 5;
    private static int RESERVATION_HEIGHT = 175;
    private static int RESERVATION_WIDTH = 700;
    private static Color GRAYSCALE = new Color(217,217,217);
    private JPanel reservePanel;
    private JPanel reserveDatePanel;
    private JPanel displayPanel;
    private JFrame reserveFrame;
    private JScrollPane reserveScrollPane;
    private JScrollBar scrollBar;

    // for the input pannel
    private JPanel inputPanel;
    private JTextField dateTextField;
    private JLabel displayText;

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

    private void addReservationToList(String TEMPORARY){
        JPanel reservationPanel = new JPanel();
        JTextPane reservationTextPane = new JTextPane();
        reservationTextPane.setText(TEMPORARY);
        reservationPanel.add(reservationTextPane);
        reservationPanel.setBackground(GRAYSCALE);
        reservationPanel.setBorder(new LineBorder(Color.WHITE, 5));
        reservationPanel.setPreferredSize(new Dimension(750,RESERVATION_HEIGHT));
        reservePanel.add(reservationPanel);
    }
    public static class MyScrollingBar extends BasicScrollBarUI{
        @Override
        protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
            // Paint the thumb in red.
            g.setColor(new Color(125,122,122));

//            c.setBackground(Color.yellow);
            c.setVisible(false);
            g.fillRoundRect(thumbBounds.x+2, thumbBounds.y, thumbBounds.width-4, thumbBounds.height, 10, 10);
        }
        @Override
        protected void paintTrack(Graphics g, JComponent c, Rectangle thumbBounds) {
//            g.setColor(Color.green);
            c.setBackground(GRAYSCALE);
        }
        @Override
        protected JButton createDecreaseButton(int orient) {
            return hideArrows();
        }
        @Override
        protected JButton createIncreaseButton(int orient) {
            return hideArrows();
        }
        private JButton hideArrows() {
            JButton jbutton = new JButton();
            jbutton.setMaximumSize(new Dimension(0, 0));
            jbutton.setMinimumSize(new Dimension(0, 0));
            jbutton.setPreferredSize(new Dimension(0, 0));
            return jbutton;
        }
    }

    private void createUIComponents() {
        reserveFrame = new JFrame();
        reservePanel = new JPanel();
        reserveDatePanel = new JPanel();
        reserveScrollPane = new JScrollPane(reservePanel);
        displayText = new JLabel();
        displayPanel = new JPanel();

        //Set scroll bar and application icon
        reserveScrollPane.setAutoscrolls(true);
        reserveScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        reserveScrollPane.getVerticalScrollBar().setUnitIncrement(15);
        reserveScrollPane.getViewport().setScrollMode(JViewport.BACKINGSTORE_SCROLL_MODE);
        reserveScrollPane.setLayout(new ScrollPaneLayout());
        reserveScrollPane.setBorder(new EmptyBorder(10, 0, 10, 0));
        backButton = new JButton();
        scrollBar = new JScrollBar();
        reserveScrollPane.getVerticalScrollBar().setUI(new MyScrollingBar());
        try{
            companyLogo = ImageIO.read(new File("src/main/java/org/primeTimeHotel/UI/Prime Time Hotel Icon.png"));
            UIManager.put("ScrollBarUI", "src/main/java/org/primeTimeHotel/UI/Prime Time Hotel Icon.png");
            BasicScrollBarUI.createUI(scrollBar);
        }catch (IOException e){
            companyLogo = null;
            System.out.println("Invalid image");
        }
        reserveFrame.setSize(960,640);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException |
                 InstantiationException e) {
        }
        reserveFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Build display frame
        displayPanel.setPreferredSize(new Dimension(900,70));
        displayPanel.setBackground(Color.red);
        displayPanel.setLayout(new GridLayout());

        //Build back button
        backButton.setBackground(Color.pink);
        backButton.setText(" Back ");
        backButton.setFont(new Font(backButton.getFont().getFontName(), Font.BOLD, 20));
        backButton.setBorder(new EmptyBorder(0, 100, 5, 50));
        backButton.setBorderPainted(false);
        backButton.setPreferredSize(new Dimension(500,50));
        backButton.setVisible(true);



        //Build display text ("Find Hotel Reservation")
        displayText.setPreferredSize(new Dimension(200,100));
        displayText.setBackground(Color.green);
        displayText.setText("Find Hotel Rooms");
        displayText.setHorizontalAlignment(JLabel.CENTER);
        displayText.setFont(new Font(backButton.getFont().getFontName(), Font.BOLD, 35));


        //Build date panel
        reserveDatePanel.setBackground(Color.BLUE);
        reserveDatePanel.setPreferredSize(new Dimension(700, 200));
        reserveDatePanel.setBorder(new EmptyBorder(5,0,5,100));



        //Build reservation panel
        reservePanel.setAutoscrolls(true);
        reservePanel.setBackground(Color.red);
//        reservePanel.setLayout(new GridLayout(3, 1));
//        reservePanel.setPreferredSize(new Dimension(800,2000));
        reservePanel.setPreferredSize(new Dimension(RESERVATION_WIDTH,NUM_RESERVATIONS * RESERVATION_HEIGHT + 50));
        reservePanel.setAutoscrolls(true);
        reservePanel.setVisible(true);
        reservePanel.setBorder(new EmptyBorder(2, 0,10,20));
        reservePanel.setBackground(Color.CYAN);

        //FAKE BUTTONS
        addReservationToList("Reservation 1");
        addReservationToList("Reservation 2");
        addReservationToList("Reservation 3");
        addReservationToList("Reservation 3");
        addReservationToList("Reservation 3");


        //Compile into frame
        reserveFrame.setIconImage(companyLogo);

        reserveFrame.setLayout(new BoxLayout(reserveFrame.getContentPane(),BoxLayout.PAGE_AXIS));
//        reserveFrame.add(backButton);
//        reserveFrame.add(displayText);
        displayPanel.add(backButton, Component.LEFT_ALIGNMENT);
        displayPanel.add(displayText, Component.CENTER_ALIGNMENT);

        JButton fakeButton = new JButton();
        fakeButton.setBackground(Color.magenta);
        fakeButton.setFont(new Font(backButton.getFont().getFontName(), Font.BOLD, 20));
        fakeButton.setBorder(new EmptyBorder(0, 100, 5, 50));
        fakeButton.setBorderPainted(false);
        fakeButton.setPreferredSize(new Dimension(500,50));
        fakeButton.setVisible(true);

        displayPanel.add(fakeButton, Component.RIGHT_ALIGNMENT);

        reserveFrame.add(displayPanel);
        reserveFrame.add(reserveDatePanel);
        reserveFrame.add(reserveScrollPane);
        reserveFrame.setVisible(true);

        // TODO: place custom component creation code here
    }
}
