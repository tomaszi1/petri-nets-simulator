package org.petri.nets.service;

import org.jgraph.graph.CellView;
import org.petri.nets.gui.graph.PetriNetGraphCell;
import org.petri.nets.gui.graph.PlaceGraphCell;
import org.petri.nets.gui.graph.TransitionGraphCell;

import java.awt.*;

public interface GraphService {

    void removeFromGraph(Object cell);


    PlaceGraphCell addPlace(Point position);

    TransitionGraphCell addTransition(Point position);

    void addArc(PetriNetGraphCell start, PetriNetGraphCell end);

    CellView getLastFocusedCell();

    Object[] getSelectedCells();

    boolean isVertex(Object cell);

    PetriNetGraphCell[] getSelectedVertices();

    boolean isTransition(Object cell);

    boolean isPlace(Object cell);
    public void saveGraphAsFile();
}
