package org.petri.nets.service;

import com.sun.deploy.util.OrderedHashSet;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import org.petri.nets.model.*;

import java.util.*;

/**
 * Created by Małgorzata on 2015-05-24.
 */
public class ReachGraph {
    public Graph<List<Integer>, Transition> reachGraph;
    private PetriNet petriNet;

    public ReachGraph(PetriNet pN) {
        petriNet = new ListPetriNet();
        if (pN.getPlacesCount() > 0) {
            petriNet.setPlaceMap(pN.getPlaceMap());
            petriNet.setTransitionMap(pN.getTransitionMap());
            petriNet.setInitialMarking(pN.getInitialMarking());
        }
        reachGraph = new SparseMultigraph<>();
    }

    public void RunReachGraph() {
        GenerateGraph(petriNet.getInitialMarking());
    }

    public void GenerateGraph(List<Integer> marking) {
        //szukamy mozl00iwych przejsc do wykonania przy tym znakowaniu
        //jesli w jakims miejscu jest znacznik, probujemy wykonac z niego przejscie

        List<Integer> prevMarking = new ArrayList<>();
        prevMarking.addAll(petriNet.getInitialMarking());

        if (reachGraph.getVertexCount() <= 0) {
            //root
            reachGraph.addVertex(prevMarking); //przeciazyc toString() dla List<Integer>, zeby wyswietlalo {1,0,0} w wezle
        }

        List<Integer> newMarking = new ArrayList<>();
/*
        for (int i = 0; i < petriNet.getPlacesCount(); i++) {
            if (prevMarking.get(i) > 0) {
                boolean equalMarking = true;
                HashMap<Transition, List<Integer>> possibleTransitions = PossibleTransitionsMap(petriNet.getPlaceMap().get(i));
                if (reachGraph.getVertexCount() > 0) {         //dla kazdej possibleTransition
                    for (Map.Entry<Transition, List<Integer>> transitionToMarking : possibleTransitions.entrySet()) {
                        Transition transition = new Transition(transitionToMarking.getKey().getIdTransition());
                        transition = transitionToMarking.getKey();
                        newMarking.addAll(transitionToMarking.getValue());
                        //if(iniMarking==nextMarking) Compare, jesli sie znakowanie zmienilo to:
                        equalMarking = newMarking.equals(prevMarking);
                        if (!equalMarking) {
                            reachGraph.addVertex(newMarking);
                            reachGraph.addEdge(transition, prevMarking, newMarking);
                        }
                    }
                }
                if (!equalMarking)
                    GenerateGraph(newMarking);
            }
        }
        */
    }

    //w takim miejscu szukamy listy mozliwych przejsc
    //dla kazdego przejscia rekurencyjnie generujemy nowe znakowanie



    //jesli we wszystkich miejscach, ktore wchodza do przejscia jest wystarczajaco duzo znacznikow
    //mozemy wykonac przejscie
    public boolean IsTransitionDoable(HashMap<Place, Arc> startingPlacesMap) {
        boolean isTransitionDoable = false;
        LinkedHashMap<Place, Arc> lhm = new LinkedHashMap<>();

        for (Map.Entry<Place, Arc> startingPlacesSet : startingPlacesMap.entrySet()) {
            int placeMarking = startingPlacesSet.getKey().getMarking();
            int arcValue = startingPlacesSet.getValue().getValue();
            if (placeMarking < arcValue) return false;
            isTransitionDoable = true;
        }
        return isTransitionDoable;
    }

    //jesli wybralismy juz przejscie o najwyzszym priorytecie
    public HashMap<Transition, List<Integer>> DoTransition(Transition transition) {
        HashMap<Transition, List<Integer>> possibleTransitions = new HashMap<>();     //zwracana mapa mozliwych przejsc ze znakowaniem, ktore tworza

        HashMap<Place, Arc> startingPlaces = new HashMap<>();
        startingPlaces.putAll(petriNet.getTransitionMap().get(transition.getIdTransition()).getPlaceFrom());

        if (IsTransitionDoable(startingPlaces)) {
            //zabieramy znaczniki z miejsc wejsciowych w zaleznosci od wartosci lukow z nich wychodzacych
            int takenMarkingCount = 0;
            for (Map.Entry<Place, Arc> sPSet : startingPlaces.entrySet()) {
                Place startPlace = sPSet.getKey();
                Arc arcLeavingStartPlace = sPSet.getValue();
                takenMarkingCount += arcLeavingStartPlace.getValue();
                startPlace.setMarking(startPlace.getMarking() - arcLeavingStartPlace.getValue());   //odejmujemy znaczniki z miejsca startowego (tyle ile wartość łuku wychodzacego)
                petriNet.getInitialMarking().set(startPlace.getIdPlace(), startPlace.getMarking()); //ustawiamy nowe znakowanie w miejscu
            }
            for (Map.Entry<Place, Arc> endPlaceSet : transition.getPlaceTo().entrySet()) //iterujemy sie po miejscach, do ktorych prowadzi przejscie
            {
                //wykonanie przejscia
                Place endPlace = endPlaceSet.getKey();              //miejsce, do ktorego trafilismy z przejscia
                Arc arcEnteringEndPlace = endPlaceSet.getValue();   //luk, ktory prowadzi do miejsca
                    /*
                    * TODO: rozszerzyc o priorytety
                    * */
                if (takenMarkingCount >= arcEnteringEndPlace.getValue())     //jesli nie, przejscia nie mozna wykonac - chyba wtedy jest niezywotne
                {
                    endPlace.setMarking(endPlace.getMarking() + arcEnteringEndPlace.getValue()); //dodajemy znaczniki do miejsca koncowego (tyle ile wartość łuku wchodzacego)
                    petriNet.getInitialMarking().set(endPlace.getIdPlace(), endPlace.getMarking());
                }
            }
            possibleTransitions.put(transition, petriNet.getInitialMarking()); //dodaj do listy mozliwych przejsc
        }
        return possibleTransitions;
    }
}