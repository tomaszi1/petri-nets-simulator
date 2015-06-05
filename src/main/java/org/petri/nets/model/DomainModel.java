package org.petri.nets.model;

import edu.uci.ics.jung.graph.Graph;
import org.jgraph.JGraph;
import org.petri.nets.model.reachability.State;
import org.petri.nets.model.reachability.TransitionEdge;

import java.io.Serializable;

public class DomainModel implements Serializable {
    private JGraph petriNetGraph;
    private Graph<State, TransitionEdge> reachabilityGraph;

    public JGraph getPetriNetGraph() {
        return petriNetGraph;
    }

    public void setPetriNetGraph(JGraph petriNetGraph) {
        this.petriNetGraph = petriNetGraph;
    }

    public Graph<State, TransitionEdge> getReachabilityGraph() {
        return reachabilityGraph;
    }

    public void setReachabilityGraph(Graph<State, TransitionEdge> reachabilityGraph) {
        this.reachabilityGraph = reachabilityGraph;
    }

/////////////////////////

    private PetriNet petriNet;
    private SyncModelGUI syncModel = new SyncModelGUI();
    public PetriNet getPetriNet() {
        return petriNet;
    }

    public void setPetriNet(PetriNet petriNet) {
        this.petriNet = petriNet;
    }

    public SyncModelGUI getSyncModel() {
        return syncModel;
    }

    public void setSyncModel(SyncModelGUI syncModel) {
        this.syncModel = syncModel;
    }
}
