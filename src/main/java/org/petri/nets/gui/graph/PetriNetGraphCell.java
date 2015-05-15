package org.petri.nets.gui.graph;

import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class PetriNetGraphCell extends DefaultGraphCell {

    private static final int CELL_WIDTH = 40;
    private static final int CELL_HEIGHT = 40;

    public PetriNetGraphCell(String text, Point position) {
        this(text, (int) position.getX(), (int) position.getY());
    }

    public PetriNetGraphCell(String text, int posX, int posY) {
        super(text);
        GraphConstants.setBounds(getAttributes(),
                new Rectangle2D.Double(posX - CELL_HEIGHT / 2, posY - CELL_HEIGHT / 2, CELL_WIDTH, CELL_HEIGHT));
        GraphConstants.setSizeable(getAttributes(), false);
        GraphConstants.setOpaque(getAttributes(), true);
        addPort();
    }
}
