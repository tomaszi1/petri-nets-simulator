package org.petri.nets.gui;

import javax.swing.*;
import java.awt.*;

public class InitialMarkingPanel extends JPanel {
    public static final int PANEL_HEIGHT = 50;

    public InitialMarkingPanel() {
        setPreferredSize(new Dimension(100 /*ignored*/, PANEL_HEIGHT));

        add(new JLabel("Znakowanie początkowe"));
    }
}
