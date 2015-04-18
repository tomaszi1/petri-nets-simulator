package org.petri.nets.gui;

import org.jgraph.JGraph;
import org.petri.nets.model.Model;

import javax.swing.*;
import java.awt.*;

public class PetriNetWrapperPanel extends JPanel{

    private final PetriNetGraphPanel petriNetGraphPanel;
    private final InitialMarkingPanel initialMarkingPanel;

    public PetriNetWrapperPanel(Model model) {
        setLayout(new BorderLayout());
        
        petriNetGraphPanel = new PetriNetGraphPanel(model);
        initialMarkingPanel = new InitialMarkingPanel();

        add(petriNetGraphPanel, BorderLayout.CENTER);
        add(initialMarkingPanel, BorderLayout.PAGE_END);
    }
}
