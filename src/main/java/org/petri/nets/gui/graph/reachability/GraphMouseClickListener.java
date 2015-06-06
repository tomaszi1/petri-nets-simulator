package org.petri.nets.gui.graph.reachability;

import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.GraphMouseListener;
import edu.uci.ics.jung.visualization.picking.PickedState;
import org.petri.nets.model.reachability.State;
import org.petri.nets.model.reachability.TransitionEdge;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Tomasz on 2015-06-06.
 */
public class GraphMouseClickListener implements GraphMouseListener<State>, MouseListener {

    private VisualizationViewer<State, TransitionEdge> visualizationViewer;

    public GraphMouseClickListener(VisualizationViewer<State, TransitionEdge> visualizationViewer) {
        this.visualizationViewer = visualizationViewer;
    }

    @Override
    public void graphClicked(State state, MouseEvent me) {
        PickedState<State> pickedVertexState = visualizationViewer.getPickedVertexState();
        pickedVertexState.clear();
        pickedVertexState.pick(state, true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2)
            visualizationViewer.getPickedVertexState().clear();
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
