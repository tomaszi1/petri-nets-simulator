package org.petri.nets.gui.panel;

import org.petri.nets.model.DomainModel;
import org.petri.nets.service.GraphService;
import org.petri.nets.synhronize.SynchronizePanel;

import javax.swing.*;
import java.awt.*;

public class PetriNetWrapperPanel extends JPanel{

    private final PetriNetGraphPanel petriNetGraphPanel;
    private final InitialMarkingPanel initialMarkingPanel;

    public PetriNetWrapperPanel(GraphService graphService,SynchronizePanel synchronizePanel) {
        setLayout(new BorderLayout());

        initialMarkingPanel = new InitialMarkingPanel(graphService);
        petriNetGraphPanel = new PetriNetGraphPanel(graphService, synchronizePanel);

        add(petriNetGraphPanel, BorderLayout.CENTER);
        add(getInitialMarkingPanel(), BorderLayout.PAGE_END);
    }

    public InitialMarkingPanel getInitialMarkingPanel() {
        return initialMarkingPanel;
    }
}
