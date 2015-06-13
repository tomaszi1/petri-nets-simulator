package org.petri.nets.model;

import com.google.common.collect.Maps;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Asia on 2015-05-17.
 */
public class Transition extends PetriNetElement implements Serializable {
    //z ktorych miejsc wchodzi sie do przejscia
    private HashMap<Place, Arc> placesFrom;
    // do ktorych miejsc idzie sie z przejscia
    private HashMap<Place, Arc> placesTo;
    private int priority;
    private String description;

    public Transition() {
        this(0, 0);
    }

    public Transition(int id, int priority) {
        setId(id);
        placesFrom = Maps.newHashMap();
        placesTo = Maps.newHashMap();
        this.priority = priority;
    }

    public Map<Place, Arc> getPlacesFrom() {
        return Maps.newHashMap(placesFrom);
    }

    public void setPlacesFrom(Map<Place, Arc> placeFrom) {
        this.placesFrom = Maps.newHashMap(placeFrom);
    }

    public void addPlaceFrom(Place placeFrom, Arc arc) {
        placesFrom.put(placeFrom, arc);
    }

    public void addPlaceTo(Place placeTo, Arc arc) {
        placesTo.put(placeTo, arc);
    }

    public void removePlaceFrom(Place placeFrom) {
        placesFrom.remove(placeFrom);
    }

    public void removePlaceTo(Place placeTo) {
        placesTo.remove(placeTo);
    }

    public HashMap<Place, Arc> getPlacesTo() {
        return Maps.newHashMap(placesTo);
    }

    public void setPlacesTo(Map<Place, Arc> placesTo) {
        this.placesTo = Maps.newHashMap(placesTo);
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return getName();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getName() {
        return "T" + getId();
    }
}
