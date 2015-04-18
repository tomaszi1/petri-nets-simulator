package org.petri.nets.model;

import org.jgraph.graph.GraphModel;

public class DomainModel {
    private GraphModel petriNetGraphModel;
    private GraphModel reachabilityGraphModel;
    private PetriNet petriNet;

    public GraphModel getPetriNetGraphModel() {
        return petriNetGraphModel;
    }

    public void setPetriNetGraphModel(GraphModel petriNetGraphModel) {
        this.petriNetGraphModel = petriNetGraphModel;
    }

    public GraphModel getReachabilityGraphModel() {
        return reachabilityGraphModel;
    }

    public void setReachabilityGraphModel(GraphModel reachabilityGraphModel) {
        this.reachabilityGraphModel = reachabilityGraphModel;
    }

    public PetriNet getPetriNet() {
        return petriNet;
    }

    public void setPetriNet(PetriNet petriNet) {
        this.petriNet = petriNet;
    }
}
