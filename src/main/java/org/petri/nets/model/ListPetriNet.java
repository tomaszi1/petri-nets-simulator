package org.petri.nets.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ListPetriNet implements PetriNet {

    private List<Integer> initialMarking;
    private HashMap<Integer,Place> placeMap;
    private HashMap<Integer,Transition> transitionMap;

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
        initialMarking = new ArrayList<Integer>();
        setPlaceMap(new HashMap<Integer, Place>());
        setTransitionMap(new HashMap<Integer, Transition>());
    }

    ////////////
    //
    // marking


    @Override
    public void setInitialMarking(List<Integer> marking) {
        this.initialMarking = marking;
    }

    @Override
    public void setInitialMarking(int place, int marking) {
       initialMarking.add(place,marking);
    }

    @Override
    public List<Integer> getInitialMarking() {
        return initialMarking;
    }

    @Override
    public int getInitialMarking(int place) {
        return initialMarking.get(place);
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
