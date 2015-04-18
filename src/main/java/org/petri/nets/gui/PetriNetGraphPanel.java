package org.petri.nets.gui;

import org.jgraph.JGraph;
import org.petri.nets.model.Model;

import javax.swing.*;
import javax.swing.border.Border;

public class PetriNetGraphPanel extends JScrollPane {
    public static final String PANEL_TITLE = "Sieć Petriego";

    private JGraph graph;

    public PetriNetGraphPanel(Model model) {
        graph = new JGraph(model.getPetriNetGraphModel());

        setViewportView(graph);

        Border etchedBorder = BorderFactory.createEtchedBorder();
        setBorder(BorderFactory.createTitledBorder(etchedBorder, PANEL_TITLE));
    }
}