package org.petri.nets.gui.graph.petriNet;

import org.jgraph.graph.GraphConstants;

import java.awt.*;

public class PlaceGraphCell extends PetriNetGraphCell {
    private static final Font FONT = new Font(Font.SANS_SERIF, Font.BOLD, 14);

    public PlaceGraphCell(int id, Point position) {
        this(id, (int) position.getX(), (int) position.getY());
    }

    public PlaceGraphCell(int id, int posX, int posY) {
        super("P" + id, posX, posY);
        GraphConstants.setBackground(getAttributes(), Color.ORANGE);
        GraphConstants.setFont(getAttributes(), FONT);
    }
}
