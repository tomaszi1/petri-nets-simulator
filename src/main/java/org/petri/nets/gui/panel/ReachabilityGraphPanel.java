package org.petri.nets.gui.panel;

import edu.uci.ics.jung.algorithms.layout.*;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.EdgeShape;
import edu.uci.ics.jung.visualization.renderers.VertexLabelAsShapeRenderer;
import org.apache.commons.collections15.Transformer;
import org.petri.nets.model.DomainModel;
import org.petri.nets.model.Transition;
import org.petri.nets.utils.AbstractResizeComponentListener;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ComponentEvent;
import java.util.HashMap;

public class ReachabilityGraphPanel extends JPanel {
    public static final String PANEL_TITLE = "Graf pokrycia";
    public static final Color VERTEX_FILL_COLOR = new Color(255, 255, 152);
    public static final Color GRAPH_BACKGROUND = Color.WHITE;
    public static final Font FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 20);


    private DomainModel domainModel;
    private VisualizationViewer<HashMap<Integer, Integer>, Transition> visualizationViewer;

    public ReachabilityGraphPanel(DomainModel domainModel) {
        setLayout(new BorderLayout());
        this.domainModel = domainModel;

//        setViewportView(graph);

        add(createGraphComponent(), BorderLayout.CENTER);

        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), PANEL_TITLE));
    }

    private JComponent createGraphComponent() {
        DirectedSparseGraph<HashMap<Integer, Integer>, Transition> graph = new DirectedSparseGraph<>();

        Layout<HashMap<Integer, Integer>, Transition> layout = new DAGLayout<>(graph);

        visualizationViewer = new VisualizationViewer<>(layout);
        DefaultModalGraphMouse gm = new DefaultModalGraphMouse();
        gm.setMode(ModalGraphMouse.Mode.TRANSFORMING);
        visualizationViewer.setGraphMouse(gm);

        VertexLabelAsShapeRenderer<HashMap<Integer, Integer>, Transition> vertexRenderer = new VertexLabelAsShapeRenderer<>(visualizationViewer.getRenderContext());

        // vertices
        visualizationViewer.getRenderContext().setVertexLabelTransformer(map -> {
            StringBuilder sb = new StringBuilder();
            for (Integer marking : map.values()) {
                sb.append(marking).append(",");
            }
            sb.setLength(sb.length() - 1);
            return sb.toString();
        });
        visualizationViewer.getRenderContext().setVertexShapeTransformer(vertexRenderer);
        visualizationViewer.getRenderContext().setVertexFillPaintTransformer(s -> VERTEX_FILL_COLOR);
        visualizationViewer.getRenderContext().setVertexFontTransformer(s -> FONT);
        visualizationViewer.getRenderer().setVertexLabelRenderer(vertexRenderer);

        // edges
        visualizationViewer.getRenderContext().setEdgeLabelTransformer(transition -> "T" + transition.getIdTransition());
        visualizationViewer.getRenderContext().setEdgeShapeTransformer(new EdgeShape.Line<>());
        visualizationViewer.getRenderContext().getEdgeLabelRenderer().setRotateEdgeLabels(false);
        visualizationViewer.getRenderContext().setEdgeFontTransformer(s -> FONT);

        visualizationViewer.setBackground(GRAPH_BACKGROUND);

        addComponentListener(new AbstractResizeComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension size = e.getComponent().getSize();
                layout.setSize(size);
            }
        });
        return visualizationViewer;
    }

    public void setLayout(Layout<HashMap<Integer, Integer>, Transition> layout) {
        visualizationViewer.setGraphLayout(layout);
    }

    public void updateGraph() {
        Layout<HashMap<Integer, Integer>, Transition> graphLayout = visualizationViewer.getGraphLayout();
        graphLayout.setGraph(domainModel.getReachabilityGraph());
        graphLayout.reset();
    }

}
