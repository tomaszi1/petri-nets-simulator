package org.petri.nets.gui.graph;

import org.jgraph.JGraph;
import org.jgraph.graph.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.Map;

public class CustomVertexRenderer extends VertexRenderer {

    @Override
    public void paint(Graphics g) {
        try {
            if (gradientColor != null && !preview && isOpaque()) {
                setOpaque(false);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setPaint(new GradientPaint(0, 0, getBackground(),
                        getWidth(), getHeight(), gradientColor, true));
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
            paintVertex(g);
            paintSelectionBorder(g);
        } catch (IllegalArgumentException e) {
            // JDK Bug: Zero length string passed to TextLayout constructor
        }
    }

    public void paintVertex(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(getBackground());
        g2.fillOval(0, 0, getWidth(), getHeight());
        FontMetrics metrics = g2.getFontMetrics();
        Rectangle2D strBounds = metrics.getStringBounds(getText(), g2);
        int x = (getWidth() - (int) strBounds.getWidth()) / 2;
        int y = (getHeight() - (int) strBounds.getHeight()) / 2 + metrics.getAscent();
        g2.setPaint(getForeground());
        g2.setFont(getFont());
        g2.drawString(getText(), x, y);
    }
}
