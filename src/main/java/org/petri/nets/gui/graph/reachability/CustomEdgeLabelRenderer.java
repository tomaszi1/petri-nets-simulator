package org.petri.nets.gui.graph.reachability;

import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.visualization.renderers.BasicEdgeLabelRenderer;
import edu.uci.ics.jung.visualization.renderers.EdgeLabelRenderer;
import org.apache.commons.collections15.Transformer;

import java.awt.*;

/**
 * Created by Tomasz on 2015-06-06.
 */
public class CustomEdgeLabelRenderer<V, E> extends BasicEdgeLabelRenderer<V, E> {
    private Transformer<E, Color> transformer;

    public CustomEdgeLabelRenderer(Transformer<E, Color> transformer) {
        this.transformer = transformer;
    }

    @Override
    public Component prepareRenderer(RenderContext<V, E> rc, EdgeLabelRenderer graphLabelRenderer, Object value, boolean isSelected, E edge) {
        Component component = super.prepareRenderer(rc, graphLabelRenderer, value, isSelected, edge);
        component.setForeground(transformer.transform(edge));
        return component;
    }
}
