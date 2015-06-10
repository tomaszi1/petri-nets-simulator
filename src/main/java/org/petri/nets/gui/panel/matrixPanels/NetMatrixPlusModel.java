package org.petri.nets.gui.panel.matrixPanels;

import org.petri.nets.model.Place;
import org.petri.nets.model.Transition;
import org.petri.nets.service.GraphService;

/**
 * Created by Asia on 2015-06-09.
 */
public class NetMatrixPlusModel extends NetMatrixModel {

    public NetMatrixPlusModel(GraphService graphService) {
        super(graphService);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        int placeId = getPlaceIds().get(rowIndex);
        int transitId = getTransitionIds().get(columnIndex);
        int valueAt = getValue(placeId, transitId);
        return valueAt;
    }

    private int getValue(int placeId, int transitId){
        Place place = getGraphService().getDomainModel().getPetriNet().getPlaceMap().get(placeId);
        Transition transition = getGraphService().getDomainModel().getPetriNet().getTransitionMap().get(transitId);
        if(place.getTransitionsFrom().get(transition) == null){
            return 0;
        }
        return place.getTransitionsFrom().get(transition).getValue();
    }

}
