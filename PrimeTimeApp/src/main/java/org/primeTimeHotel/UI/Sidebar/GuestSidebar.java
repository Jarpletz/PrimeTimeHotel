package org.primeTimeHotel.UI.Sidebar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GuestSidebar extends AbstractSidebar{

    static public void main(String[] args) {
        sidebarDemo(new GuestSidebar());
    }

    @Override
    List<SidebarButton> getButtons() {
        ArrayList<SidebarButton> list = new ArrayList<>();
        SidebarButton button = new SidebarButton("Make Reservation", "https://icon-library.com/images/reservation-icon/reservation-icon-5.jpg");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pressed New Reservation Button");
            }
        });
        list.add(button);

        button = new SidebarButton("View/Edit Reservations", "https://clipground.com/images/reservation-icon-clipart-5.png");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pressed View Reservation Button");
            }
        });
        list.add(button);

        button = new SidebarButton("Shop", "https://clipground.com/images/icon-cart-clipart-2.jpg");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pressed Shop Button");
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
