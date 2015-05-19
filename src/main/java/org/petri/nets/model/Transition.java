package org.petri.nets.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Asia on 2015-05-17.
 */
public class Transition {
    private int idTransition;
    //z ktorych miejsc wchodzi sie do przejscia
    //HashMap<idPrzejscia, Luk>
    private HashMap<Integer, Arc> placeFrom;
    // do ktorych miejsc idzie sie z przejscia
    private HashMap<Integer, Arc> placeTo;

    public Transition(int idTrans){
        this.idTransition = idTrans;
        placeFrom = new HashMap<Integer, Arc>();
        placeTo = new HashMap<Integer, Arc>();
    }
    public int getIdTransition() {
        return idTransition;
    }

    public void setIdTransition(int idTransition) {
        this.idTransition = idTransition;
    }

    public HashMap<Integer, Arc> getPlaceFrom() {
        return placeFrom;
    }

    public void setPlaceFrom(HashMap<Integer, Arc> placeFrom) {
        this.placeFrom = placeFrom;
    }

    public HashMap<Integer, Arc> getPlaceTo() {
        return placeTo;
    }

    public void setPlaceTo(HashMap<Integer, Arc> placeTo) {
        this.placeTo = placeTo;
    }
}
