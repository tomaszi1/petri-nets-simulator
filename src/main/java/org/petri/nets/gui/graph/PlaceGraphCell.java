package org.petri.nets.gui.graph;

import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class PlaceGraphCell extends DefaultGraphCell{
    public PlaceGraphCell(int id) {
        super("P" + id);
        
        GraphConstants.setBounds(getAttributes(),
                new Rectangle2D.Double(20, 20, 40, 40));
        GraphConstants.setSizeable(getAttributes(), false);
        GraphConstants.setOpaque(getAttributes(), true);
        GraphConstants.setBackground(getAttributes(), Color.ORANGE);
    }
}
