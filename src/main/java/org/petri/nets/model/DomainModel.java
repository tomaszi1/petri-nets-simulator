package org.petri.nets.model;

import org.jgraph.graph.GraphLayoutCache;

public class DomainModel {
    private GraphLayoutCache petriNetGraphLayoutCache;
    private GraphLayoutCache reachabilityGraphLayoutCache;
    private PetriNet petriNet;

    public GraphLayoutCache getPetriNetGraphLayoutCache() {
        return petriNetGraphLayoutCache;
    }

    public void setPetriNetGraphLayoutCache(GraphLayoutCache petriNetGraphLayoutCache) {
        this.petriNetGraphLayoutCache = petriNetGraphLayoutCache;
    }

    public GraphLayoutCache getReachabilityGraphLayoutCache() {
        return reachabilityGraphLayoutCache;
    }

    public void setReachabilityGraphLayoutCache(GraphLayoutCache reachabilityGraphLayoutCache) {
        this.reachabilityGraphLayoutCache = reachabilityGraphLayoutCache;
    }

    public PetriNet getPetriNet() {
        return petriNet;
    }

    public void setPetriNet(PetriNet petriNet) {
        this.petriNet = petriNet;
    }
}
