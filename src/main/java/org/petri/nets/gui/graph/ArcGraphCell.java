package org.petri.nets.gui.graph;


import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.GraphConstants;

public class ArcGraphCell extends DefaultEdge{
    private PetriNetGraphCell start;
    private PetriNetGraphCell end;
    public ArcGraphCell() {
        GraphConstants.setLineEnd(getAttributes(), GraphConstants.ARROW_CLASSIC);
    }

    public PetriNetGraphCell getStart() {
        return start;
    }

    public void setStart(PetriNetGraphCell start) {
        this.start = start;
    }

    public PetriNetGraphCell getEnd() {
        return end;
    }

    public void setEnd(PetriNetGraphCell end) {
        this.end = end;
    }
}
