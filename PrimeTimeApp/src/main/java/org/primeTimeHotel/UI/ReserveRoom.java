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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Flow;

public class ReserveRoom {
    private static int NUM_RESERVATIONS = 5;
    private static int RESERVATION_HEIGHT = 170;
    private static int RESERVATION_WIDTH = 700;
    private static Color GRAYSCALE = new Color(217,217,217);
    private JPanel reservePanel;
    private JPanel reserveDatePanel;

    // For the top display text "Find Hotel Rooms" panel
    private JPanel displayPanel;


    private JButton backButton;
    private JFrame reserveFrame;
    private JScrollPane reserveScrollPane;
    private JScrollBar scrollBar;


    // for the input pannel
    private JButton roomType;
    private JPanel inputPanel;
    private JTextField dateTextFieldStart;
    private JTextField dateTextFieldEnd;
    private JLabel displayText;


    private JTextField roomTextField;
    private BufferedImage tempRoomImage;
    private BufferedImage companyLogo;

    public ReserveRoom() {
        this.createUIComponents();
    }


    private void dropDownButton(JMenu menu, String lableName){
        JMenuItem btn = new JMenuItem(lableName);
        menu.add(btn);
        roomType.addActionListener(e ->{
            btn.setVisible(true);
             menu.setPopupMenuVisible(true);
//             btn.getComponentPopupMenu(), roomType.getX(),roomType.getY());
        });
        btn.addActionListener(e ->{
            menu.setPopupMenuVisible(false);
        });
    }
    private void createDatePanel(JPanel panel){
        JLabel displayDateOfStay = new JLabel("Date of stay:");
        displayDateOfStay.setBorder(new EmptyBorder(0, 100, 0, 600));
        displayDateOfStay.setFont(new Font(displayDateOfStay.getFont().getFontName(), Font.BOLD, 18));

        roomType = new JButton();
        roomType.setText(" Room Type ");
        roomType.setFont(new Font(roomType.getFont().getFontName(), Font.BOLD, 18));
        roomType.setBackground(GRAYSCALE);
        roomType.setPreferredSize(new Dimension(180,30));

        JMenu roomTypePopup = new JMenu();
        roomTypePopup.setPreferredSize(new Dimension(0,0));
        roomType.add(roomTypePopup);


        dropDownButton(roomTypePopup, "Room Type 1");
        dropDownButton(roomTypePopup, "Room Type 2");
        dropDownButton(roomTypePopup, "Room Type 3");


        dateTextFieldStart = new JTextField(10);
        dateTextFieldStart.setPreferredSize(new Dimension(100, 30));
        dateTextFieldStart.setFont(new Font(dateTextFieldStart.getFont().getFontName(), Font.PLAIN, 16));

        JLabel separator = new JLabel(" - ");
        separator.setFont(new Font(separator.getFont().getFontName(), Font.BOLD, 16));

        dateTextFieldEnd = new JTextField(10);
        dateTextFieldEnd.setPreferredSize(new Dimension(100, 30));
        dateTextFieldEnd.setFont(new Font(dateTextFieldEnd.getFont().getFontName(), Font.PLAIN, 16));

        panel.add(displayDateOfStay);
        panel.add(dateTextFieldStart);
        panel.add(separator);
        panel.add(dateTextFieldEnd);

        JLabel separator2 = new JLabel(" ");
        separator2.setPreferredSize(new Dimension(150,50));
        panel.add(separator2);

        panel.add(roomType);
    }

