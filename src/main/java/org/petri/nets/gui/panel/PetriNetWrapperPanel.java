package org.petri.nets.gui.panel;

import org.jgraph.plaf.GraphUI;
import org.petri.nets.gui.graph.PetriNetGraphUI;
import org.petri.nets.model.DomainModel;
import org.petri.nets.service.GraphService;
import org.petri.nets.synhronize.SynchronizePanel;

import javax.swing.*;
import java.awt.*;

public class PetriNetWrapperPanel extends JPanel{

    private final InitialMarkingPanel initialMarkingPanel;

    public PetriNetWrapperPanel(GraphService graphService, GraphUI graphUI, SynchronizePanel synchronizePanel) {
        setLayout(new BorderLayout());

        initialMarkingPanel = new InitialMarkingPanel(graphService);

        PetriNetGraphPanel petriNetGraphPanel = new PetriNetGraphPanel(graphService, graphUI, synchronizePanel);

        add(petriNetGraphPanel, BorderLayout.CENTER);
        add(getInitialMarkingPanel(), BorderLayout.PAGE_END);
    }

    public InitialMarkingPanel getInitialMarkingPanel() {
        return initialMarkingPanel;
    }
}
