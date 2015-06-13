package org.petri.nets.service;

import edu.uci.ics.jung.graph.Graph;
import org.jgraph.JGraph;
import org.petri.nets.gui.graph.petriNet.ArcGraphCell;
import org.petri.nets.gui.graph.petriNet.PetriNetGraphCell;
import org.petri.nets.gui.graph.petriNet.PlaceGraphCell;
import org.petri.nets.gui.graph.petriNet.TransitionGraphCell;
import org.petri.nets.model.Arc;
import org.petri.nets.model.DomainModel;
import org.petri.nets.model.Place;
import org.petri.nets.model.Transition;
import org.petri.nets.model.reachability.State;
import org.petri.nets.model.reachability.TransitionEdge;
import org.petri.nets.synhronize.SynchronizePanel;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public interface GraphService {

    SynchronizePanel getSynchronizePanel();

    void removeFromGraph(Object cell);

    PlaceGraphCell addPlace(Point position);

    TransitionGraphCell addTransition(Point position);

    void addArc(PetriNetGraphCell start, PetriNetGraphCell end);

    void setInitialMarking(int placeId, int marking);

    Object[] getSelectedCells();

    PlaceGraphCell tryCastToPlace(Object cell);

    TransitionGraphCell tryCastToTransition(Object cell);

    boolean isVertex(Object cell);

    PetriNetGraphCell[] getSelectedVertices();

    boolean isTransition(Object cell);

    boolean isPlace(Object cell);

    void openGraphFromFile(File file) throws Exception;

    void saveGraphAsFile(File file) throws IOException;

    HashMap<Integer, Integer> getInitialMarking();

    int getInitialMarking(int placeId);

    void invalidateReachabilityGraph();

    JGraph getPetriNetGraph();

    DomainModel getDomainModel();

    Graph<State, TransitionEdge> getReachabilityGraph();

    Place getModelRepresentative(PlaceGraphCell placeGraphCell);

    Transition getModelRepresentative(TransitionGraphCell transitionGraphCell);

    boolean isArc(Object cell);

    ArcGraphCell tryCastToArc(Object cell);

    Arc getModelRepresentative(ArcGraphCell arcGraphCell);

    void refreshGraph();

    void refreshMatrix();

    public void exportMatrixToFile(File file);

    void displayMarkingOnGraph(Map<Integer, Integer> marking);

    String getDescriptionOfCell(Object cell);
}
