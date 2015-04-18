package org.petri.nets.gui;

import org.jgraph.JGraph;
import org.jgraph.graph.*;
import org.jgraph.plaf.basic.BasicGraphUI;
import org.petri.nets.gui.graph.PlaceGraphCell;
import org.petri.nets.model.DomainModel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class PetriNetGraphPanel extends JScrollPane {
    public static final String PANEL_TITLE = "Sieć Petriego";

    private DomainModel domainModel;
    private GraphModel graphModel;
    private GraphLayoutCache graphLayoutCache;
    private JGraph graph;

    public PetriNetGraphPanel(DomainModel domainModel) {
        this.domainModel = domainModel;
        graphModel = domainModel.getPetriNetGraphModel();
        graphLayoutCache = new GraphLayoutCache(
                graphModel,
                new DefaultCellViewFactory());
        graph = new JGraph(graphModel, graphLayoutCache);

        setViewportView(graph);

        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), PANEL_TITLE));

        initGraph();
    }

    private void initGraph(){

        graphLayoutCache.insert(new PlaceGraphCell("P1"));
    }
}