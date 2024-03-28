package org.primeTimeHotel.UI.Sidebar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ClerkSidebar extends AbstractSidebar{

    static public void main(String[] args) {
        sidebarDemo(new ClerkSidebar());
    }

    @Override
    List<SidebarButton> getButtons() {
        ArrayList<SidebarButton> list = new ArrayList<>();
        SidebarButton button = new SidebarButton("Make Guest Reservation", "https://icon-library.com/images/reservation-icon/reservation-icon-5.jpg");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pressed New Reservation Button");
            }
        });
        list.add(button);

        button = new SidebarButton("View/Edit Guest Reservations", "https://clipground.com/images/reservation-icon-clipart-5.png");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pressed View Reservation Button");
            }
        });
        list.add(button);

        button = new SidebarButton("View/Edit Rooms", "https://th.bing.com/th/id/OIP.qufYPd770M5wpdk9HEbDDAHaHa?rs=1&pid=ImgDetMain");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pressed View/Edit Rooms Button");
            }
        });
        list.add(button);

        button = new SidebarButton("Check In/Out", "https://th.bing.com/th/id/R.f1ef11d6eddb0b8416dbdc20779fe33c?rik=LhRx1rxhWdMfmw&riu=http%3a%2f%2fcdn.onlinewebfonts.com%2fsvg%2fimg_571836.png&ehk=sgTYK8aJurMIYbBFxzxzP66vtuCnKaYO7udNsJZE1Fk%3d&risl=&pid=ImgRaw&r=0");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pressed Check In/Out Button");
            }
        });
        list.add(button);

        button = new SidebarButton("Billing", "https://th.bing.com/th/id/OIP._LWM1wSO545W16mPtgiL6gHaHa?rs=1&pid=ImgDetMain");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pressed Billing Button");
            }
        });
        list.add(button);

        button = new SidebarButton("Account Details", "https://pluspng.com/img-png/user-png-icon-thin-line-user-icon-2232.png");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pressed Account Details Button");
            }
        });
        list.add(button);

        return list;
    }
}
