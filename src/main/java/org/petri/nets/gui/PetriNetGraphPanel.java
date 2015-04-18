package org.petri.nets.gui;

import org.jgraph.JGraph;

import javax.swing.*;
import javax.swing.border.Border;

public class PetriNetGraphPanel extends JScrollPane {
    public static final String PANEL_TITLE = "Sieć Petriego";

    private JGraph graph;

    public PetriNetGraphPanel(JGraph graph) {
        super(graph);
        this.graph = graph;

        Border etchedBorder = BorderFactory.createEtchedBorder();
        setBorder(BorderFactory.createTitledBorder(etchedBorder, PANEL_TITLE));
    }
}