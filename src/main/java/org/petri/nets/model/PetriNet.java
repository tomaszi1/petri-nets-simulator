package org.petri.nets.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface PetriNet {
    public void setInitialMarking(List<Integer> marking);

    //markings
    public List<Integer> getInitialMarking();
    public int getInitialMarking(int place);
    public void setInitialMarking(int place, int marking);

    public int getTransitionsCount();
    public int getPlacesCount();

    //place
    public HashMap<Integer,Place> getPlaceMap();
    public void setPlaceMap(HashMap<Integer, Place> placeList);

    //Transition
    public HashMap<Integer,Transition> getTransitionMap();
    public void setTransitionMap(HashMap<Integer, Transition> transitionList);

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
