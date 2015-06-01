package org.petri.nets.gui.panel;

import org.jgraph.JGraph;
import org.jgraph.graph.GraphLayoutCache;
import org.petri.nets.gui.graph.PetriNetGraphUI;
import org.petri.nets.model.DomainModel;
import org.petri.nets.synhronize.SynchronizePanel;

import javax.swing.*;

public class PetriNetGraphPanel extends JScrollPane {
    public static final String PANEL_TITLE = "Sieć Petriego";

    private JGraph graph;

    public PetriNetGraphPanel(DomainModel domainModel, SynchronizePanel synchronizePanel) {
        graph = domainModel.getPetriNetGraph();
        setViewportView(graph);
        graph.setEditable(false);
        graph.setUI(new PetriNetGraphUI(domainModel, synchronizePanel));

        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), PANEL_TITLE));

    }


}