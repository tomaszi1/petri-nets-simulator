package org.petri.nets.model;

import com.google.common.collect.Maps;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by Asia on 2015-05-17.
 */
public class Place extends PetriNetElement implements Serializable {
    //z ktorych przejsc wchodzi sie do miejsca
    private HashMap<Transition, Arc> transitionsFrom;
    // do ktorych przejsc mozna isc
    private HashMap<Transition, Arc> transitionsTo;
    private int initialMarking;


    public Place(int id) {
        this(id, 0);
    }

    public Place(int id, int initialMarking) {
        setId(id);
        transitionsFrom = new HashMap<>();
        transitionsTo = new HashMap<>();
        this.initialMarking = initialMarking;
    }

    public HashMap<Transition, Arc> getTransitionsFrom() {
        return Maps.newHashMap(transitionsFrom);
    }

    public HashMap<Transition, Arc> getTransitionsTo() {
        return Maps.newHashMap(transitionsTo);
    }

    public void addTransitionFrom(Transition transitionFrom, Arc arc) {
        transitionsFrom.put(transitionFrom, arc);
    }

    public void removeTransitionFrom(Transition transitionFrom) {
        transitionsFrom.remove(transitionFrom);
    }

    public void addTransitionTo(Transition transitionTo, Arc arc) {
        transitionsTo.put(transitionTo, arc);
    }

    public void removeTransitionTo(Transition transitionTo) {
        transitionsTo.remove(transitionTo);
    }

    public void setInitialMarking(int initialMarking) {
        this.initialMarking = initialMarking;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public String getName() {
        return "P" + getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Place place = (Place) o;
        return place.getId()==getId();
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
