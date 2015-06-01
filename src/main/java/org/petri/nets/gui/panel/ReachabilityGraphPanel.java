package org.petri.nets.gui.panel;

import org.jgraph.JGraph;
import org.jgraph.graph.DefaultGraphModel;
import org.petri.nets.model.DomainModel;

import javax.swing.*;
import javax.swing.border.Border;

public class ReachabilityGraphPanel extends JScrollPane {
    private static final String PANEL_TITLE = "Graf osiągalności";

    private final DomainModel domainModel;

    public ReachabilityGraphPanel(DomainModel domainModel) {
        this.domainModel = domainModel;

        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), PANEL_TITLE));
    }
}
