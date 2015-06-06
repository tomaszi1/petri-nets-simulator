package org.petri.nets.service;

import edu.uci.ics.jung.graph.Graph;
import org.jgraph.JGraph;
import org.jgraph.graph.CellView;
import org.petri.nets.gui.graph.ArcGraphCell;
import org.petri.nets.gui.graph.PetriNetGraphCell;
import org.petri.nets.gui.graph.PlaceGraphCell;
import org.petri.nets.gui.graph.TransitionGraphCell;
import org.petri.nets.model.Arc;
import org.petri.nets.model.DomainModel;
import org.petri.nets.model.Place;
import org.petri.nets.model.reachability.State;
import org.petri.nets.model.Transition;
import org.petri.nets.model.reachability.TransitionEdge;

import java.awt.*;
import java.io.File;
import java.util.HashMap;

public interface GraphService {

    void removeFromGraph(Object cell);


    PlaceGraphCell addPlace(Point position);

    TransitionGraphCell addTransition(Point position);

    void addArc(PetriNetGraphCell start, PetriNetGraphCell end);

    void setInitialMarking(int placeId, int marking);

    CellView getLastFocusedCell();

    Object[] getSelectedCells();

    PlaceGraphCell tryCastToPlace(Object cell);

    TransitionGraphCell tryCastToTransition(Object cell);

    boolean isVertex(Object cell);

    PetriNetGraphCell[] getSelectedVertices();

    boolean isTransition(Object cell);

    boolean isPlace(Object cell);

    public void openGraphfromFile(File file);

    public void saveGraphAsFile(File file);

    HashMap<Integer, Integer> getInitialMarking();

    int getInitialMarking(int placeId);

    JGraph getPetriNetGraph();

    DomainModel getDomainModel();

    Graph<State, TransitionEdge> getReachabilityGraph();

    SynchronizeService getSynchronizeService();

    Place getModelRepresentative(PlaceGraphCell placeGraphCell);

    Transition getModelRepresentative(TransitionGraphCell transitionGraphCell);


    boolean isArc(Object cell);

    ArcGraphCell tryCastToArc(Object cell);

    Arc getModelRepresentative(ArcGraphCell arcGraphCell);
}
