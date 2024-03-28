package org.primeTimeHotel.UI.Sidebar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AdminSidebar extends AbstractSidebar{

    static public void main(String[] args) {
        sidebarDemo(new AdminSidebar());
    }

    @Override
    List<SidebarButton> getButtons() {
        ArrayList<SidebarButton> list = new ArrayList<>();
        SidebarButton button = new SidebarButton("Create Clerk Account", "https://uxwing.com/wp-content/themes/uxwing/download/editing-user-action/create-account-icon.png");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pressed Create Clerk Account Button");
            }
        });
        list.add(button);

        button = new SidebarButton("Reset User Password", "https://th.bing.com/th/id/R.227cdd12e8198b52c19ac9f49ec718c6?rik=EW8jfKwN4nPcaQ&riu=http%3a%2f%2fwww.newdesignfile.com%2fpostpic%2f2009%2f11%2freset-button_303425.jpg&ehk=%2f1xEtl9B0KvXg%2fJnP61VMahjW3E8Mvl2vV4pYhaOOZw%3d&risl=&pid=ImgRaw&r=0");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pressed Reset User Password Button");
            }
        });
        list.add(button);

        return list;
    }
}
