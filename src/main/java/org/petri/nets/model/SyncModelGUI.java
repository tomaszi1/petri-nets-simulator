package org.petri.nets.model;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.petri.nets.gui.graph.PlaceGraphCell;
import org.petri.nets.gui.graph.TransitionGraphCell;

import java.io.Serializable;

/**
 * Created by Asia on 2015-06-01.
 */
public class SyncModelGUI implements Serializable{
    private BiMap<PlaceGraphCell, Place> placeGUI;
    private BiMap<TransitionGraphCell, Transition> transitonGUI;
    private InitialMarkingList initialMarkingList;
    public SyncModelGUI(){
        placeGUI = HashBiMap.create();
        transitonGUI = HashBiMap.create();
        initialMarkingList = new InitialMarkingList();
    }
    public InitialMarkingList getInitialMarkingList() {
        return initialMarkingList;
    }

    public void setInitialMarkingList(InitialMarkingList initialMarkingList) {
        this.initialMarkingList = initialMarkingList;
    }

    public BiMap<PlaceGraphCell, Place> getPlaceGUI() {
        return placeGUI;
    }

    public void setPlaceGUI(BiMap<PlaceGraphCell, Place> placeGUI) {
        this.placeGUI = placeGUI;
    }

    public BiMap<TransitionGraphCell, Transition> getTransitonGUI() {
        return transitonGUI;
    }

    public void setTransitonGUI(BiMap<TransitionGraphCell, Transition> transitonGUI) {
        this.transitonGUI = transitonGUI;
    }
}