    //Handles the text creation side of the reservation frame (right side)
    private void reservationToListCreateText(JPanel jPanel, String[] ROOM_INFO_ARR){
        JLabel roomNumLabel = new JLabel();
        JLabel bedNumLabel = new JLabel();
        JLabel rentRateLabel = new JLabel();
        JLabel bedTypeLabel = new JLabel();
        JLabel smokingStatus = new JLabel();

        //Room Number
        roomNumLabel.setText(ROOM_INFO_ARR[0]); // Grab
        roomNumLabel.setBorder(new EmptyBorder(5, 5, 5, 100));
        roomNumLabel.setFont(new Font(roomNumLabel.getFont().getFontName(), Font.BOLD, 20));

        //Bed num label
        bedNumLabel.setText(ROOM_INFO_ARR[1]); // Grab
        bedNumLabel.setBorder(new EmptyBorder(5, 5, 5, 100));
        bedNumLabel.setFont(new Font(bedNumLabel.getFont().getFontName(), Font.BOLD, 20));

        //rent rate
        rentRateLabel.setText(ROOM_INFO_ARR[2]); // Grab
        rentRateLabel.setBorder(new EmptyBorder(5, 20, 5, 75));
        rentRateLabel.setFont(new Font(rentRateLabel.getFont().getFontName(), Font.PLAIN, 15));

        //bed type
        bedTypeLabel.setText(ROOM_INFO_ARR[3]); // Grab
        bedTypeLabel.setBorder(new EmptyBorder(5, 40, 5, 70));
        bedTypeLabel.setFont(new Font(bedTypeLabel.getFont().getFontName(), Font.PLAIN, 15));

        //Smoking status
        smokingStatus.setText(ROOM_INFO_ARR[4]); // Grab
        smokingStatus.setBorder(new EmptyBorder(5, 40, 5, 340));
        smokingStatus.setFont(new Font(smokingStatus.getFont().getFontName(), Font.PLAIN, 15));

        JButton selectButton = new JButton("SELECT");
        selectButton.setBackground(Color.BLACK);
        selectButton.setBorderPainted(false);
        selectButton.setFont(new Font(smokingStatus.getFont().getFontName(), Font.BOLD, 15));
        selectButton.setForeground(Color.WHITE);
        selectButton.setPreferredSize(new Dimension(100 ,30));

        jPanel.add(roomNumLabel);
        jPanel.add(bedNumLabel);
        jPanel.add(rentRateLabel);
        jPanel.add(bedTypeLabel);
        jPanel.add(smokingStatus);
        JLabel fakeLabel = new JLabel();
        fakeLabel.setBorder(new EmptyBorder(5, 300, 5, 40));
        fakeLabel.setFont(new Font(fakeLabel.getFont().getFontName(), Font.PLAIN, 15));
        jPanel.add(fakeLabel);
        jPanel.add(selectButton);
    }

    //Handles the image and the call to make a text panel
    private void addReservationToList(String TEMPORARY){
        JLabel roomImage = new JLabel();
        roomImage.setIcon(new ImageIcon(tempRoomImage));
        JPanel reservationPanel = new JPanel();

        //Handle the text
        JPanel textPanel = new JPanel();
        textPanel.setPreferredSize(new Dimension(reserveFrame.getWidth()/2,150));
        reservationToListCreateText(textPanel, TEMPORARY.split(","));

        reservationPanel.setBackground(GRAYSCALE.darker());
        reservationPanel.setBorder(new LineBorder(Color.WHITE, 5));

        reservationPanel.setPreferredSize(new Dimension(750,RESERVATION_HEIGHT));
//        reservationPanel.setBackground(Color.magenta.darker());
        JPanel imgHolder = new JPanel();
        imgHolder.setPreferredSize(new Dimension(250,150));
        imgHolder.add(roomImage);
        reservationPanel.add(imgHolder, Component.LEFT_ALIGNMENT);
        reservationPanel.add(textPanel);
        reservePanel.add(reservationPanel);
    }
    public static class MyScrollingBar extends BasicScrollBarUI{
        @Override
        protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
            // Paint the thumb in red.
            g.setColor(new Color(107,136,90));

//            c.setBackground(Color.yellow);
            c.setVisible(false);
            g.fillRoundRect(thumbBounds.x+3, thumbBounds.y, thumbBounds.width-5, thumbBounds.height, 10, 10);
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
            companyLogo = ImageIO.read(new File("PrimeTimeApp/src/main/java/org/primeTimeHotel/UI/Prime Time Hotel Icon.png"));
            tempRoomImage = ImageIO.read(new File("PrimeTimeApp/src/main/java/org/primeTimeHotel/UI/Prime Time Hotel Icon - Copy.png"));
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
//        displayPanel.setBackground(Color.red);
        displayPanel.setLayout(new GridLayout());

