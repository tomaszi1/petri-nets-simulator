package org.petri.nets.service;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Graph;
import org.petri.nets.model.*;
import org.petri.nets.model.reachability.State;
import org.petri.nets.model.reachability.TransitionEdge;

import java.util.*;

/**
 * Created by Tomasz on 2015-06-05.
 */
public class ReachabilityGraphGenerator {

    private Graph<State, TransitionEdge> reachGraph;
    private PetriNet petriNet;
    private int maxVertexCount;
    private Queue<State> stateQueue;
    private Map<Integer, Integer> kBoundedness;
    private State initialState;
    private boolean isPetriNetConservative;
    private boolean isPetriNetBounded;

    public ReachabilityGraphGenerator(PetriNet petriNet, int maxVertexCount) {
        this.maxVertexCount = maxVertexCount;
        this.petriNet = petriNet;
        this.kBoundedness = Maps.newHashMap();
        this.isPetriNetConservative = true;
        this.isPetriNetBounded = true;
    }

    public Graph<State, TransitionEdge> generateGraph() {
        reachGraph = new DirectedSparseMultigraph<>();
        stateQueue = new LinkedList<>();
        int vertexCount = 0;

        initialState = new State();
        initialState.setMarking(petriNet.getInitialMarking());
        initialState.setDepth(0);
        updateProperties(initialState);

        reachGraph.addVertex(initialState);

        stateQueue.add(initialState);

        while (!stateQueue.isEmpty() && vertexCount < maxVertexCount) {
            State state = stateQueue.poll();
            Set<Transition> doableTransitions = findDoableTransitions(state);

            transitionLoop:
            for (Transition transition : doableTransitions) {
                State newState = doTransition(transition, state);

                boolean stateIsNew = reachGraph.addVertex(newState);

                if (stateIsNew) {
                    updateProperties(newState);
                    stateQueue.add(newState);
                    vertexCount++;
                }

                Collection<TransitionEdge> existingEdges = reachGraph.findEdgeSet(state, newState);

                for (TransitionEdge existingEdge : existingEdges) {
                    if (existingEdge.getTransition().equals(transition))
                        continue transitionLoop;
                }
                reachGraph.addEdge(new TransitionEdge(transition), state, newState);
            }

        }

        return reachGraph;
    }

    private State doTransition(Transition transition, State state) {
        Map<Integer, Integer> newMarking = state.getMarking();

        transition.getPlacesFrom().forEach((place, arc) -> {
            int newToken = newMarking.get(place.getId()) - arc.getValue();
            newMarking.put(place.getId(), newToken);
        });

        transition.getPlacesTo().forEach((place, arc) -> {
            int newToken = newMarking.get(place.getId()) + arc.getValue();
            newMarking.put(place.getId(), newToken);
        });

        State newState = new State(newMarking);
        newState.setDepth(state.getDepth() + 1);

        return newState;
    }

    private Set<Transition> findDoableTransitions(State state) {
        Set<Transition> doableTransitions = Sets.newHashSet();

        Collection<Transition> allTransitions = petriNet.getTransitions();
        for (Transition t : allTransitions) {
            if (isTransitionDoable(t, state))
                doableTransitions.add(t);
        }

        return doableTransitions;
    }

    private boolean isTransitionDoable(Transition transition, State state) {
        Map<Place, Arc> placesFrom = transition.getPlacesFrom();

        for (Map.Entry<Place, Arc> placeArcEntry : placesFrom.entrySet()) {
            Place place = placeArcEntry.getKey();
            Arc arc = placeArcEntry.getValue();

            if (state.getMarkingForPlace(place) - arc.getValue() < 0)
                return false;

            for (Transition t : place.getTransitionsTo().keySet()) {
                if (t.getPriority() > transition.getPriority())
                    return false;
            }
        }

        return !(placesFrom.isEmpty() || transition.getPlacesTo().isEmpty());
    }

    private void updateProperties(State state) {
        updateKBoundedness(state);
        updateConservation(state);
    }

    private void updateKBoundedness(State state) {
        state.getMarking().forEach(
                (placeId, marking) -> kBoundedness.put(placeId, Math.max(marking, kBoundedness.getOrDefault(placeId, 0))));
    }

    private void updateConservation(State state) {
        if (initialState == null)
            initialState = state;
        if (sumOfTokens(state) != sumOfTokens(initialState)) {
            isPetriNetConservative = false;
            System.out.println("not conservative!");
        }
    }

    private static int sumOfTokens(State state) {
        return state.getMarking().values().stream().mapToInt(v -> v).sum();
    }

    private boolean isPetriNetBounded() {
        return isPetriNetBounded;
    }

    public boolean isPetriNetConservative() {
        return isPetriNetConservative;
    }

    public Map<Integer, Integer> getKBoundedness() {
        return Maps.newHashMap(kBoundedness);
    }
}
