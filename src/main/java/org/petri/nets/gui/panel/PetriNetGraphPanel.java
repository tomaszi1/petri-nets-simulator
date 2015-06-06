package org.petri.nets.gui.panel;

import org.jgraph.JGraph;
import org.jgraph.plaf.GraphUI;
import org.petri.nets.service.GraphService;
import org.petri.nets.synhronize.SynchronizePanel;

import javax.swing.*;

public class PetriNetGraphPanel extends JScrollPane {
    public static final String PANEL_TITLE = "Sieć Petriego";

    private JGraph graph;

    public PetriNetGraphPanel(GraphService graphService, GraphUI graphUI, SynchronizePanel synchronizePanel) {
        graph = graphService.getPetriNetGraph();
        setViewportView(graph);
        graph.setEditable(false);
        graph.setUI(graphUI);

        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), PANEL_TITLE));

    }


}