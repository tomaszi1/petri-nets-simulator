package org.petri.nets.gui.graph;

import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class TransitionGraphCell extends DefaultGraphCell {
    public TransitionGraphCell(int id) {
        super("T" + id);
        GraphConstants.setBounds(getAttributes(), new Rectangle2D.Double(60, 60, 40, 40));
        GraphConstants.setOpaque(getAttributes(), true);
        GraphConstants.setBackground(getAttributes(), Color.CYAN);
        GraphConstants.setSizeable(getAttributes(), false);
    }
}
