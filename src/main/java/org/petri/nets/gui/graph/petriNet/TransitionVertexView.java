package org.petri.nets.gui.graph.petriNet;

import org.jgraph.JGraph;
import org.jgraph.graph.CellViewRenderer;
import org.jgraph.graph.VertexRenderer;
import org.jgraph.graph.VertexView;

import java.awt.*;

public class TransitionVertexView extends VertexView{
    // just default

    private static VertexRenderer vertexRenderer = new TransitionVertexRenderer();

    @Override
    public CellViewRenderer getRenderer() {
        return vertexRenderer;
    }

    public TransitionVertexView() {
        super();
    }

    public TransitionVertexView(Object cell) {
        super(cell);
    }
}
