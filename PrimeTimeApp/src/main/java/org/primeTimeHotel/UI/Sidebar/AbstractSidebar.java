package org.primeTimeHotel.UI.Sidebar;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public abstract class AbstractSidebar extends JPanel {

    final static int WIDTH = 250;

    JLabel icon;
    SidebarButton home_button;
    SidebarButton logout_button;
    List<SidebarButton> buttons;

    AbstractSidebar() {
        buttons = getButtons();

        setLayout(new GridLayoutManager(4 + buttons.size(),1,new Insets(5,5,5,5), 10, 10));
        setPreferredSize(new Dimension(WIDTH, -1));
        setBackground(new Color(142, 150, 126));

        try {
            BufferedImage logo = ImageIO.read(new File("src/main/java/org/primeTimeHotel/UI/Prime Time Hotel Icon.png"));
            Image scaled = logo.getScaledInstance(WIDTH-25, WIDTH-25, Image.SCALE_SMOOTH);
            icon = new JLabel(new ImageIcon(scaled));
            add(icon, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        } catch (IOException ignore) {}

        home_button = new SidebarButton("Home", "https://clipground.com/images/white-home-icon-transparent-png-6.png");
        add(home_button, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

        for (int i = 0; i < buttons.size(); ++i)
            add(buttons.get(i), new GridConstraints(2+i, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));

        final Spacer spacer = new Spacer();
        add(spacer, new GridConstraints(2+buttons.size(), 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));

        logout_button = new SidebarButton("Logout", "https://th.bing.com/th/id/R.1998b5da6cd9bd18ceac91d1ad72684e?rik=beRFgwmEYkQzHQ&riu=http%3a%2f%2fcdn.onlinewebfonts.com%2fsvg%2fimg_342905.png&ehk=WsB0szQn2J%2b3Ias%2bDY9YY%2fPB7y%2fOvDjSHp%2bSF90RySY%3d&risl=&pid=ImgRaw&r=0");
        add(logout_button, new GridConstraints(3+buttons.size(), 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    abstract List<SidebarButton> getButtons();

    public static void sidebarDemo(AbstractSidebar sidebar) {
        JFrame frame = new JFrame("SidebarTest");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);

        JPanel area = new JPanel();

        frame.add(sidebar, BorderLayout.WEST);
        frame.add(area, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}

class SidebarButton extends JButton {
    SidebarButton (String text, String url) {
        super(text);
        try {
            BufferedImage bufferedImage = ImageIO.read(new URL(url));
            Image scaled = bufferedImage.getScaledInstance(40,40,Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(scaled);
            setIcon(icon);
        } catch (IOException ignored) {}

        setHorizontalTextPosition(SwingConstants.RIGHT);
        setHorizontalAlignment(SwingConstants.LEFT);
        setPreferredSize(new Dimension(AbstractSidebar.WIDTH-25, 40));
        setIconTextGap(15);
        setBackground(new Color(255, 255, 255));
        setForeground(new Color(0, 0, 0));
        setBorderPainted(false);
        setBorder(new EmptyBorder(0,5,0,5));
        setFocusPainted(false);
    }
}
