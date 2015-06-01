package org.petri.nets.service;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import org.petri.nets.model.*;

import java.util.*;

/**
 * Created by Małgorzata on 2015-05-24.
 */

public class ReachGraph {

    private Graph<HashMap<Integer,Integer>, Transition> reachGraph;
    private PetriNet petriNet;
    private List<HashMap<Integer,Integer>> graphVertexList;
    private final int vertexMaxCount;

    public ReachGraph(PetriNet pN, int vertexMaxCount) {
        petriNet = new ListPetriNet();
        if (pN.getPlacesCount() > 0) {
            petriNet.setPlaceMap(pN.getPlaceMap());
            petriNet.setTransitionMap(pN.getTransitionMap());
            petriNet.setInitialMarking(pN.getInitialMarking());
        }
        reachGraph = new SparseMultigraph<>();
        graphVertexList=new ArrayList<>();
        this.vertexMaxCount=vertexMaxCount;
    }

    public void RunReachGraph() {
        GenerateGraph(petriNet.getInitialMarking());
    }

    private void GenerateGraph(HashMap<Integer,Integer> marking) {
        petriNet.setInitialMarking(marking);
        HashMap<Integer,Integer> prevMarking = new HashMap<>();
        prevMarking.putAll(marking);
        //jesli graf nie ma wierzcholka, dodajemy go
        if (reachGraph.getVertexCount() <= 0) {
            reachGraph.addVertex(prevMarking); //przeciazyc toString() dla List<Integer>, zeby wyswietlalo {1,0,0} w wezle
            graphVertexList.add(prevMarking); //dodajemy do listy wierzcholkow
        }
        //tworzymy liste mozliwych przejsc przy tym znakowaniu
        List<Transition> possibleTransitions = new ArrayList<>(); //inicjacja listy przejsc mozliwych do wykonania przy tym znakowaniu
        for(Map.Entry<Integer,Transition> allTransitionsEntry:petriNet.getTransitionMap().entrySet()){ //iterujemy sie po wszystkich przejsciach
            Transition transition = allTransitionsEntry.getValue(); //przejscie
            if(IsTransitionDoable(transition)) { //jesli przejcie jest mozliwe do wykonania przy tym znakowaniu
                possibleTransitions.add(transition); //dodajemy do listy mozliwych przejsc
            }
        }
        List<HashMap<Integer,Integer>>newMarkingList=new ArrayList<>();
        for(int i=0;possibleTransitions.size()>i; i++){ //iterujemy sie po mozliwych do wykonania przejsciach
            Transition possibleTransition=(possibleTransitions.get(i)).copy(); //pobieramy przejscie
            HashMap<Integer,Integer> newMarking = new HashMap<>(); //inicjalizujemy nowe znakowanie
            newMarking.putAll(DoTransition(possibleTransition)); //wykonujemy przejscie, pobieramy nowe znakowanie po jego wykonaniu
            //jesli taki wierzcholek juz istnieje, dodajemy krawedz pomiedzy poprzednim znakowaniem, a tym wyszukanym istniejacym w grafie
            HashMap<Integer,Integer> existingMarking=IsMarkingNew(newMarking);// sprawdzamy czy takie znakowanie juz istnieje w grafie
                if (existingMarking == null) {//jesli znakowanie nie istnieje w grafie, dodajemy nowe znakowanie do grafu
                    if(vertexMaxCount>graphVertexList.size()){// jesli nie osiagnelismy maksymalnej liczby wierzcholkow do wygenerowania, to jedziemy dalej
                    reachGraph.addVertex(newMarking); //dodajemy nowy wierzcholek
                    reachGraph.addEdge(possibleTransition, prevMarking, newMarking); //dodajemy nowa krakwedz
                    newMarkingList.add(newMarking);
                    graphVertexList.add(newMarking);
                    }
                } else
                    reachGraph.addEdge(possibleTransition, prevMarking, existingMarking);//jesli takie znakowanie juz istnieje, dodajemy krawedz od poprzedniego znakowania do istniejacego juz w grafie

        }
        for(int i=0;newMarkingList.size()>i;i++) {
            GenerateGraph(newMarkingList.get(i)); //wywolujemy funkcje dla nowego znakowania
        }
    }

    //sprawdzamy czy znakowanie juz istnieje w grafie
    //zwraca wskaznik na istniejacy wezel lub null, jesli nie ma takiego znakowania w grafie
    private HashMap<Integer,Integer> IsMarkingNew(HashMap<Integer,Integer> marking){
        for(int i=0;graphVertexList.size()>i;i++){
            if(marking.equals(graphVertexList.get(i)))return graphVertexList.get(i);
        }
        return null;
    }

    //jesli we wszystkich miejscach, ktore wchodza do przejscia jest wystarczajaco duzo znacznikow
    //mozemy wykonac przejscie
    private boolean IsTransitionDoable(Transition transition) {
        boolean isTransitionDoable = false;
        HashMap<Place, Arc> startingPlacesMap = new HashMap<>();
        startingPlacesMap.putAll(transition.getPlaceFrom());
        for (Map.Entry<Place, Arc> startingPlacesSet : startingPlacesMap.entrySet()) {
            int placeMarking = startingPlacesSet.getKey().getMarking();
            int arcValue = startingPlacesSet.getValue().getValue();
            if (placeMarking < arcValue || arcValue==0) return false;
            isTransitionDoable = true;
        }
        return isTransitionDoable;
    }

    //jesli wybralismy juz przejscie o najwyzszym priorytecie
    private HashMap<Integer,Integer> DoTransition(Transition transition) {
        HashMap<Integer,Integer> marking = new HashMap<>();
        marking.putAll(petriNet.getInitialMarking());
        HashMap<Place, Arc> startingPlaces = new HashMap<>();
        startingPlaces.putAll(transition.getPlaceFrom());
        //zabieramy znaczniki z miejsc wejsciowych w zaleznosci od wartosci lukow z nich wychodzacych
        int takenMarkingCount = 0;
        for (Map.Entry<Place, Arc> startingPlacesSet : startingPlaces.entrySet()) {
            Place startPlace = startingPlacesSet.getKey();
            Arc arcLeavingStartPlace = startingPlacesSet.getValue();
            takenMarkingCount += arcLeavingStartPlace.getValue();
            int newMarkingForStartPlace=startPlace.getMarking() - arcLeavingStartPlace.getValue();   //odejmujemy znaczniki z miejsca startowego (tyle ile wartość łuku wychodzacego)
            marking.replace(startPlace.getIdPlace(), startPlace.getMarking(), newMarkingForStartPlace);
        }
        for (Map.Entry<Place, Arc> endPlaceSet : transition.getPlaceTo().entrySet()) //iterujemy sie po miejscach, do ktorych prowadzi przejscie
        {
            //wykonanie przejscia
            Place endPlace = endPlaceSet.getKey();              //miejsce, do ktorego trafilismy z przejscia
            Arc arcEnteringEndPlace = endPlaceSet.getValue();   //luk, ktory prowadzi do miejsca
            if (takenMarkingCount >= arcEnteringEndPlace.getValue())     //jesli nie, przejscia nie mozna wykonac - chyba wtedy jest niezywotne
            {
                int newMarkingForEndPlace=endPlace.getMarking() + arcEnteringEndPlace.getValue(); //dodajemy znaczniki do miejsca koncowego (tyle ile wartość łuku wchodzacego)
                marking.replace(endPlace.getIdPlace(),endPlace.getMarking(),newMarkingForEndPlace);
            }
        }
        return marking;
    }

    public Graph<HashMap<Integer, Integer>, Transition> getReachGraph() {
        return reachGraph;
    }
}