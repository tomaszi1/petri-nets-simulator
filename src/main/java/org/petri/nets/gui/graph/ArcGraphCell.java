package org.petri.nets.gui.graph;


import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.GraphConstants;

public class ArcGraphCell extends DefaultEdge{

    private int priority;
    private int value;
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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
