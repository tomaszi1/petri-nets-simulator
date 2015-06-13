package org.petri.nets.model;

import edu.uci.ics.jung.graph.Graph;
import org.jgraph.JGraph;
import org.jgraph.graph.GraphLayoutCache;
import org.petri.nets.model.reachability.State;
import org.petri.nets.model.reachability.TransitionEdge;

import java.io.Serializable;

public class DomainModel implements Serializable {
    private transient JGraph petriNetGraph;
    private transient Graph<State, TransitionEdge> reachabilityGraph;
    private GraphLayoutCache graphLayoutCache;
    private PetriNet petriNet;
    private SyncModelGUI syncModel = new SyncModelGUI();



    public JGraph getPetriNetGraph() {
        return petriNetGraph;
    }

    public void setPetriNetGraph(JGraph petriNetGraph) {
        graphLayoutCache = petriNetGraph.getGraphLayoutCache();
        this.petriNetGraph = petriNetGraph;
    }

    public Graph<State, TransitionEdge> getReachabilityGraph() {
        return reachabilityGraph;
    }

    public void setReachabilityGraph(Graph<State, TransitionEdge> reachabilityGraph) {
        this.reachabilityGraph = reachabilityGraph;
    }

    public PetriNet getPetriNet() {
        return petriNet;
    }

    public void setPetriNet(PetriNet petriNet) {
        this.petriNet = petriNet;
    }

    public SyncModelGUI getSyncModel() {
        return syncModel;
    }

    public void validate() {
        graphLayoutCache = petriNetGraph.getGraphLayoutCache();
    }

    public GraphLayoutCache getGraphLayoutCache() {
        return graphLayoutCache;
    }
}
