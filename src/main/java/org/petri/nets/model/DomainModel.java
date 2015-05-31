package org.petri.nets.model;

import com.google.common.collect.BiMap;
import org.jgraph.JGraph;
import org.jgraph.graph.GraphLayoutCache;
import org.petri.nets.gui.graph.PlaceGraphCell;
import org.petri.nets.gui.graph.TransitionGraphCell;

import java.io.Serializable;

public class DomainModel implements Serializable {
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
