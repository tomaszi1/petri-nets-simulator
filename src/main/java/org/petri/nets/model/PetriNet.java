package org.petri.nets.model;

import java.util.*;

public interface PetriNet {
    LinkedHashMap<Integer, Integer> getInitialMarking();

    Integer getInitialMarking(Integer placeId);

    void setInitialMarking(Integer placeId, int marking);

    void setPlaceMap(HashMap<Integer, Place> placeList);

    Collection<Transition> getTransitions();

    void setTransitionMap(HashMap<Integer, Transition> transitionList);

    // add

    Place addPlace();

    Transition addTransition();

    Arc addArc(Place place, Transition transition, int value, boolean startsInPlace);

    // remov
    void removeArc(Place place, Transition transition, boolean isPlaceStart);

    void removePlace(Place place);

    void removePlace(int id);

    void removeTransition(Transition transition);

    void removeTransition(int id);

}
