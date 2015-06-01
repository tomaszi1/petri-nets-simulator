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
    public SyncModelGUI(){
        placeGUI = HashBiMap.create();
        transitonGUI = HashBiMap.create();
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
