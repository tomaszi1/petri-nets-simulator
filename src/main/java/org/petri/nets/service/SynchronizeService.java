package org.petri.nets.service;

import org.petri.nets.model.Place;
import org.petri.nets.model.Transition;
import org.petri.nets.synhronize.SynchronizePanel;

/**
 * Created by Asia on 2015-05-17.
 */
public interface SynchronizeService {
    void addPlace();
    void addArc(Place place, Transition transition, int value, boolean isPlaceStart);
    void removePlace(Place place);
    void removeTransition(Transition transition);
    void removeArc(Transition transition, Place place, boolean isPlaceStart);
    void updateReachabilityGraph();
    SynchronizePanel getSynchronizePanel();
}
