package org.petri.nets.gui.graph.reachability;

import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.GraphMouseListener;
import edu.uci.ics.jung.visualization.picking.PickedState;
import org.petri.nets.gui.panel.ReachabilityGraphPanel;
import org.petri.nets.model.reachability.State;
import org.petri.nets.model.reachability.TransitionEdge;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Tomasz on 2015-06-06.
 */
public class ReachGraphMouseListener implements GraphMouseListener<State>, MouseListener {

    private VisualizationViewer<State, TransitionEdge> visualizationViewer;
    private StatePickedListener listener;
    private ReachabilityGraphPanel graphPanel;

    public ReachGraphMouseListener(VisualizationViewer<State, TransitionEdge> visualizationViewer, StatePickedListener listener, ReachabilityGraphPanel graphPanel) {
        this.visualizationViewer = visualizationViewer;
        this.listener = listener;
        this.graphPanel = graphPanel;
    }

    @Override
    public void graphClicked(State state, MouseEvent me) {
        PickedState<State> pickedVertexState = visualizationViewer.getPickedVertexState();
        pickedVertexState.clear();
        pickedVertexState.pick(state, true);
        listener.publish(state);
        graphPanel.updateColoring();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        visualizationViewer.getPickedVertexState().clear();
        listener.publish(null);
        graphPanel.updateColoring();
    }

    @Override
    public void graphPressed(State state, MouseEvent me) {

    }

    @Override
    public void graphReleased(State state, MouseEvent me) {

    }


    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
