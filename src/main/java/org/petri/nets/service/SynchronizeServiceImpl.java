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
    public void addPlace() {
        synchronizePanel.updateMarking();
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
