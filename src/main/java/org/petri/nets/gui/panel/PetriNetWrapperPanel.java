package org.petri.nets.gui.panel;

import org.petri.nets.service.GraphService;

import javax.swing.*;
import java.awt.*;

public class PetriNetWrapperPanel extends JPanel{

    private final InitialMarkingPanel initialMarkingPanel;

    public PetriNetWrapperPanel(GraphService graphService) {
        setLayout(new BorderLayout());

        initialMarkingPanel = new InitialMarkingPanel(graphService);

        PetriNetGraphPanel petriNetGraphPanel = new PetriNetGraphPanel(graphService);

        add(petriNetGraphPanel, BorderLayout.CENTER);
        add(getInitialMarkingPanel(), BorderLayout.PAGE_END);
    }

    public InitialMarkingPanel getInitialMarkingPanel() {
        return initialMarkingPanel;
    }
}
