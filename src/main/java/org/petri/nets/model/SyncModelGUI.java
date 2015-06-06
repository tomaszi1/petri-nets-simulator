package org.petri.nets.model;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.petri.nets.gui.graph.petriNet.PlaceGraphCell;
import org.petri.nets.gui.graph.petriNet.TransitionGraphCell;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asia on 2015-06-01.
 */
public class SyncModelGUI implements Serializable{
    private BiMap<PlaceGraphCell, Place> placeGUI;
    private BiMap<TransitionGraphCell, Transition> transitonGUI;
    private List<Integer> placeIds;
    public SyncModelGUI(){
        placeGUI = HashBiMap.create();
        transitonGUI = HashBiMap.create();
        placeIds = new ArrayList<>();
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

    public List<Integer> getPlaceIds() {
        return placeIds;
    }

    public void setPlaceIds(List<Integer> placeIds) {
        this.placeIds = placeIds;
    }
}
