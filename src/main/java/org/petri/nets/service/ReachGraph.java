package org.petri.nets.service;

import com.google.common.collect.Sets;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;
import org.petri.nets.model.*;

import java.util.*;

/**
 * Created by Małgorzata on 2015-05-24.
 */

public class ReachGraph {
/*
    private Graph<State, Transition> reachGraph;
    private PetriNet petriNet;
    private Set<State> graphVertexList;
    private final int vertexMaxCount;

    public ReachGraph(PetriNet pN, int vertexMaxCount) {
        petriNet = new ListPetriNet();
        if (pN.getPlacesCount() > 0) {
            petriNet.setPlaceMap(pN.getPlaceMap());
            petriNet.setTransitionMap(pN.getTransitionMap());
            petriNet.setInitialMarking(pN.getInitialMarking());
        }
        reachGraph = new DirectedSparseGraph<>();
        graphVertexList = Sets.newHashSet();
        this.vertexMaxCount = vertexMaxCount;
    }

    public void RunReachGraph() {
        GenerateGraph(petriNet.getInitialMarking());
    }

    public void GenerateGraph(LinkedHashMap<Integer, Integer> marking) {
        petriNet.setInitialMarking(marking);
        State prevMarking = new State();
        prevMarking.setMarking(marking);
        //jesli graf nie ma wierzcholka, dodajemy go
        if (reachGraph.getVertexCount() <= 0) {
            reachGraph.addVertex(prevMarking); //przeciazyc toString() dla List<Integer>, zeby wyswietlalo {1,0,0} w wezle
            graphVertexList.add(prevMarking); //dodajemy do listy wierzcholkow
        }
        //tworzymy liste mozliwych przejsc przy tym znakowaniu
        List<Transition> possibleTransitions = new ArrayList<>(); //inicjacja listy przejsc mozliwych do wykonania przy tym znakowaniu
        for (Map.Entry<Integer, Transition> allTransitionsEntry : petriNet.getTransitionMap().entrySet()) { //iterujemy sie po wszystkich przejsciach
            Transition transition = allTransitionsEntry.getValue(); //przejscie
            if (IsTransitionDoable(transition)) { //jesli przejcie jest mozliwe do wykonania przy tym znakowaniu
                possibleTransitions.add(transition); //dodajemy do listy mozliwych przejsc
            }
        }
        List<LinkedHashMap<Integer, Integer>> newMarkingList = new ArrayList<>();
        for (Transition possibleTransition1 : possibleTransitions) { //iterujemy sie po mozliwych do wykonania przejsciach
            Transition possibleTransition = possibleTransition1.copy(); //pobieramy przejscie
            State newState = doTransition(possibleTransition);//inicjalizujemy nowe znakowanie


            State existingState = graphVertexList.contains(newState);// sprawdzamy czy takie znakowanie juz istnieje w grafie
            if (existingState == null) {//jesli znakowanie nie istnieje w grafie, dodajemy nowe znakowanie do grafu
                if (vertexMaxCount < graphVertexList.size()) break; // jesli osiągnelismy limit wierzchołków to nara
                reachGraph.addVertex(newState); //dodajemy nowy wierzcholek
                reachGraph.addEdge(possibleTransition, prevMarking, newState); //dodajemy nowa krakwedz
                newMarkingList.add(newState);
                graphVertexList.add(newState);

            } else
                reachGraph.addEdge(possibleTransition, prevMarking, existingState);//jesli takie znakowanie juz istnieje, dodajemy krawedz od poprzedniego znakowania do istniejacego juz w grafie

        }
        for (int i = 0; newMarkingList.size() > i; i++) {
            GenerateGraph(newMarkingList.get(i)); //wywolujemy funkcje dla nowego znakowania
        }
    }

    //sprawdzamy czy znakowanie juz istnieje w grafie
    //zwraca wskaznik na istniejacy wezel lub null, jesli nie ma takiego znakowania w grafie
    private State stateExists(State state) {
        for (State s : graphVertexList)
            if (state.equals(s)) return s;
        return null;
    }

    //jesli we wszystkich miejscach, ktore wchodza do przejscia jest wystarczajaco duzo znacznikow
    //mozemy wykonac przejscie
    private boolean IsTransitionDoable(Transition transition) {
        boolean isTransitionDoable = false;
        HashMap<Place, Arc> startingPlacesMap = new HashMap<>();
        startingPlacesMap.putAll(transition.getPlacesFrom());
        for (Map.Entry<Place, Arc> startingPlacesSet : startingPlacesMap.entrySet()) {
            int placeMarking = startingPlacesSet.getKey().getInitialMarking();
            int arcValue = startingPlacesSet.getValue().getValue();
            if (placeMarking < arcValue || arcValue == 0) return false;
            isTransitionDoable = true;
        }

        return !transition.getPlacesTo().isEmpty() && isTransitionDoable;

    }

    //jesli wybralismy juz przejscie o najwyzszym priorytecie
    private Map<Integer, Integer> doTransition(Transition transition) {
        Map<Integer, Integer> marking = petriNet.getInitialMarking();

        //zabieramy znaczniki z miejsc wejsciowych w zaleznosci od wartosci lukow z nich wychodzacych

        for (Map.Entry<Place, Arc> startingPlaceArc : transition.getPlacesFrom().entrySet()) {
            Place startPlace = startingPlaceArc.getKey();
            Arc arcFromStartingPlace = startingPlaceArc.getValue();

            int newMarkingForStartPlace = startPlace.getInitialMarking() - arcFromStartingPlace.getValue();   //odejmujemy znaczniki z miejsca startowego (tyle ile wartość łuku wychodzacego)
            marking.put(startPlace.getId(), newMarkingForStartPlace);
        }

        for (Map.Entry<Place, Arc> endPlaceWithArc : transition.getPlacesTo().entrySet()) { //iterujemy sie po miejscach, do ktorych prowadzi przejscie
            //wykonanie przejscia
            Place endPlace = endPlaceWithArc.getKey();              //miejsce, do ktorego trafilismy z przejscia
            Arc arcIntoEndPlace = endPlaceWithArc.getValue();   //luk, ktory prowadzi do miejsca

            int newMarkingForEndPlace = endPlace.getInitialMarking() + arcIntoEndPlace.getValue(); //dodajemy znaczniki do miejsca koncowego (tyle ile wartość łuku wchodzacego)
            marking.put(endPlace.getId(), newMarkingForEndPlace);
        }
        return marking;
    }

    public Graph<State, Transition> getReachGraph() {
        return reachGraph;
    }*/
}