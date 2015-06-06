package org.petri.nets.gui.graph;


import edu.uci.ics.jung.graph.Graph;
import org.jgraph.graph.DefaultEdge;
import org.jgraph.graph.GraphConstants;

import java.awt.*;
import java.awt.geom.Point2D;

public class ArcGraphCell extends DefaultEdge {

    private static final Font FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 16);

    private PetriNetGraphCell start;
    private PetriNetGraphCell end;

    public ArcGraphCell() {
        GraphConstants.setLineEnd(getAttributes(), GraphConstants.ARROW_TECHNICAL);
        GraphConstants.setLabelPosition(getAttributes(), new Point2D.Double(GraphConstants.PERMILLE / 2, -10));
        GraphConstants.setFont(getAttributes(), FONT);
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
