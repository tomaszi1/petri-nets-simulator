package org.petri.nets.model;

import com.google.common.collect.BiMap;
import edu.uci.ics.jung.graph.Graph;
import org.jgraph.JGraph;
import org.jgraph.graph.GraphLayoutCache;
import org.petri.nets.gui.graph.PlaceGraphCell;
import org.petri.nets.gui.graph.TransitionGraphCell;

import java.io.Serializable;
import java.util.HashMap;

public class DomainModel implements Serializable {
    private JGraph petriNetGraph;
    private Graph<HashMap<Integer, Integer>, Transition> reachabilityGraph;

    public JGraph getPetriNetGraph() {
        return petriNetGraph;
    }

    public void setPetriNetGraph(JGraph petriNetGraph) {
        this.petriNetGraph = petriNetGraph;
    }

    public Graph<HashMap<Integer, Integer>, Transition> getReachabilityGraph() {
        return reachabilityGraph;
    }

    public void setReachabilityGraph(Graph<HashMap<Integer, Integer>, Transition> reachabilityGraph) {
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
