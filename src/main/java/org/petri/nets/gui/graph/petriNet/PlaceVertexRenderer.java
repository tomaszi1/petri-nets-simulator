package org.petri.nets.gui.graph.petriNet;

import org.jgraph.graph.CellView;
import org.jgraph.graph.VertexRenderer;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class PlaceVertexRenderer extends VertexRenderer {
    private String tooltip;

    @Override
    protected void installAttributes(CellView view) {
        tooltip = ((PlaceGraphCell)view.getCell()).getDescription();
        super.installAttributes(view);
    }

    @Override
    public String getToolTipText() {
        return tooltip==null ? null : "<html><font size=+1>"+tooltip+"</font></html>";
    }

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
        String[] split = getText().split("\\|");
        if (split.length == 1) {
            Rectangle2D strBounds = metrics.getStringBounds(getText(), g2);
            int x = (getWidth() - (int) strBounds.getWidth()) / 2;
            int y = (getHeight() - (int) strBounds.getHeight()) / 2 + metrics.getAscent();
            g2.setPaint(getForeground());
            g2.setFont(getFont());
            g2.drawString(getText(), x, y);
        } else if (split.length == 2) {
            String name = split[0];
            String marking = split[1];

            g2.setPaint(getForeground());
            g2.setFont(getFont());

            Rectangle2D nameBounds = metrics.getStringBounds(name, g2);
            int nameX = (getWidth() - (int) nameBounds.getWidth()) / 2;
            int nameY = (getHeight() - (int) nameBounds.getHeight()) / 4 + metrics.getAscent();
            g2.drawString(name, nameX, nameY);

            Rectangle2D markingBounds = metrics.getStringBounds(marking, g2);
            int markingX = (getWidth() - (int) markingBounds.getWidth()) / 2;
            int markingY = 3 * (getHeight() - (int) markingBounds.getHeight()) / 4 + metrics.getAscent();
            g2.setPaint(Color.BLUE);
            g2.drawString(marking.equals("-1") ? "\u221E" : marking, markingX, markingY);
        }
    }
}
