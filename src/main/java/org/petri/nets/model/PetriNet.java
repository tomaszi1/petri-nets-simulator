package org.petri.nets.model;

import java.util.ArrayList;
import java.util.List;

public interface PetriNet {
    void setInitialMarking(List<Integer> marking);

    List<Integer> getInitialMarking();

    int getInitialMarking(int place);

    void setInitialMarking(int place, int marking);

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
    public List<List<Arc>> getPlaceToTransitionArcs();

    public void setPlaceToTransitionArcs(List<List<Arc>> placeToTransitionArcs);

    public List<List<Arc>> getTransitionToPlaceArcs();

    public void setTransitionToPlaceArcs(List<List<Arc>> transitionToPlaceArcs);
    public ArrayList<Place> getPlaceList();

    public void setPlaceList(ArrayList<Place> placeList);
    public ArrayList<Transition> getTransitionList();

    public void setTransitionList(ArrayList<Transition> transitionList);

/*
    boolean doTransition(int transition);

    boolean isTransitionPossible(int transition);

    List<Integer> doAllAliveTransitions();

    List<Integer> doTransitions(List<Integer> transitions);

    List<Integer> getAliveTransitions();
*/
}
