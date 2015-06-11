package org.petri.nets.gui.graph.reachability;

import org.petri.nets.gui.panel.ReachabilityGraphPanel;
import org.petri.nets.model.reachability.State;
import org.petri.nets.service.GraphService;
import org.petri.nets.utils.Listener;

import java.util.Map;

/**
 * Created by Tomasz on 2015-06-07.
 */
public class StatePickedListener implements Listener<State> {
    private GraphService graphService;

    public StatePickedListener(GraphService graphService) {
        this.graphService = graphService;
    }

    @Override
    public void publish(State state) {
        graphService.displayMarkingOnGraph(state == null ? null : state.getMarking());
    }
}
