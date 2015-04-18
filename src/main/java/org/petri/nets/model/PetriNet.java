package org.petri.nets.model;

import java.util.List;

public interface PetriNet {
    void setInitialMarking(List<Integer> marking);

    List<Integer> getInitialMarking();

    int getToken(int place);

    List<Arc> getIngoingArcsForPlace(int place);

    List<Arc> getOutgoingArcsForPlace(int place);

    List<Arc> getIngoingArcsForTransition(int transition);

    List<Arc> getOutgoingArcsForTransition(int transition);

    Arc getArcFromPlaceToTransition(int fromPlace, int toTransition);

    void putArcFromPlaceToTransition(int fromPlace, int toTransition, Arc arc);

    Arc getArcFromTransitionToPlace(int fromTransition, int toPlace);

    void putArcFromTransitionToPlace(int fromTransition, int toPlace, Arc arc);

    int getTransitionsCount();

    int getPlacesCount();

/*
    boolean doTransition(int transition);

    boolean isTransitionPossible(int transition);

    List<Integer> doAllAliveTransitions();

    List<Integer> doTransitions(List<Integer> transitions);

    List<Integer> getAliveTransitions();
*/
}
