package org.petri.nets.gui.graph;

import org.jgraph.graph.DefaultCellViewFactory;
import org.jgraph.graph.VertexView;

public class CustomCellViewFactory extends DefaultCellViewFactory {
    @Override
    protected VertexView createVertexView(Object cell) {
        if (cell instanceof PlaceGraphCell)
            return new CustomVertexView(cell);
        else return new VertexView(cell);
    }
}
