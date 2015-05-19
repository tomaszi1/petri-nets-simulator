package org.petri.nets.service;

import org.petri.nets.model.Place;
import org.petri.nets.model.Transition;
/**
 * Created by Asia on 2015-05-17.
 */
public interface SynchronizeService {
    public void addPlace(int idPlace);
    public void addTransition(int idTransition);
    public void addArc(int idTransition, int idPlace, boolean isPlaceStart);
    public void removePlace(int idPlace);
    public void removeTransition(int idTransition);
    public void removeArc(int idTransition, int idPlace, boolean isPlaceStart);
    public void removeFromGraph(int id);

}
