package org.petri.nets.gui.graph.petriNet;

import org.jgraph.JGraph;
import org.jgraph.graph.DefaultGraphModel;
import org.jgraph.graph.GraphLayoutCache;

public class JGraphFactory {

    public static JGraph createGraph() {
        DefaultGraphModel defaultGraphModel = new DefaultGraphModel();
        GraphLayoutCache graphLayoutCache  = new GraphLayoutCache(defaultGraphModel, new CustomCellViewFactory());
        JGraph newGraph = new JGraph(defaultGraphModel, graphLayoutCache);
        newGraph.setDisconnectable(false);
        newGraph.setAntiAliased(true);
        return newGraph;
    }
}
