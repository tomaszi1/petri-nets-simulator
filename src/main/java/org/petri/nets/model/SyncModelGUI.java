package org.petri.nets.model;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.petri.nets.gui.graph.petriNet.PlaceGraphCell;
import org.petri.nets.gui.graph.petriNet.TransitionGraphCell;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

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

    public BiMap<TransitionGraphCell, Transition> getTransitonGUI() {
        return transitonGUI;
    }

    public Set<Integer> getPlaceIds() {
        return placeGUI.values().stream().map(PetriNetElement::getId).collect(Collectors.toSet());
    }

    public Set<Integer> getTransitionIds() {
        return transitonGUI.values().stream().map(PetriNetElement::getId).collect(Collectors.toSet());
    }

}
