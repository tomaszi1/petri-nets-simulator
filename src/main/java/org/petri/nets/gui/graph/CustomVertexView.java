package org.petri.nets.gui.graph;

import org.jgraph.graph.CellViewRenderer;
import org.jgraph.graph.VertexRenderer;
import org.jgraph.graph.VertexView;

public class CustomVertexView extends VertexView {

    private static VertexRenderer vertexRenderer = new CustomVertexRenderer();

    @Override
    public CellViewRenderer getRenderer() {
        return vertexRenderer;
    }

    public CustomVertexView() {
        super();
    }

    public CustomVertexView(Object cell) {
        super(cell);
    }

}
