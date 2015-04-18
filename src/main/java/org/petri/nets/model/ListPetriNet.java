package org.petri.nets.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListPetriNet implements PetriNet {
    private List<List<Arc>> placeToTransitionArcs;
    private List<List<Arc>> transitionToPlaceArcs;
    private List<Integer> initialMarking;

    public ListPetriNet(int numOfPlaces, int numOfTransitions) {
        placeToTransitionArcs = new ArrayList<>(numOfTransitions);
        transitionToPlaceArcs = new ArrayList<>(numOfTransitions);

        for (int i = 0; i < numOfTransitions; i++) {
            placeToTransitionArcs.add(Collections.nCopies(numOfPlaces, (Arc) null));
            transitionToPlaceArcs.add(Collections.nCopies(numOfPlaces, (Arc) null));
        }
    }

    public ListPetriNet() {
    }

    @Override
    public void setInitialMarking(List<Integer> marking) {
        initialMarking = new ArrayList<>(marking);
    }

    @Override
    public List<Integer> getInitialMarking() {
        return new ArrayList<>(initialMarking);
    }

    @Override
    public int getToken(int place) {
        return initialMarking.get(place);
    }

    @Override
    public List<Arc> getIngoingArcsForPlace(int place) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Arc> getOutgoingArcsForPlace(int place) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Arc> getIngoingArcsForTransition(int transition) {
        return new ArrayList<>(placeToTransitionArcs.get(transition));
    }

    @Override
    public List<Arc> getOutgoingArcsForTransition(int transition) {
        return new ArrayList<>(transitionToPlaceArcs.get(transition));
    }

    @Override
    public Arc getArcFromPlaceToTransition(int fromPlace, int toTransition) {
        return placeToTransitionArcs.get(toTransition).get(fromPlace);
    }

    @Override
    public void putArcFromPlaceToTransition(int fromPlace, int toTransition, Arc arc) {
        placeToTransitionArcs.get(toTransition).set(fromPlace, arc);
    }

    @Override
    public Arc getArcFromTransitionToPlace(int fromTransition, int toPlace) {
        return transitionToPlaceArcs.get(fromTransition).get(toPlace);
    }

    @Override
    public void putArcFromTransitionToPlace(int fromTransition, int toPlace, Arc arc) {
        transitionToPlaceArcs.get(fromTransition).set(toPlace, arc);
    }

    @Override
    public int getTransitionsCount() {
        return transitionToPlaceArcs.size();
    }

    @Override
    public int getPlacesCount() {
        if(transitionToPlaceArcs.isEmpty())
            return 0;
        return transitionToPlaceArcs.get(0).size();
    }
}
