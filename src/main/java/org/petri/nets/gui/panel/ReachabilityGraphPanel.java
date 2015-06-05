package org.petri.nets.gui.panel;

import com.google.common.collect.Maps;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.EdgeShape;
import edu.uci.ics.jung.visualization.renderers.VertexLabelAsShapeRenderer;
import org.petri.nets.model.reachability.State;
import org.petri.nets.model.reachability.TransitionEdge;
import org.petri.nets.service.GraphService;
import org.petri.nets.utils.AbstractResizeComponentListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.geom.Point2D;
import java.util.Map;

public class ReachabilityGraphPanel extends JPanel {
    public static final String PANEL_TITLE = "Graf pokrycia";
    public static final Color VERTEX_FILL_COLOR = new Color(255, 255, 152);
    public static final Color GRAPH_BACKGROUND = Color.WHITE;
    public static final Font FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 20);

    private GraphService graphService;


    private VisualizationViewer<State, TransitionEdge> visualizationViewer;

    public ReachabilityGraphPanel(GraphService graphService) {
        this.graphService = graphService;
        setLayout(new BorderLayout());

        add(createGraphComponent(), BorderLayout.CENTER);

        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), PANEL_TITLE));
    }

    private JComponent createGraphComponent() {
        DirectedSparseGraph<State, TransitionEdge> graph = new DirectedSparseGraph<>();

        Layout<State, TransitionEdge> layout = new StaticLayout<>(graph, new CustomInitializer());

//        layout.setInitializer();
//        layout.setStretch(0.1);
//        layout.setRepulsionRange(500);
//        layout.setForceMultiplier(0.1);

        visualizationViewer = new VisualizationViewer<>(layout);
        DefaultModalGraphMouse gm = new DefaultModalGraphMouse();
        gm.setMode(ModalGraphMouse.Mode.TRANSFORMING);
        visualizationViewer.setGraphMouse(gm);

        VertexLabelAsShapeRenderer<State, TransitionEdge> vertexRenderer = new VertexLabelAsShapeRenderer<>(visualizationViewer.getRenderContext());

        // vertices
        visualizationViewer.getRenderContext().setVertexLabelTransformer(state -> {
            StringBuilder sb = new StringBuilder();
            for (Integer marking : state.getMarking().values()) {
                sb.append(marking).append(",");
            }
            if (sb.length() > 0) sb.setLength(sb.length() - 1);
            return sb.toString();
        });
        visualizationViewer.getRenderContext().setVertexShapeTransformer(vertexRenderer);
        visualizationViewer.getRenderContext().setVertexFillPaintTransformer(s -> VERTEX_FILL_COLOR);
        visualizationViewer.getRenderContext().setVertexFontTransformer(s -> FONT);
        visualizationViewer.getRenderer().setVertexLabelRenderer(vertexRenderer);

        // edges
        visualizationViewer.getRenderContext().setEdgeLabelTransformer(transition -> "T" + transition.getTransition().getId());
        visualizationViewer.getRenderContext().setEdgeShapeTransformer(new EdgeShape.QuadCurve<>());
        visualizationViewer.getRenderContext().setEdgeFontTransformer(s -> FONT);
        visualizationViewer.getRenderContext().getEdgeLabelRenderer().setRotateEdgeLabels(false);

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

    public void updateGraph() {
        Layout<State, TransitionEdge> graphLayout = visualizationViewer.getGraphLayout();
        graphLayout.setInitializer(new CustomInitializer());
        graphLayout.setGraph(graphService.getReachabilityGraph());
        graphLayout.reset();
        visualizationViewer.repaint();
    }

    private class CustomInitializer implements org.apache.commons.collections15.Transformer<State, Point2D> {
        private Map<Integer, Integer> verticesCountAtDepth = Maps.newHashMap();
        private final int distance = 200;

        @Override
        public Point2D transform(State state) {
            int depth = state.getDepth();
            verticesCountAtDepth.putIfAbsent(depth, 0);

            int posX = 100 * verticesCountAtDepth.get(depth);
            int posY = distance * depth;

            verticesCountAtDepth.put(depth, verticesCountAtDepth.get(depth) + 1);
            return new Point2D.Double(posX, posY);
        }
    }
}
