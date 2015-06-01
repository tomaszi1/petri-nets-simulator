package org.petri.nets.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public interface PetriNet {
    //markings
    void setInitialMarking(LinkedHashMap<Integer, Integer> marking);
    LinkedHashMap<Integer, Integer> getInitialMarking();
    Integer getInitialMarking(Integer placeId);
    void setInitialMarking(Integer placeId, int marking);

    int getTransitionsCount();
    int getPlacesCount();

    //place
    HashMap<Integer,Place> getPlaceMap();
    void setPlaceMap(HashMap<Integer, Place> placeList);

    //Transition
    HashMap<Integer,Transition> getTransitionMap();
    void setTransitionMap(HashMap<Integer, Transition> transitionList);

    //Id transition + place
    int getPlaceIdCounter();
    void setPlaceIdCounter(int placeIdCounter);
    int getTransitionIdCounter();
    void setTransitionIdCounter(int transitionIdCounter);

    Place addPlace();
    Transition addTransition();

    Arc addArc(Place place, Transition transition, int value, boolean startsInPlace);


/*    List<Arc> getIngoingArcsForPlace(int place);

    List<Arc> getOutgoingArcsForPlace(int place);

    List<Arc> getIngoingArcsForTransition(int transition);

    List<Arc> getOutgoingArcsForTransition(int transition);

    Arc getArcFromPlaceToTransition(int fromPlace, int toTransition);

    void putArcFromPlaceToTransition(int fromPlace, int toTransition, Arc arc);

    Arc getArcFromTransitionToPlace(int fromTransition, int toPlace);

    void putArcFromTransitionToPlace(int fromTransition, int toPlace, Arc arc);
    //public List<List<Arc>> getPlaceToTransitionArcs();

   // public void setPlaceToTransitionArcs(List<List<Arc>> placeToTransitionArcs);

    //public List<List<Arc>> getTransitionToPlaceArcs();

    //public void setTransitionToPlaceArcs(List<List<Arc>> transitionToPlaceArcs);


    boolean doTransition(int transition);

    boolean isTransitionPossible(int transition);

    List<Integer> doAllAliveTransitions();

    List<Integer> doTransitions(List<Integer> transitions);

    List<Integer> getAliveTransitions();
*/
}
