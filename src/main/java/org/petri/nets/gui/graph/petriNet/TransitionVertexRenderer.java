package org.petri.nets.gui.graph.petriNet;

import org.jgraph.graph.CellView;
import org.jgraph.graph.VertexRenderer;

/**
 * Created by Tomasz on 2015-06-07.
 */
public class TransitionVertexRenderer extends VertexRenderer {
    private String tooltip;

    @Override
    protected void installAttributes(CellView view) {
        tooltip = ((TransitionGraphCell)view.getCell()).getDescription();
        super.installAttributes(view);
    }

    @Override
    public String getToolTipText() {
        return tooltip==null ? null : "<html><font size=+1>"+tooltip+"</font></html>";
    }

}
