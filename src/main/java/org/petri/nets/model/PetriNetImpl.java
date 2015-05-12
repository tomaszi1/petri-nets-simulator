package org.petri.nets.model;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.*;

public class PetriNetImpl implements PetriNet {

    private List<Integer> initialMarking;
    private Table<Integer, Integer, Arc> placeToTransitionArcs = HashBasedTable.create();
    private Table<Integer, Integer, Arc> transitionToPlaceArcs = HashBasedTable.create();

    @Override
    public void setInitialMarking(List<Integer> marking) {
        initialMarking = new ArrayList<>(marking);
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
    public void setInitialMarking(int place, int marking) {
        if (marking < 0)
            throw new IllegalArgumentException("Negative marking");
        initialMarking.set(place, marking);
    }

    @Override
    public List<Arc> getIngoingArcsForPlace(int place) {
        return null;
    }

    @Override
    public List<Arc> getOutgoingArcsForPlace(int place) {
        return null;
    }

    @Override
    public List<Arc> getIngoingArcsForTransition(int transition) {
        throw new UnsupportedOperationException();
    }

    public Map<Integer, Arc> getIngoingArcsForTransitionX(int transition) {
        return placeToTransitionArcs.column(transition);
    }

    @Override
    public List<Arc> getOutgoingArcsForTransition(int transition) {
        throw new UnsupportedOperationException();
    }

    public Map<Integer, Arc> getOutgoingArcsForTransitionX(int transition) {
        return transitionToPlaceArcs.column(transition);
    }


    @Override
    public Arc getArcFromPlaceToTransition(int fromPlace, int toTransition) {
        return placeToTransitionArcs.get(toTransition, fromPlace);
    }

    @Override
    public void putArcFromPlaceToTransition(int fromPlace, int toTransition, Arc arc) {
        placeToTransitionArcs.put(toTransition, fromPlace, arc);
    }

    @Override
    public Arc getArcFromTransitionToPlace(int fromTransition, int toPlace) {
        return transitionToPlaceArcs.get(fromTransition, toPlace);
    }

    @Override
    public void putArcFromTransitionToPlace(int fromTransition, int toPlace, Arc arc) {
        transitionToPlaceArcs.put(fromTransition, toPlace, arc);
    }

    @Override
    public int getTransitionsCount() {
        return placeToTransitionArcs.columnKeySet().size();
    }

    @Override
    public int getPlacesCount() {
        return placeToTransitionArcs.rowKeySet().size();
    }
}
