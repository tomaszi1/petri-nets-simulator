package org.petri.nets.gui.panel;

import com.google.common.collect.Sets;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.algorithms.shortestpath.BFSDistanceLabeler;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.EdgeShape;
import edu.uci.ics.jung.visualization.picking.PickedState;
import edu.uci.ics.jung.visualization.renderers.VertexLabelAsShapeRenderer;
import org.petri.nets.model.reachability.State;
import org.petri.nets.model.reachability.TransitionEdge;
import org.petri.nets.service.GraphService;
import org.petri.nets.service.ReachabilityGraphGenerator;
import org.petri.nets.utils.AbstractResizeComponentListener;
import org.petri.nets.gui.graph.reachability.CustomEdgeLabelRenderer;
import org.petri.nets.gui.graph.reachability.CustomInitializer;
import org.petri.nets.gui.graph.reachability.GraphMouseClickListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.util.HashSet;
import java.util.Set;

public class ReachabilityGraphPanel extends JPanel {
    public static final String PANEL_TITLE = "Graf pokrycia";
    public static final Color VERTEX_FILL_COLOR = new Color(255, 255, 120);
    public static final Color ACTIVE_VERTEX_FILL_COLOR = new Color(128, 255, 0);
    public static final Color INACTIVE_VERTEX_FILL_COLOR = new Color(255, 255, 180);

    public static final Color EDGE_FILL_COLOR = new Color(30, 30, 30);
    public static final Color INACTIVE_EDGE_FILL_COLOR = new Color(230, 230, 230);

    public static final Color GRAPH_BACKGROUND = Color.WHITE;
    public static final Font FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 20);

    private GraphService graphService;

    private VisualizationViewer<State, TransitionEdge> visualizationViewer;
    private BFSDistanceLabeler<State, TransitionEdge> bfsDistanceLabeler;

    public ReachabilityGraphPanel(GraphService graphService) {
        this.graphService = graphService;
        setLayout(new BorderLayout());
        bfsDistanceLabeler = new BFSDistanceLabeler<>();
        add(createGraphComponent(), BorderLayout.CENTER);

        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), PANEL_TITLE));
    }

    private JComponent createGraphComponent() {

        Graph<State, TransitionEdge> graph = new DirectedSparseGraph<>();

        Layout<State, TransitionEdge> layout = new StaticLayout<>(graph, new CustomInitializer());

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
        visualizationViewer.getRenderContext().setVertexFillPaintTransformer(s -> {
            if (!visualizationViewer.getPickedVertexState().getPicked().isEmpty())
                return visualizationViewer.getPickedVertexState().isPicked(s) ? ACTIVE_VERTEX_FILL_COLOR : INACTIVE_VERTEX_FILL_COLOR;
            return VERTEX_FILL_COLOR;
        });
        visualizationViewer.getRenderContext().setVertexFontTransformer(s -> FONT);
        visualizationViewer.getRenderer().setVertexLabelRenderer(vertexRenderer);

        // edges
        visualizationViewer.getRenderContext().setEdgeLabelTransformer(transition -> "T" + transition.getTransition().getId());
        visualizationViewer.getRenderContext().setEdgeShapeTransformer(new EdgeShape.QuadCurve<>());
        visualizationViewer.getRenderContext().setEdgeDrawPaintTransformer(this::edgeColor);
        visualizationViewer.getRenderContext().setArrowDrawPaintTransformer(this::edgeColor);
        visualizationViewer.getRenderContext().setEdgeFontTransformer(s -> FONT);
        visualizationViewer.getRenderContext().getEdgeLabelRenderer().setRotateEdgeLabels(false);
        CustomEdgeLabelRenderer<State, TransitionEdge> customEdgeLabelRenderer = new CustomEdgeLabelRenderer<>(this::edgeColor);
        visualizationViewer.getRenderer().setEdgeLabelRenderer(customEdgeLabelRenderer);

        visualizationViewer.setBackground(GRAPH_BACKGROUND);

        addComponentListener(new AbstractResizeComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension size = e.getComponent().getSize();
                layout.setSize(size);
            }
        });

        GraphMouseClickListener graphMouseListener = new GraphMouseClickListener(visualizationViewer);
        visualizationViewer.addMouseListener(graphMouseListener);
        visualizationViewer.addGraphMouseListener(graphMouseListener);

        return visualizationViewer;
    }

    public void updateGraph() {
        if (graphService == null || graphService.getReachabilityGraph() == null)
            return;
        Layout<State, TransitionEdge> graphLayout = visualizationViewer.getGraphLayout();
        graphLayout.setInitializer(new CustomInitializer());
        Graph<State, TransitionEdge> graph = graphService.getReachabilityGraph();
        bfsDistanceLabeler.labelDistances(graph, ReachabilityGraphGenerator.findRoot(graph));
        graphLayout.setGraph(graph);
        visualizationViewer.getPickedVertexState().clear();
        visualizationViewer.repaint();
    }

    public Set<State> gatherAllPredecessors(State endPoint) {
        Set<State> predecessors = Sets.newHashSet(bfsDistanceLabeler.getPredecessors(endPoint));
        for (State predecessor : predecessors) {
            Set<State> preds = gatherAllPredecessors(predecessor);
            predecessors.addAll(preds);
        }
        return predecessors;
    }

    public Color edgeColor(TransitionEdge transitionEdge) {
        PickedState<State> pickedVertexState = visualizationViewer.getPickedVertexState();
        Set<State> pickedStates = pickedVertexState.getPicked();
        if (pickedStates.isEmpty())
            return EDGE_FILL_COLOR;
        State picked = pickedStates.iterator().next();

        Set<State> predecessors = gatherAllPredecessors(picked);
        predecessors.add(picked);
        Set<State> incidentVertices = new HashSet<>(visualizationViewer.getGraphLayout().getGraph().getIncidentVertices(transitionEdge));

        if (Sets.intersection(incidentVertices, predecessors).size() == 2)
            return EDGE_FILL_COLOR;
        return INACTIVE_EDGE_FILL_COLOR;
    }

}
