package org.petri.nets.gui.graph;

import org.jgraph.JGraph;
import org.jgraph.graph.DefaultCellViewFactory;
import org.jgraph.graph.DefaultGraphModel;
import org.jgraph.graph.GraphLayoutCache;

public class JGraphFactory {

    public static JGraph createGraph() {
        DefaultGraphModel defaultGraphModel = new DefaultGraphModel();
        GraphLayoutCache graphLayoutCache  = new GraphLayoutCache(defaultGraphModel, new CustomCellViewFactory());
        return new JGraph(defaultGraphModel, graphLayoutCache);
    }
}
