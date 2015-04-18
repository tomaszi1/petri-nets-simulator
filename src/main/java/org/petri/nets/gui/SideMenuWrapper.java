package org.petri.nets.gui;

import javax.swing.*;
import java.awt.*;

public class SideMenuWrapper extends JScrollPane{

    public SideMenuWrapper() {

        super(new SideMenuPanel());
        setPreferredSize(new Dimension(220,500));
    }

}
