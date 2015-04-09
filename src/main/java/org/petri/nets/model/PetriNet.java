package org.petri.nets.model;

import java.util.List;

public interface PetriNet {
    void setMarking(List<Integer> marking);

    List<Integer> getMarking();

    int getToken(int place);

    List<Integer> getTransitions(int place);

    List<Integer> getIngoingArcValuesForTransition(int transition);

    List<Integer> getOutgoingArcValuesForTransition(int transition);

    int getPlaceToTransitionArcValue(int fromPlace, int toTransition);

    void setPlaceToTransitionArcValue(int fromPlace, int toTransition, int value);

    int getTransitionToPlaceArcValue(int fromTransition, int toPlace);

    void setTransitionToPlaceArcValue(int fromTransition, int toPlace, int value);

    int getTransitionsCount();

    int getPlacesCount();

    boolean doTransition(int transition);

    boolean isTransitionPossible(int transition);

    List<Integer> doAllAliveTransitions();

    List<Integer> doTransitions(List<Integer> transitions);

    List<Integer> getAliveTransitions();
}
