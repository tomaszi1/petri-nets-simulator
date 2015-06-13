package org.petri.nets.gui.panel.matrixPanels;

import org.petri.nets.model.Place;
import org.petri.nets.model.Transition;
import org.petri.nets.service.GraphService;

/**
 * Created by Asia on 2015-06-09.
 */
public class NetMatrixMinusModel  extends NetMatrixModel {

    public NetMatrixMinusModel(GraphService graphService) {
        super(graphService);
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        int placeId = getPlaceIds().get(rowIndex);
        int transitId = getTransitionIds().get(columnIndex);
        return getValue(placeId, transitId);
    }

    private int getValue(int placeId, int transitId){
        Place place = getGraphService().getDomainModel().getPetriNet().getPlaceMap().get(placeId);
        Transition transition = getGraphService().getDomainModel().getPetriNet().getTransitionMap().get(transitId);
        if(place.getTransitionsTo().get(transition) == null){
            return 0;
        }
        return place.getTransitionsTo().get(transition).getValue();
    }

}
