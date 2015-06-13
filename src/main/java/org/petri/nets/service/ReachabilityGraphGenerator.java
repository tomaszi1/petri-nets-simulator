package org.petri.nets.service;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import edu.uci.ics.jung.graph.DirectedOrderedSparseMultigraph;
import edu.uci.ics.jung.graph.Graph;
import org.petri.nets.model.*;
import org.petri.nets.model.reachability.State;
import org.petri.nets.model.reachability.TransitionEdge;

import java.util.*;

/**
 * Created by Tomasz on 2015-06-05.
 */
public class ReachabilityGraphGenerator {

    private PetriNet petriNet;
    private int maxVertexCount;
    private State initialState;
    private PetriNetProperties properties;
    private Map<Transition, State> lastStateAfterTransitionMap;

    public ReachabilityGraphGenerator(PetriNet petriNet, int maxVertexCount) {
        this.maxVertexCount = maxVertexCount;
        this.petriNet = petriNet;
        properties = new PetriNetProperties();
        properties.setIsPetriNetConservative(true);
        properties.setIsPetriNetBounded(true);
        lastStateAfterTransitionMap = Maps.newHashMap();
    }


    public Graph<State, TransitionEdge> generateGraph() {
        Graph<State, TransitionEdge> reachGraph = new DirectedOrderedSparseMultigraph<>();
        Queue<State> stateQueue = new LinkedList<>();
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

            properties.getTransitionLiveness().addAll(doableTransitions);

            transitionLoop:
            for (Transition transition : doableTransitions) {

                State newState = doTransition(transition, state);

                State lastStateAfterTransition = lastStateAfterTransitionMap.putIfAbsent(transition, newState);
                if (lastStateAfterTransition != null) {
                    Map<Integer, Integer> diff = calcStateDifference(lastStateAfterTransition, newState);
                    if (isGoingToInfinity(diff))
                        newState = makeInifityState(newState, diff.keySet());
                }

                boolean stateIsNew = reachGraph.addVertex(newState);

                if (stateIsNew) {
                    updateProperties(newState);
//                    if (!newState.isInfinite())
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

        properties.setIsReversible(!reachGraph.getInEdges(initialState).isEmpty());

        return reachGraph;
    }

    private State makeInifityState(State newState, Set<Integer> inifinitePlaces) {
        Map<Integer, Integer> infMarking = newState.getMarking();
        for (Integer placeId : inifinitePlaces)
            infMarking.put(placeId, -1);
        State infState = new State(infMarking);
        infState.setDepth(newState.getDepth());
        return infState;
    }

    private boolean isGoingToInfinity(Map<Integer, Integer> diff) {
        return diff.values().stream().filter(tokenDiff -> tokenDiff > 0).count() == diff.size();
    }

    private Map<Integer, Integer> calcStateDifference(State priorState, State posteriorState) {
        Map<Integer, Integer> priorMarking = priorState.getMarking();
        Map<Integer, Integer> posteriorMarking = posteriorState.getMarking();
        Map<Integer, Integer> diff = Maps.newHashMap();

        for (Map.Entry<Integer, Integer> idTokenEntry : posteriorMarking.entrySet()) {
            Integer placeId = idTokenEntry.getKey();
            Integer token = idTokenEntry.getValue();
            Integer prevToken = priorMarking.get(placeId);
            if (!token.equals(prevToken))
                diff.put(placeId, token - prevToken);
        }
        return diff;
    }

    private State doTransition(Transition transition, State state) {
        Map<Integer, Integer> newMarking = state.getMarking();

        transition.getPlacesFrom().forEach((place, arc) -> {
            int newToken = newMarking.get(place.getId()) - arc.getValue();
            newMarking.put(place.getId(), state.getMarkingForPlace(place) == -1 ? -1 : newToken);
        });

        transition.getPlacesTo().forEach((place, arc) -> {
            int newToken = newMarking.get(place.getId()) + arc.getValue();
            newMarking.put(place.getId(), state.getMarkingForPlace(place) == -1 ? -1 : newToken);
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

            if (state.getMarkingForPlace(place) - arc.getValue() < 0 && state.getMarkingForPlace(place)!=-1)
                return false;

            for (Transition t : place.getTransitionsTo().keySet()) {
                if (t.getPriority() > transition.getPriority() && isTransitionDoable(t, state))
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
        Map<Integer, Integer> kBoundedness = properties.getkBoundedness();
        state.getMarking().forEach(
                (placeId, marking) -> kBoundedness.put(placeId, Math.max(marking, kBoundedness.getOrDefault(placeId, 0))));
    }

    private void updateConservation(State state) {
        if (initialState == null)
            initialState = state;
        if (sumOfTokens(state) != sumOfTokens(initialState)) {
            properties.setIsPetriNetConservative(false);
        }
    }

    private static int sumOfTokens(State state) {
        if (state.isInfinite())
            return Integer.MAX_VALUE;
        return state.getMarking().values().stream().mapToInt(v -> v).sum();
    }

    public static State findRoot(Graph<State, TransitionEdge> graph) {
        return graph.getVertices()
                .stream()
                .filter(state -> state.getDepth() == 0)
                .findFirst()
                .get();
    }

    public PetriNetProperties getPetriNetProperties() {
        return new PetriNetProperties(properties);
    }
}
