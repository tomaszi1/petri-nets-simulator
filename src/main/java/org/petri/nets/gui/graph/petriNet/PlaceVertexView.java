package org.petri.nets.gui.graph.petriNet;

import org.jgraph.graph.CellViewRenderer;
import org.jgraph.graph.VertexRenderer;
import org.jgraph.graph.VertexView;

public class PlaceVertexView extends VertexView {

    private static VertexRenderer vertexRenderer = new PlaceVertexRenderer();

    @Override
    public CellViewRenderer getRenderer() {
        return vertexRenderer;
    }

    public PlaceVertexView() {
        super();
    }

    public PlaceVertexView(Object cell) {
        super(cell);
    }

}
