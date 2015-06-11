package org.petri.nets.model;

import com.google.common.collect.Maps;

import java.io.Serializable;
import java.util.*;

public class ListPetriNet implements PetriNet, Serializable {

    private LinkedHashMap<Integer, Integer> initialMarking;

    private HashMap<Integer, Place> placeMap;
    private HashMap<Integer, Transition> transitionMap;
    private int placeIdCounter = 1;
    private int transitionIdCounter = 1;

    public ListPetriNet() {
        initialMarking = new LinkedHashMap<>();
        transitionMap = Maps.newHashMap();
        placeMap = Maps.newHashMap();
    }

    ////////////
    //
    // marking


    @Override
    public void setInitialMarking(Integer placeId, int marking) {
        initialMarking.put(placeId, marking);
        placeMap.get(placeId).setInitialMarking(marking);
    }

    @Override
    public LinkedHashMap<Integer, Integer> getInitialMarking() {
        return Maps.newLinkedHashMap(initialMarking);
    }

    @Override
    public Integer getInitialMarking(Integer placeId) {
        return initialMarking.get(placeId);
    }

    //////////////////
    //
    // place


    /////////
    //
    // transition

    @Override
    public Place addPlace() {
        Place place = new Place(placeIdCounter);
        initialMarking.put(placeIdCounter, 0);
        placeMap.put(placeIdCounter, place);
        placeIdCounter++;
        return place;
    }

    @Override
    public Transition addTransition() {
        Transition transition = new Transition(transitionIdCounter, 1);
        transitionMap.put(transitionIdCounter, transition);
        transitionIdCounter++;
        return transition;
    }

    @Override
    public Arc addArc(Place place, Transition transition, int value, boolean startsInPlace) {
        Arc arc = new Arc(value);

        if (startsInPlace) {
            placeMap.get(place.getId()).addTransitionTo(transition, arc);
            transitionMap.get(transition.getId()).addPlaceFrom(place, arc);
            arc.setStart(place);
            arc.setEnd(transition);
        } else {
            placeMap.get(place.getId()).addTransitionFrom(transition, arc);
            transitionMap.get(transition.getId()).addPlaceTo(place, arc);
            arc.setEnd(place);
            arc.setStart(transition);
        }

        return arc;
    }

    @Override
    public void removeArc(Place place, Transition transition, boolean isPlaceStart) {
        if(isPlaceStart){
            place.removeTransitionTo(transition);
            transition.removePlaceFrom(place);
        }else{
            place.removeTransitionFrom(transition);
            transition.removePlaceTo(place);
        }
    }

    @Override
    public Collection<Transition> getTransitions() {
        return transitionMap.values();
    }

    @Override
    public void removePlace(Place place) {
        removePlace(place.getId());
    }

    @Override
    public void removePlace(int id) {
        Place removed = placeMap.remove(id);
        if (removed != null) {
            removed.getTransitionsFrom().forEach((transition, arc) -> transition.removePlaceTo(removed));
            removed.getTransitionsTo().forEach((transition, arc) -> transition.removePlaceFrom(removed));
        }
        initialMarking.remove(id);
    }

    @Override
    public void removeTransition(Transition transition) {
        removeTransition(transition.getId());
    }

    @Override
    public void removeTransition(int id) {
        Transition removed = transitionMap.remove(id);
        if (removed != null) {
            removed.getPlacesFrom().forEach((place, arc) -> place.removeTransitionTo(removed));
            removed.getPlacesTo().forEach((place, arc) -> place.removeTransitionFrom(removed));
        }
    }

    @Override
    public boolean hasArc(Place place, Transition transition) {
        return !(place == null || transition == null)
                && (place.getTransitionsFrom().containsKey(transition)
                || transition.getPlacesFrom().containsKey(place));
    }

    @Override
    public Map<Integer, Place> getPlaces() {
        return Maps.newHashMap(placeMap);
    }

    @Override
    public Place getPlace(int id) {
        return placeMap.get(id);
    }

    @Override
    public HashMap<Integer, Place> getPlaceMap() {
        return placeMap;
    }

    @Override
    public HashMap<Integer, Transition> getTransitionMap() {
        return transitionMap;
    }
}
