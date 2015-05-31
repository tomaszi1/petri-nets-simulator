package org.petri.nets.model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by Asia on 2015-05-17.
 */
public class Transition implements Serializable {
    private int idTransition;
    //z ktorych miejsc wchodzi sie do przejscia
    private HashMap<Place, Arc> placeFrom;
    // do ktorych miejsc idzie sie z przejscia
    private HashMap<Place, Arc> placeTo;
    private int priority;

    public Transition(int idTrans){
        this.idTransition = idTrans;
        placeFrom = new HashMap<Place, Arc>();
        placeTo = new HashMap<Place, Arc>();

    }
    public int getIdTransition() {
        return idTransition;
    }

    public void setIdTransition(int idTransition) {
        this.idTransition = idTransition;
    }

    public HashMap<Place, Arc> getPlaceFrom() {
        return placeFrom;
    }

    public void setPlaceFrom(HashMap<Place, Arc> placeFrom) {
        this.placeFrom = placeFrom;
    }

    public HashMap<Place, Arc> getPlaceTo() {
        return placeTo;
    }

    public void setPlaceTo(HashMap<Place, Arc> placeTo) {
        this.placeTo = placeTo;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
