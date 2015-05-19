package org.petri.nets.service;

import org.petri.nets.model.Arc;
import org.petri.nets.model.DomainModel;
import org.petri.nets.model.Place;
import org.petri.nets.model.Transition;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asia on 2015-05-17.
 */
public class SynchronizeServiceImpl implements SynchronizeService{

    DomainModel model;

    public SynchronizeServiceImpl(DomainModel model){
        this.model = model;
    }

    @Override
    public void addPlace(int idPlace) {
        model.getPetriNet().getPlaceList().add(idPlace, new Place(idPlace));
        List<Arc> arcList = new ArrayList<Arc>();
        model.getPetriNet().getPlaceToTransitionArcs().add(idPlace,arcList);
        int transCount = model.getPetriNet().getTransitionsCount();
        while(transCount>0){
            model.getPetriNet().getTransitionToPlaceArcs().get(transCount-1).add( (Arc) null);
            transCount--;
        }
        model.getPetriNet().getInitialMarking().add(0);

    }

    @Override
    public void addTransition(int idTransition) {
        model.getPetriNet().getTransitionList().add(idTransition, new Transition(idTransition));
        List<Arc> arcList = new ArrayList<Arc>();
        model.getPetriNet().getTransitionToPlaceArcs().add(idTransition, arcList);
        int placeCount = model.getPetriNet().getPlacesCount();
        while(placeCount>0){
            model.getPetriNet().getPlaceToTransitionArcs().get(placeCount-1).add( (Arc) null);
            placeCount--;
        }
    }

    @Override
    public void addArc(int idTransition, int idPlace, boolean isPlaceStart) {


    }


    @Override
    public void removePlace(int idPlace) {

    }

    @Override
    public void removeTransition(int idTransition) {

    }

    @Override
    public void removeArc(int idTransition, int idPlace, boolean isPlaceStart){

    }

    @Override
    public void removeFromGraph(int id) {

    }
}
