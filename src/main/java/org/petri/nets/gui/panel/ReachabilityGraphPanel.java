package org.petri.nets.gui.panel;

import com.google.common.collect.Sets;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.algorithms.shortestpath.BFSDistanceLabeler;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.util.Pair;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.EdgeShape;
import edu.uci.ics.jung.visualization.picking.PickedState;
import edu.uci.ics.jung.visualization.renderers.VertexLabelAsShapeRenderer;
import org.petri.nets.gui.graph.reachability.ReachGraphEdgeLabelRenderer;
import org.petri.nets.gui.graph.reachability.ReachGraphInitializer;
import org.petri.nets.gui.graph.reachability.ReachGraphMouseListener;
import org.petri.nets.gui.graph.reachability.StatePickedListener;
import org.petri.nets.model.reachability.State;
import org.petri.nets.model.reachability.TransitionEdge;
import org.petri.nets.service.GraphService;
import org.petri.nets.service.ReachabilityGraphGenerator;
import org.petri.nets.utils.AbstractResizeComponentListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.util.*;

public class ReachabilityGraphPanel extends JPanel {
    public static final String PANEL_TITLE = "Graf pokrycia";
    public static final Color VERTEX_FILL_COLOR = new Color(255, 255, 120);
    public static final Color ACTIVE_VERTEX_FILL_COLOR = new Color(128, 255, 0);
    public static final Color INACTIVE_VERTEX_FILL_COLOR = new Color(255, 255, 180);

    public static final Color EDGE_FILL_COLOR = new Color(30, 30, 30);
    public static final Color INACTIVE_EDGE_FILL_COLOR = new Color(0, 0, 0, 20);

    public static final Color ALIVE_TRANSITION_COLOR = new Color(255, 0, 255);

