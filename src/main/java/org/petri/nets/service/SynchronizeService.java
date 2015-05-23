package org.petri.nets.service;

import org.petri.nets.model.Place;
import org.petri.nets.model.Transition;
/**
 * Created by Asia on 2015-05-17.
 */
public interface SynchronizeService {
    public void addPlace(int idPlace, Place place);
    public void addTransition(int idTransition, Transition transition);
    public void addArc(Place place, Transition transition,int value,int priority, boolean isPlaceStart );
    public void removePlace(Place place);
    public void removeTransition(Transition transition);
    public void removeArc(Transition transition, Place place, boolean isPlaceStart);
    //public void removeFromGraph(int id);

}