        //Build back button
//        backButton.setBackground(Color.pink);
        backButton.setText(" Back ");
        backButton.setFont(new Font(backButton.getFont().getFontName(), Font.BOLD, 20));
        backButton.setBorder(new EmptyBorder(0, 0, 5, 200));
        backButton.setBorderPainted(false);
        backButton.setPreferredSize(new Dimension(500,50));
        backButton.setVisible(true);



        //Build display text ("Find Hotel Reservation")
        displayText.setPreferredSize(new Dimension(200,100));
//        displayText.setBackground(Color.green);
        displayText.setText("Find Hotel Rooms");
        displayText.setHorizontalAlignment(JLabel.CENTER);
        displayText.setFont(new Font(backButton.getFont().getFontName(), Font.BOLD, 35));


        //Build date panel
//        reserveDatePanel.setBackground(Color.BLUE);
        reserveDatePanel.setPreferredSize(new Dimension(700, 150));
        reserveDatePanel.setBorder(new EmptyBorder(5,0,5,100));
        createDatePanel(reserveDatePanel);



        //Build reservation panel
        reservePanel.setAutoscrolls(true);
//        reservePanel.setBackground(Color.red);
//        reservePanel.setLayout(new GridLayout(3, 1));
//        reservePanel.setPreferredSize(new Dimension(800,2000));
        reservePanel.setPreferredSize(new Dimension(RESERVATION_WIDTH,NUM_RESERVATIONS * RESERVATION_HEIGHT + 50));
        reservePanel.setAutoscrolls(true);
        reservePanel.setVisible(true);
        reservePanel.setBorder(new EmptyBorder(2, 0,10,20));
//        reservePanel.setBackground(Color.CYAN);

        //FAKE BUTTONS
        addReservationToList("Room 305,Beds: 3,$215 per night,2 Queen 1 Pullout,Non-smoking");
        addReservationToList("Room 306,Beds: 2,$200 per night,1 King 1 Pullout,Non-smoking");
        addReservationToList("Room 307,Beds: 2,$205 per night,1 Queen 1 Pullout,Non-smoking");
        addReservationToList("Room 308,Beds: 3,$215 per night,2 Queen 1 Pullout,Non-smoking");
        addReservationToList("Room 309,Beds: 3,$215 per night,2 Queen 1 Pullout,Non-smoking");


        //Compile into frame
        reserveFrame.setIconImage(companyLogo);

        reserveFrame.setLayout(new BoxLayout(reserveFrame.getContentPane(),BoxLayout.PAGE_AXIS));
//        reserveFrame.add(backButton);
//        reserveFrame.add(displayText);
        displayPanel.add(backButton, Component.LEFT_ALIGNMENT);
        displayPanel.add(displayText, Component.CENTER_ALIGNMENT);

        JLabel fakeButton = new JLabel();
//        fakeButton.setBackground(Color.magenta);
        fakeButton.setFont(new Font(backButton.getFont().getFontName(), Font.BOLD, 20));
        fakeButton.setBorder(new EmptyBorder(0, 100, 5, 50));
//        fakeButton.setBorderPainted(false);
        fakeButton.setPreferredSize(new Dimension(500,50));

        displayPanel.add(fakeButton, Component.RIGHT_ALIGNMENT);

        reserveFrame.add(displayPanel);
        reserveFrame.add(reserveDatePanel);
        reserveFrame.add(reserveScrollPane);
        reserveFrame.setVisible(true);

        // TODO: place custom component creation code here
    }
}
