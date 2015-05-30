package org.petri.nets.service;

import org.petri.nets.model.Arc;
import org.petri.nets.model.DomainModel;
import org.petri.nets.model.Place;
import org.petri.nets.model.Transition;
import org.petri.nets.synhronize.SynchronizePanel;

/**
 * Created by Asia on 2015-05-17.
 */
public class SynchronizeServiceImpl implements SynchronizeService{

    DomainModel model;
    SynchronizePanel synchronizePanel;


    public SynchronizeServiceImpl(DomainModel model,SynchronizePanel synchronizePanel){
        this.model = model;
        this.synchronizePanel = synchronizePanel;
    }

    @Override
    public void addPlace(int idPlace, Place place) {
        model.getPetriNet().getPlaceMap().put(idPlace, place);
        synchronizePanel.getInitialMarkingPanel().addNewMarking(idPlace, 0);
    }

    @Override
    public void addTransition(int idTransition,  Transition transition) {
        model.getPetriNet().getTransitionMap().put(idTransition, transition);
    }

    @Override
    public void addArc(Place place, Transition transition, int value, boolean isPlaceStart) {
        Arc arc = new Arc(value);
        //dodajemy przejscie do listy przejsc w Place
        addTransitToTransitListInPlace(place, transition, arc,isPlaceStart);

        //dodajemy miejse do listy miejsc w Transition
        addPlaceToPlaceListInTransit(place, transition,arc, isPlaceStart);

    }
    /**
     * dodajemy przejscie do listy przejscw w Place
     * jesli true to dodajemy do transitionFrom;
     * false -> transitionTo;
     **/
    private void addTransitToTransitListInPlace(Place place, Transition transition, Arc arc,boolean isPlaceStart){

        if(isPlaceStart){
            model.getPetriNet().getPlaceMap().get(place.getIdPlace()).getTransitionTo().put(transition,arc);
        }else{
            model.getPetriNet().getPlaceMap().get(place.getIdPlace()).getTransitionFrom().put(transition,arc);
        }
    }
    /**
     * ostatni paramter pyta jakie miejsce dodajemy. Czy można z miejsca dojsc do przejscia
     * jesli true to dodajemy do placeFrom;
     * false -> placeTo;
     **/
    private void addPlaceToPlaceListInTransit(Place place, Transition transition, Arc arc,boolean isPlaceStart){

        if(isPlaceStart){
            model.getPetriNet().getTransitionMap().get(transition.getIdTransition()).getPlaceFrom().put(place,arc);
        }else{
            model.getPetriNet().getTransitionMap().get(transition.getIdTransition()).getPlaceTo().put(place,arc);
        }
    }

    @Override
    public void removePlace(Place place) {
        model.getPetriNet().getInitialMarking().remove(place.getIdPlace());
        model.getPetriNet().getPlaceMap().remove(place.getIdPlace());
        for(Transition transit : place.getTransitionFrom().keySet()){
            model.getPetriNet().getTransitionMap().get(transit.getIdTransition()).getPlaceTo().remove(place);
        }
        for(Transition transit : place.getTransitionTo().keySet()){
            model.getPetriNet().getTransitionMap().get(transit.getIdTransition()).getPlaceFrom().remove(place);
        }
    }

    @Override
    public void removeTransition(Transition transition) {
        model.getPetriNet().getTransitionMap().remove(transition.getIdTransition());
        for(Place place : transition.getPlaceFrom().keySet()){
            model.getPetriNet().getPlaceMap().get(place.getIdPlace()).getTransitionTo().remove(transition);
        }

        for(Place place : transition.getPlaceTo().keySet()){
            model.getPetriNet().getPlaceMap().get(place.getIdPlace()).getTransitionFrom().remove(transition);
        }
    }

    @Override
    public void removeArc(Transition transition, Place place, boolean isPlaceStart){
        removeArcFromTransitionListInPlace(transition, place, isPlaceStart);
        removeArcFromPlaceListInTransition(transition, place, isPlaceStart);

    }

    private void removeArcFromTransitionListInPlace(Transition transition, Place place, boolean isPlaceStart){
        if(isPlaceStart){
            model.getPetriNet().getPlaceMap().get(place.getIdPlace()).getTransitionTo().remove(transition);
        }else{
            model.getPetriNet().getPlaceMap().get(place.getIdPlace()).getTransitionFrom().remove(transition);
        }

    }

    private void removeArcFromPlaceListInTransition(Transition transition, Place place, boolean isPlaceStart){
        if(isPlaceStart){
            model.getPetriNet().getTransitionMap().get(transition.getIdTransition()).getPlaceFrom().remove(place);
        }else{
            model.getPetriNet().getTransitionMap().get(transition.getIdTransition()).getPlaceTo().remove(place);
        }
    }

/*    @Override
    public void removeFromGraph(int id) {

    }*/
}
