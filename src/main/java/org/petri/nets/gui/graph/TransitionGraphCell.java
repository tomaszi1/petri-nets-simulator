package org.petri.nets.gui.graph;

import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class TransitionGraphCell extends PetriNetGraphCell {
    private static final Font FONT = new Font(Font.SANS_SERIF, Font.BOLD, 14);

    public TransitionGraphCell(int id, Point position) {
        this(id, (int) position.getX(), (int) position.getY());
    }

    public TransitionGraphCell(int id, int posX, int posY) {
        super("T" + id, posX, posY);
        GraphConstants.setBackground(getAttributes(), Color.CYAN);
        GraphConstants.setFont(getAttributes(), FONT);
    }

}
