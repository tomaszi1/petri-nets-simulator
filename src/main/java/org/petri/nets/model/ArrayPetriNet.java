package org.petri.nets.model;


import org.petri.nets.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ArrayPetriNet implements PetriNet {
    public static final String NEGATIVE_VALUE_FORBIDDEN = "Negative value forbidden";

    private int[][] placeToTransitionArcs;
    private int[][] transitionToPlaceArcs;
    private int[] currentMarking;

    public ArrayPetriNet(int transitions, int places) {
        placeToTransitionArcs = new int[transitions][places];
        transitionToPlaceArcs = new int[transitions][places];
        currentMarking = new int[places];
    }

    public void setMarking(List<Integer> marking) {
        if (marking == null || marking.size() != currentMarking.length)
            throw new IllegalArgumentException();
        for (int i = 0; i < currentMarking.length; i++)
            currentMarking[i] = marking.get(i);
    }

    public List<Integer> getMarking() {
        List<Integer> marking = new ArrayList<Integer>(currentMarking.length);
        for (int mark : currentMarking)
            marking.add(mark);
        return marking;
    }

    public List<Integer> getTransitions(int place) {
        throw new UnsupportedOperationException();
    }

    public int getToken(int place) {
        return currentMarking[place];
    }

    public List<Integer> getIngoingArcValuesForTransition(int transition) {
        return Utils.toList(placeToTransitionArcs[transition]);
    }

    public int getPlaceToTransitionArcValue(int fromPlace, int toTransition) {
        return placeToTransitionArcs[toTransition][fromPlace];
    }

    public void setPlaceToTransitionArcValue(int fromPlace, int toTransition, int value) {
        if (value < 0)
            throw new IllegalArgumentException(NEGATIVE_VALUE_FORBIDDEN);
        placeToTransitionArcs[toTransition][fromPlace] = value;
    }

    public int getTransitionToPlaceArcValue(int fromTransition, int toPlace) {
        return transitionToPlaceArcs[fromTransition][toPlace];
    }

    public void setTransitionToPlaceArcValue(int fromTransition, int toPlace, int value) {
        if (value < 0)
            throw new IllegalArgumentException(NEGATIVE_VALUE_FORBIDDEN);
        transitionToPlaceArcs[fromTransition][toPlace] = value;
    }

    public int getTransitionsCount() {
        return placeToTransitionArcs.length;
    }

    public int getPlacesCount() {
        return transitionToPlaceArcs[0].length;
    }

    public boolean doTransition(int transition) {
        if (!isTransitionPossible(transition))
            return false;
        for (int i = 0; i < transitionToPlaceArcs[0].length; i++) {
            currentMarking[i] -= placeToTransitionArcs[transition][i];
            currentMarking[i] += transitionToPlaceArcs[transition][i];
        }
        return true;
    }

    public boolean isTransitionPossible(int transition) {
        boolean nonZero = false;
        for (int i = 0; i < placeToTransitionArcs[0].length; i++) {
            if (currentMarking[i] - placeToTransitionArcs[transition][i] < 0)
                return false;
            if (placeToTransitionArcs[transition][i] != 0)
                nonZero = true;
        }
        return nonZero;
    }

    public List<Integer> doAllAliveTransitions() {
        List<Integer> transitionsDone = new ArrayList<Integer>();
        for (int i = 0; i < transitionToPlaceArcs.length; i++)
            if (doTransition(i))
                transitionsDone.add(i);
        return transitionsDone;
    }

    public List<Integer> doTransitions(List<Integer> transitions) {
        List<Integer> transitionsDone = new ArrayList<Integer>();
        for (Integer i : transitions)
            if (doTransition(i))
                transitionsDone.add(i);
        return transitionsDone;
    }

    public List<Integer> getAliveTransitions() {
        List<Integer> possibleTransitions = new ArrayList<Integer>();
        for (int i = 0; i < placeToTransitionArcs.length; i++)
            if (isTransitionPossible(i))
                possibleTransitions.add(i);
        return possibleTransitions;
    }

    public List<Integer> getOutgoingArcValuesForTransition(int transition) {
        return Utils.toList(transitionToPlaceArcs[transition]);
    }


}
