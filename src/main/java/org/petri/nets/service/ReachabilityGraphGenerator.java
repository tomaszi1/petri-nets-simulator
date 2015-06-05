package org.petri.nets.service;

import com.google.common.collect.Sets;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
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

    public ReachabilityGraphGenerator(PetriNet petriNet, int maxVertexCount) {
        this.maxVertexCount = maxVertexCount;
        this.petriNet = petriNet;
    }

    public Graph<State, TransitionEdge> generateGraph() {
        reachGraph = new DirectedSparseMultigraph<>();
        stateQueue = new LinkedList<>();
        int vertexCount = 0;

        State initialState = new State();
        initialState.setMarking(petriNet.getInitialMarking());
        initialState.setDepth(0);

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

}
