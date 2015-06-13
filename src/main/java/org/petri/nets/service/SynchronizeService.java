package org.petri.nets.service;

import org.petri.nets.synhronize.SynchronizePanel;

/**
 * Created by Asia on 2015-05-17.
 */
public interface SynchronizeService {
    void updateReachabilityGraph();
    SynchronizePanel getSynchronizePanel();
    void addTransition(int id);
    void removeTransition(int id);
    void removePlace(int id);
    void addPlace(int id);
}