    public static final Color GRAPH_BACKGROUND = Color.WHITE;
    public static final Font FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 20);

    private GraphService graphService;

    private VisualizationViewer<State, TransitionEdge> visualizationViewer;
    private BFSDistanceLabeler<State, TransitionEdge> bfsDistanceLabeler;
    private Map<TransitionEdge, Color> edgeColoring;

    public ReachabilityGraphPanel(GraphService graphService) {
        this.graphService = graphService;
        setLayout(new BorderLayout());
        bfsDistanceLabeler = new BFSDistanceLabeler<>();
        add(createGraphComponent(), BorderLayout.CENTER);
        edgeColoring = new HashMap<>();
        updateColoring();
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), PANEL_TITLE));
        updateGraph();
    }

    private JComponent createGraphComponent() {

        Graph<State, TransitionEdge> graph = new DirectedSparseGraph<>();

        Layout<State, TransitionEdge> layout = new StaticLayout<>(graph, new ReachGraphInitializer(graph.getVertices()));

        visualizationViewer = new VisualizationViewer<>(layout);
        DefaultModalGraphMouse gm = new DefaultModalGraphMouse();
        gm.setMode(ModalGraphMouse.Mode.TRANSFORMING);
        visualizationViewer.setGraphMouse(gm);

        VertexLabelAsShapeRenderer<State, TransitionEdge> vertexRenderer = new VertexLabelAsShapeRenderer<>(visualizationViewer.getRenderContext());

        // vertices
        visualizationViewer.getRenderContext().setVertexLabelTransformer(state -> {
            StringBuilder sb = new StringBuilder();
            for (Integer token : state.getMarking().values()) {
                sb.append(token == -1 ? "\u221E" : token).append(",");
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
        visualizationViewer.getRenderContext().setEdgeShapeTransformer(new EdgeShape.CubicCurve<>());
        visualizationViewer.getRenderContext().setEdgeDrawPaintTransformer(this::getEdgeColor);
        visualizationViewer.getRenderContext().setArrowDrawPaintTransformer(this::getEdgeColor);
        visualizationViewer.getRenderContext().setArrowFillPaintTransformer(this::getEdgeColor);
        visualizationViewer.getRenderContext().setEdgeFontTransformer(s -> FONT);
        visualizationViewer.getRenderContext().getEdgeLabelRenderer().setRotateEdgeLabels(false);
        ReachGraphEdgeLabelRenderer<State, TransitionEdge> reachGraphEdgeLabelRenderer = new ReachGraphEdgeLabelRenderer<>(this::getEdgeColor);
        visualizationViewer.getRenderer().setEdgeLabelRenderer(reachGraphEdgeLabelRenderer);

        visualizationViewer.setBackground(GRAPH_BACKGROUND);

        addComponentListener(new AbstractResizeComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension size = e.getComponent().getSize();
                layout.setSize(size);
            }
        });

        ReachGraphMouseListener graphMouseListener = new ReachGraphMouseListener(visualizationViewer, new StatePickedListener(graphService), this);
        visualizationViewer.addMouseListener(graphMouseListener);
        visualizationViewer.addGraphMouseListener(graphMouseListener);

        return visualizationViewer;
    }

    public void updateGraph() {
        if (graphService == null || graphService.getReachabilityGraph() == null)
            return;
        Graph<State, TransitionEdge> graph = graphService.getReachabilityGraph();
        visualizationViewer.setGraphLayout(new StaticLayout<>(graph, new ReachGraphInitializer(graph.getVertices())));
        bfsDistanceLabeler.labelDistances(graph, ReachabilityGraphGenerator.findRoot(graph));
        visualizationViewer.getPickedVertexState().clear();
        visualizationViewer.repaint();
    }

    public LinkedHashSet<State> gatherAllPredecessors(State endPoint) {
        LinkedHashSet<State> allPreds = Sets.newLinkedHashSet();
        allPreds.add(endPoint);

        Set<State> predecessors = bfsDistanceLabeler.getPredecessors(endPoint);
        LinkedHashSet<State> furtherPreds = Sets.newLinkedHashSet();

        for (State predecessor : predecessors)
            furtherPreds.addAll(gatherAllPredecessors(predecessor));
        allPreds.addAll(predecessors);
        allPreds.addAll(furtherPreds);
        return allPreds;
    }

    private Color calcEdgeColor(TransitionEdge edge, Color customAliveTransition) {
        State picked = getPickedState();
        if (picked == null)
            return EDGE_FILL_COLOR;

        LinkedHashSet<State> predecessors = gatherAllPredecessors(picked);
        Graph<State, TransitionEdge> graph = visualizationViewer.getGraphLayout().getGraph();
        Pair<State> endpoints = graph.getEndpoints(edge);

        if (graph.getOutEdges(picked).contains(edge))
            return customAliveTransition != null ? customAliveTransition : ALIVE_TRANSITION_COLOR;

        boolean startFound = false, endFound = false, startFirst = false;

        for (State state : predecessors) {
            if (!startFound && state.equals(endpoints.getFirst())) {
                startFound = true;
                startFirst = !endFound;
            }
            if (!endFound && state.equals(endpoints.getSecond())) {
                endFound = true;
                startFirst = startFound;
            }
            if (startFound && endFound) {
                if (!startFirst)
                    return EDGE_FILL_COLOR;
                break;
            }
        }

        return INACTIVE_EDGE_FILL_COLOR;
    }

    private State getPickedState() {
        PickedState<State> pickedVertexState = visualizationViewer.getPickedVertexState();
        Set<State> pickedStates = pickedVertexState.getPicked();
        return pickedStates.isEmpty() ? null : pickedStates.iterator().next();
    }


    public void updateColoring() {
        edgeColoring.clear();
        if (graphService.getReachabilityGraph() != null) {
            Collection<TransitionEdge> edges = graphService.getReachabilityGraph().getEdges();
            edges.forEach(edge -> edgeColoring.put(edge, calcEdgeColor(edge, null)));
        }
    }

    private Color getEdgeColor(TransitionEdge e) {
        return edgeColoring.getOrDefault(e, Color.BLACK);
    }
}
