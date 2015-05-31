package org.petri.nets.model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by Asia on 2015-05-17.
 */
public class Place implements Serializable{
    private int idPlace;
    //z ktorych przejsc wchodzi sie do miejsca
    private HashMap<Transition, Arc> transitionFrom;
    // do ktorych przejsc mozna isc
    private HashMap<Transition, Arc> transitionTo;
    private int marking;

    public Place(int idPlace){
        this.idPlace = idPlace;
        transitionFrom = new HashMap<Transition, Arc>();
        transitionTo = new HashMap<Transition, Arc>();
        marking = 0;
    }
    public Place(int idPlace, int marking){
        this.idPlace = idPlace;
        transitionFrom = new HashMap<Transition, Arc>();
        transitionTo = new HashMap<Transition, Arc>();
        this.marking = marking;
    }

    public int getIdPlace() {
        return idPlace;
    }

    public void setIdPlace(int idPlace) {
        this.idPlace = idPlace;
    }

    public HashMap<Transition, Arc> getTransitionFrom() {
        return transitionFrom;
    }

    public void setTransitionFrom(HashMap<Transition, Arc> transitionFrom) {
        this.transitionFrom = transitionFrom;
    }

    public HashMap<Transition, Arc> getTransitionTo() {
        return transitionTo;
    }

    public void setTransitionTo(HashMap<Transition, Arc> transitionTo) {
        this.transitionTo = transitionTo;
    }

    public int getMarking() {
        return marking;
    }

    public void setMarking(int marking) {
        this.marking = marking;
    }
}
