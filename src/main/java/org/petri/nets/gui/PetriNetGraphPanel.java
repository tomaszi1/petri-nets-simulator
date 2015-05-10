package org.petri.nets.gui;

import org.jgraph.JGraph;
import org.jgraph.graph.DefaultCellViewFactory;
import org.jgraph.graph.DefaultGraphModel;
import org.jgraph.graph.GraphLayoutCache;
import org.jgraph.plaf.GraphUI;
import org.jgraph.plaf.basic.BasicGraphUI;
import org.petri.nets.gui.graph.PetriNetGraphUI;
import org.petri.nets.gui.graph.PlaceGraphCell;
import org.petri.nets.model.DomainModel;

import javax.swing.*;

public class PetriNetGraphPanel extends JScrollPane {
    public static final String PANEL_TITLE = "Sieć Petriego";

    private DomainModel domainModel;
    private GraphLayoutCache graphLayoutCache;
    private JGraph graph;

    public PetriNetGraphPanel(DomainModel domainModel) {
        this.domainModel = domainModel;
        graph = domainModel.getPetriNetGraph();
        setViewportView(graph);
        graph.setEditable(false);
        graph.setUI(new PetriNetGraphUI(domainModel));

        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), PANEL_TITLE));

    }

}