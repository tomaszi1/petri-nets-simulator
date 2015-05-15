package org.petri.nets.gui.graph;


import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.GraphConstants;

public class ArcGraphCell extends DefaultEdge{
    public ArcGraphCell() {
        GraphConstants.setLineEnd(getAttributes(), GraphConstants.ARROW_CLASSIC);
    }
}
