package org.petri.nets.gui.panel;

import org.petri.nets.model.DomainModel;
import org.petri.nets.service.GraphService;

import javax.swing.*;
import java.awt.*;

public class SideMenuWrapper extends JScrollPane{

    public static final int MENU_WIDTH = 220;

    public SideMenuWrapper(GraphService graphService) {

        super(new SideMenuPanel(graphService));
        setPreferredSize(new Dimension(MENU_WIDTH,500));
    }

}
