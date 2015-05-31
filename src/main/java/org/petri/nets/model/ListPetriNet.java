package org.petri.nets.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListPetriNet implements PetriNet, Serializable {

    private HashMap<Integer,Integer> initialMarking;

    private HashMap<Integer,Place> placeMap;
    private HashMap<Integer,Transition> transitionMap;
    private int placeIdCounter = 0;
    private int transitionIdCounter = 0;

/*//to bedzie domyslnie uzywany konstruktor - nie usuwac
    public ListPetriNet() {
        setPlaceToTransitionArcs(new ArrayList<List<Arc>>());
        setTransitionToPlaceArcs(new ArrayList<List<Arc>>());
        initialMarking = new ArrayList<Integer>();

        setPlaceMap(new ArrayList<Place>());
        setTransitionMap(new ArrayList<Transition>());
    }*/
//konstruktor do obecnego poczatkowego stanu aplikacji
    public ListPetriNet() {
        initialMarking = new HashMap<Integer,Integer>();
        setPlaceMap(new HashMap<Integer, Place>());
        setTransitionMap(new HashMap<Integer, Transition>());
    }

    ////////////
    //
    // marking


    @Override
    public void setInitialMarking(HashMap<Integer,Integer> marking) {
        for(Map.Entry<Integer,Integer>markingEntry:marking.entrySet()){
            placeMap.get(markingEntry.getKey()).setMarking(markingEntry.getValue());
        }
        this.initialMarking = marking;
    }

    @Override
    public void setInitialMarking(Integer placeId, int marking) {
       initialMarking.put(placeId,marking);
        placeMap.get(placeId).setMarking(marking);
    }

    @Override
    public HashMap<Integer, Integer> getInitialMarking() {
        return initialMarking;
    }

    @Override
    public Integer getInitialMarking(Integer placeId) {
        return initialMarking.get(placeId);
    }

    //////////////////
    //
    // place


    @Override
    public HashMap<Integer,Place> getPlaceMap() {
        return placeMap;
    }

    @Override
    public void setPlaceMap(HashMap<Integer, Place> placeMap) {
        this.placeMap = placeMap;
    }

    /////////
    //
    // transition

    @Override
    public HashMap<Integer,Transition> getTransitionMap() {
        return transitionMap;
    }

    @Override
    public void setTransitionMap(HashMap<Integer, Transition> transitionMap) {
        this.transitionMap = transitionMap;
    }

    @Override
    public int getTransitionsCount() {
        return transitionMap.size();
    }

    @Override
    public int getPlacesCount() {
        return placeMap.size();
    }
    @Override
    public int getPlaceIdCounter() {
        return placeIdCounter;
    }
    @Override
    public void setPlaceIdCounter(int placeIdCounter) {
        this.placeIdCounter = placeIdCounter;
    }
    @Override
    public int getTransitionIdCounter() {
        return transitionIdCounter;
    }
    @Override
    public void setTransitionIdCounter(int transitionIdCounter) {
        this.transitionIdCounter = transitionIdCounter;
    }


//chyba nie sa nam potrzbne te metody w nowej reprezentacji sieci
/*    @Override
   public List<Arc> getIngoingArcsForPlace(Place place) {
        ArrayList<Arc> ingoingArc= new ArrayList<Arc>();
        for(Integer Transition : getPlaceMap().get(placeId).getTransitionFrom().keySet()){
            ingoingArc.add(getPlaceMap().get(placeId).getTransitionFrom().get(idTransition));
        }
        return  ingoingArc;
        //throw new UnsupportedOperationException();
    }

    @Override
    public List<Arc> getOutgoingArcsForPlace(int placeId) {
        ArrayList<Arc> outgoingArc= new ArrayList<Arc>();
        for(Integer idTransition : getPlaceMap().get(placeId).getTransitionTo().keySet()){
            outgoingArc.add(getPlaceMap().get(placeId).getTransitionFrom().get(idTransition));
        }
        return  outgoingArc;

        //throw new UnsupportedOperationException();
    }

    @Override
    public List<Arc> getIngoingArcsForTransition(int idTransition) {
        return new ArrayList<>(getPlaceToTransitionArcs().get(idTransition));
    }

    @Override
    public List<Arc> getOutgoingArcsForTransition(int idTransition) {
        return new ArrayList<>(getTransitionToPlaceArcs().get(idTransition));
    }

    @Override
    public Arc getArcFromPlaceToTransition(int fromPlace, int toTransition) {
        return getPlaceToTransitionArcs().get(toTransition).get(fromPlace);
    }

    @Override
    public void putArcFromPlaceToTransition(int fromPlace, int toTransition, Arc arc) {
        getPlaceToTransitionArcs().get(toTransition).set(fromPlace, arc);
    }

    @Override
    public Arc getArcFromTransitionToPlace(int fromTransition, int toPlace) {
        return getTransitionToPlaceArcs().get(fromTransition).get(toPlace);
    }

    @Override
    public void putArcFromTransitionToPlace(int fromTransition, int toPlace, Arc arc) {
        getTransitionToPlaceArcs().get(fromTransition).set(toPlace, arc);
    }*/

 /*   @Override
    public List<List<Arc>> getPlaceToTransitionArcs() {
        return placeToTransitionArcs;
    }

    @Override
    public void setPlaceToTransitionArcs(List<List<Arc>> placeToTransitionArcs) {
        this.placeToTransitionArcs = placeToTransitionArcs;
    }

    @Override
    public List<List<Arc>> getTransitionToPlaceArcs() {
        return transitionToPlaceArcs;
    }

    @Override
    public void setTransitionToPlaceArcs(List<List<Arc>> transitionToPlaceArcs) {
        this.transitionToPlaceArcs = transitionToPlaceArcs;
    }
*/

}
