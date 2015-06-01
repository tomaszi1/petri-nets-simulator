package org.petri.nets.service;

import org.petri.nets.model.Place;
import org.petri.nets.model.Transition;
/**
 * Created by Asia on 2015-05-17.
 */
public interface SynchronizeService {
    void addPlace(int idPlace, Place place);
    void addTransition(int idTransition, Transition transition);
    void addArc(Place place, Transition transition, int value, boolean isPlaceStart);
    void removePlace(Place place);
    void removeTransition(Transition transition);
    void removeArc(Transition transition, Place place, boolean isPlaceStart);
    void updateReachabilityGraph();
}
