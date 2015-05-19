package org.petri.nets.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListPetriNet implements PetriNet {
    //To
    private List<List<Arc>> placeToTransitionArcs;
    //From
    //nie powinno miec innej nazwy? np
    private List<List<Arc>> transitionToPlaceArcs;
    private List<Integer> initialMarking;

    // nie wiem czy nam to potrzebne, ale chyba warto miec tez taka reprezentacje sieci w razie
    // gdyby latwiej sie na niej liczylo
    private ArrayList<Place> placeList;
    private ArrayList<Transition> transitionList;

    public ListPetriNet(int numOfPlaces, int numOfTransitions) {
        setPlaceToTransitionArcs(new ArrayList<>(numOfTransitions));
        setTransitionToPlaceArcs(new ArrayList<>(numOfTransitions));
        initialMarking = new ArrayList<Integer>();

        for (int i = 0; i < numOfTransitions; i++) {
            getPlaceToTransitionArcs().add(Collections.nCopies(numOfPlaces, (Arc) null));
            getTransitionToPlaceArcs().add(Collections.nCopies(numOfPlaces, (Arc) null));
        }

        setPlaceList(new ArrayList<Place>(numOfPlaces));
        setTransitionList(new ArrayList<Transition>(numOfTransitions));
    }
/*//to bedzie domyslnie uzywany konstruktor - nie uzuwac
    public ListPetriNet() {
        setPlaceToTransitionArcs(new ArrayList<List<Arc>>());
        setTransitionToPlaceArcs(new ArrayList<List<Arc>>());
        initialMarking = new ArrayList<Integer>();

        setPlaceList(new ArrayList<Place>());
        setTransitionList(new ArrayList<Transition>());
    }*/
//konstruktor do obecnego poczatkowego stanu aplikacji
    public ListPetriNet() {
        setPlaceToTransitionArcs(new ArrayList<List<Arc>>());
        setTransitionToPlaceArcs(new ArrayList<List<Arc>>());
        initialMarking = new ArrayList<Integer>();
        setPlaceList(new ArrayList<Place>());
        setTransitionList(new ArrayList<Transition>());

        initialMarking.add(0);
        placeList.add(0,new Place(0));
        transitionList.add(0,new Transition(0));
        placeToTransitionArcs.add(new ArrayList<Arc>());
        transitionToPlaceArcs.add(new ArrayList<Arc>());

    }

    @Override
    public void setInitialMarking(List<Integer> marking) {
        initialMarking = new ArrayList<>(marking);
    }

    @Override
    public void setInitialMarking(int place, int marking) {
        if (marking < 0)
            throw new IllegalArgumentException("Negative marking");
        initialMarking.set(place, marking);
    }

    @Override
    public List<Integer> getInitialMarking() {
        return new ArrayList<>(initialMarking);
    }

    @Override
    public int getInitialMarking(int place) {
        return initialMarking.get(place);
    }


    @Override
    public List<Arc> getIngoingArcsForPlace(int placeId) {
        ArrayList<Arc> ingoingArc= new ArrayList<Arc>();
        for(Integer idTransition : getPlaceList().get(placeId).getTransitionFrom().keySet()){
            ingoingArc.add(getPlaceList().get(placeId).getTransitionFrom().get(idTransition));
        }
        return  ingoingArc;
        //throw new UnsupportedOperationException();
    }

    @Override
    public List<Arc> getOutgoingArcsForPlace(int placeId) {
        ArrayList<Arc> outgoingArc= new ArrayList<Arc>();
        for(Integer idTransition : getPlaceList().get(placeId).getTransitionTo().keySet()){
            outgoingArc.add(getPlaceList().get(placeId).getTransitionFrom().get(idTransition));
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
    }

    @Override
    public int getTransitionsCount() {
        return getTransitionToPlaceArcs().size();
    }

    @Override
    public int getPlacesCount() {
        if (getTransitionToPlaceArcs().isEmpty())
            return 0;
        return getTransitionToPlaceArcs().get(0).size();
    }

    @Override
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

    @Override
    public ArrayList<Place> getPlaceList() {
        return placeList;
    }

    @Override
    public void setPlaceList(ArrayList<Place> placeList) {
        this.placeList = placeList;
    }

    @Override
    public ArrayList<Transition> getTransitionList() {
        return transitionList;
    }

    @Override
    public void setTransitionList(ArrayList<Transition> transitionList) {
        this.transitionList = transitionList;
    }
}
