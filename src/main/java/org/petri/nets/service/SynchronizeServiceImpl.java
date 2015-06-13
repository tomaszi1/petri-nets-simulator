package org.petri.nets.service;

import org.petri.nets.model.DomainModel;
import org.petri.nets.synhronize.SynchronizePanel;

/**
 * Created by Asia on 2015-05-17.
 */
public class SynchronizeServiceImpl implements SynchronizeService {

    DomainModel model;
    SynchronizePanel synchronizePanel;

    public SynchronizeServiceImpl(DomainModel model, SynchronizePanel synchronizePanel) {
        this.model = model;
        this.synchronizePanel = synchronizePanel;
    }

    @Override
    public void addPlace(int id) {
        model.getSyncModel().getPlaceIds().add(id);
        synchronizePanel.updateMarking();
        synchronizePanel.updateNetMatrix();
    }
    @Override
    public void removePlace(int id){
        for(int ids : model.getSyncModel().getPlaceIds()){
            if(ids==id){
                model.getSyncModel().getPlaceIds().remove(ids);
                synchronizePanel.updateNetMatrix();
                synchronizePanel.updateMarking();
                return;
            }
        }
    }
    @Override
    public void addTransition(int id){
        model.getSyncModel().getTransitionIds().add(id);
        synchronizePanel.updateNetMatrix();
    }
    @Override
    public void removeTransition(int id){
        for(int ids : model.getSyncModel().getTransitionIds()){
            if(ids==id){
                model.getSyncModel().getTransitionIds().remove(ids);
                synchronizePanel.updateNetMatrix();
                return;
            }
        }

    }
    @Override
    public void updateReachabilityGraph() {
        synchronizePanel.updateReachabilityGraph();
    }

    @Override
    public SynchronizePanel getSynchronizePanel() {
        return synchronizePanel;
    }

    /*    @Override
    public void removeFromGraph(int id) {

    }*/
}
