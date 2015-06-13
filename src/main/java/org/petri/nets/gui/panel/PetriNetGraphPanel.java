package org.petri.nets.gui.panel;

import org.jgraph.JGraph;
import org.petri.nets.service.GraphService;

import javax.swing.*;

public class PetriNetGraphPanel extends JScrollPane {
    public static final String PANEL_TITLE = "Sieć Petriego";

    private JGraph graph;

    public PetriNetGraphPanel(GraphService graphService) {
        graph = graphService.getPetriNetGraph();
        setViewportView(graph);

        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), PANEL_TITLE));

    }


}