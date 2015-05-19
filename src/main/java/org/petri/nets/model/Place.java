package org.petri.nets.model;

import java.util.HashMap;

/**
 * Created by Asia on 2015-05-17.
 */
public class Place {
    private int idPlace;
    //z ktorych przejsc wchodzi sie do miejsca
    //HashMap<idPrzejscia, Luk>
    private HashMap<Integer, Arc> transitionFrom;
    // do ktorych przejsc mozna isc
    private HashMap<Integer, Arc> transitionTo;

    public Place(int idPlace){
        this.idPlace = idPlace;
        transitionFrom = new HashMap<Integer, Arc>();
        transitionTo = new HashMap<Integer, Arc>();
    }

    public int getIdPlace() {
        return idPlace;
    }

    public void setIdPlace(int idPlace) {
        this.idPlace = idPlace;
    }

    public HashMap<Integer, Arc> getTransitionFrom() {
        return transitionFrom;
    }

    public void setTransitionFrom(HashMap<Integer, Arc> transitionFrom) {
        this.transitionFrom = transitionFrom;
    }

    public HashMap<Integer, Arc> getTransitionTo() {
        return transitionTo;
    }

    public void setTransitionTo(HashMap<Integer, Arc> transitionTo) {
        this.transitionTo = transitionTo;
    }
}
