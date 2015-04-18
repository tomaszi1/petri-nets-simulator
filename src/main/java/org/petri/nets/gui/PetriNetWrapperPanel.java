package org.petri.nets.gui;

import org.jgraph.JGraph;

import javax.swing.*;
import java.awt.*;

public class PetriNetWrapperPanel extends JPanel{

    private final PetriNetGraphPanel petriNetGraphPanel;
    private final InitialMarkingPanel initialMarkingPanel;

    public PetriNetWrapperPanel() {
        setLayout(new BorderLayout());

        petriNetGraphPanel = new PetriNetGraphPanel(new JGraph());
        initialMarkingPanel = new InitialMarkingPanel();

        add(petriNetGraphPanel, BorderLayout.CENTER);
        add(initialMarkingPanel, BorderLayout.PAGE_END);
    }
}
