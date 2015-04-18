package org.petri.nets.gui;

import javax.swing.*;
import java.awt.*;

public class SideMenuWrapper extends JScrollPane{

    public static final int MENU_WIDTH = 220;

    public SideMenuWrapper() {

        super(new SideMenuPanel());
        setPreferredSize(new Dimension(MENU_WIDTH,500));
    }

}
