package org.petri.nets.gui;

import org.jgraph.JGraph;

import javax.swing.*;
import javax.swing.border.Border;

public class ReachabilityGraphPanel extends JScrollPane {

    private static final String PANEL_TITLE = "Graf osiągalności";

    private JGraph graph;

    public ReachabilityGraphPanel(JGraph graph) {
        super(graph);
        this.graph = graph;

        Border etchedBorder = BorderFactory.createEtchedBorder();
        setBorder(BorderFactory.createTitledBorder(etchedBorder, PANEL_TITLE));
    }
}
