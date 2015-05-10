package org.petri.nets.gui.graph;

import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class TransitionGraphCell extends PetriNetGraphCell {
    public TransitionGraphCell(int id, int posX, int posY) {
        super("T" + id, posX, posY);
        GraphConstants.setBackground(getAttributes(), Color.CYAN);
    }
}
