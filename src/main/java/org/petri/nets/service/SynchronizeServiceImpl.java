package org.petri.nets.service;

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
