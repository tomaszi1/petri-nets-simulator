package org.petri.nets.service;

import org.petri.nets.gui.graph.PlaceGraphCell;
import org.petri.nets.gui.graph.TransitionGraphCell;

import java.awt.*;

public interface GraphService {

    void removeFromGraph(Object cell);
    PlaceGraphCell addPlace(Point position);
    TransitionGraphCell addTransition(Point position);
    void addArc(Object place, Object transition);

}
