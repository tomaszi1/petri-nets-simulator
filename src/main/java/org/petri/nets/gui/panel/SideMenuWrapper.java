package org.petri.nets.gui.panel;

import org.petri.nets.model.DomainModel;

import javax.swing.*;
import java.awt.*;

public class SideMenuWrapper extends JScrollPane{

    public static final int MENU_WIDTH = 220;

    public SideMenuWrapper(DomainModel domainModel) {

        super(new SideMenuPanel(domainModel));
        setPreferredSize(new Dimension(MENU_WIDTH,500));
    }

}
