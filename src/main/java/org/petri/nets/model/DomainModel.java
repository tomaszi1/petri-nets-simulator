package org.petri.nets.model;

import org.jgraph.JGraph;
import org.jgraph.graph.GraphLayoutCache;

public class DomainModel {
    private JGraph petriNetGraph;
    private JGraph reachabilityGraph;

    public JGraph getPetriNetGraph() {
        return petriNetGraph;
    }

    public void setPetriNetGraph(JGraph petriNetGraph) {
        this.petriNetGraph = petriNetGraph;
    }

    public JGraph getReachabilityGraph() {
        return reachabilityGraph;
    }

    public void setReachabilityGraph(JGraph reachabilityGraph) {
        this.reachabilityGraph = reachabilityGraph;
    }

    private PetriNet petriNet;

    public PetriNet getPetriNet() {
        return petriNet;
    }

    public void setPetriNet(PetriNet petriNet) {
        this.petriNet = petriNet;
    }
}
