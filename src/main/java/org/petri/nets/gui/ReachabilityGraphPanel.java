package org.petri.nets.gui;

import org.jgraph.JGraph;
import org.petri.nets.model.Model;

import javax.swing.*;
import javax.swing.border.Border;

public class ReachabilityGraphPanel extends JScrollPane {
    private static final String PANEL_TITLE = "Graf osiągalności";

    private final Model model;
    private final JGraph graph;

    public ReachabilityGraphPanel(Model model) {
        this.model = model;
        graph = new JGraph(model.getReachabilityGraphModel());

        setViewportView(graph);

        Border etchedBorder = BorderFactory.createEtchedBorder();
        setBorder(BorderFactory.createTitledBorder(etchedBorder, PANEL_TITLE));
    }
}
