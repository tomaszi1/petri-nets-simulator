package org.petri.nets.service;

import org.petri.nets.model.Arc;
import org.petri.nets.model.DomainModel;
import org.petri.nets.model.Place;
import org.petri.nets.model.Transition;

/**
 * Created by Asia on 2015-05-17.
 */
public class SynchronizeServiceImpl implements SynchronizeService{

    DomainModel model;


    public SynchronizeServiceImpl(DomainModel model){
        this.model = model;
    }

    @Override
    public void addPlace(int idPlace, Place place) {
        model.getPetriNet().getPlaceMap().put(idPlace, place);
        model.getPetriNet().getInitialMarking().add(idPlace,0);

    }

    @Override
    public void addTransition(int idTransition,  Transition transition) {
        model.getPetriNet().getTransitionMap().put(idTransition, transition);
    }

    @Override
    public void addArc(Place place, Transition transition, boolean isPlaceStart) {
        boolean transitFromPlace =  true;
        boolean placeFromTransition = false;
        if(isPlaceStart){
            transitFromPlace = false;
            placeFromTransition = true;
        }
        addTransitToPlace(place,transition,transitFromPlace);
        addPlaceToTransit(place,transition, placeFromTransition);

    }
    /**
     * ostatni paramter pyta czy jakie przejscie dodajemy. Czy można z przejscia dojsc do miejsca
     * jesli true to dodajemy do transitionFrom;
     * false -> transitionTo;
     **/
    private void addTransitToPlace(Place place, Transition transition, boolean transitFromPlace){

        Arc arc = new Arc(0,0);

        if(transitFromPlace){
            model.getPetriNet().getPlaceMap().get(place.getIdPlace()).getTransitionFrom().put(transition,arc);
        }else{
            model.getPetriNet().getPlaceMap().get(place.getIdPlace()).getTransitionTo().put(transition,arc);
        }
    }
    /**
     * ostatni paramter pyta czy jakie miejsce dodajemy. Czy można z miejsca dojsc do przejscia
     * jesli true to dodajemy do placeFrom;
     * false -> placeTo;
     **/
    private void addPlaceToTransit(Place place, Transition transition, boolean placeFromTransition){

        Arc arc = new Arc(0,0);

        if(placeFromTransition){
            model.getPetriNet().getTransitionMap().get(transition.getIdTransition()).getPlaceFrom().put(place,arc);
        }else{
            model.getPetriNet().getTransitionMap().get(transition.getIdTransition()).getPlaceTo().put(place,arc);
        }
    }

    @Override
    public void removePlace(Place place) {

    }

    @Override
    public void removeTransition(Transition transition) {

    }

    @Override
    public void removeArc(Transition transition, Place place, boolean isPlaceStart){

    }

/*    @Override
    public void removeFromGraph(int id) {

    }*/
}
