package org.petri.nets.model;

import java.util.*;

public interface PetriNet {
    //markings
    void setInitialMarking(LinkedHashMap<Integer, Integer> marking);
    LinkedHashMap<Integer, Integer> getInitialMarking();
    Integer getInitialMarking(Integer placeId);
    void setInitialMarking(Integer placeId, int marking);

    void putTransition(Transition transition);

    int getTransitionsCount();
    int getPlacesCount();

    //place
    HashMap<Integer,Place> getPlaceMap();

    void setPlaceMap(HashMap<Integer, Place> placeList);

    //Transition
    HashMap<Integer,Transition> getTransitionMap();
    Collection<Transition> getTransitions();
    void setTransitionMap(HashMap<Integer, Transition> transitionList);

    Transition getTransition(int id);
    Place getPlace(int id);

    Place addPlace();
    Transition addTransition();

    Arc addArc(Place place, Transition transition, int value, boolean startsInPlace);

    void removeArc(Place place, Transition transition, boolean isPlaceStart);

    void removePlace(Place place);

    void removePlace(int id);

    void removeTransition(Transition transition);

    void removeTransition(int id);
}
