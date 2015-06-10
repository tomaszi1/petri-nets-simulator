package org.petri.nets.gui.panel.matrixPanels;

import org.petri.nets.model.Place;
import org.petri.nets.model.Transition;
import org.petri.nets.service.GraphService;

import javax.swing.table.AbstractTableModel;

/**
 * Created by Asia on 2015-06-09.
 */
public class NetMatrixGeneralModel  extends NetMatrixModel {

    public NetMatrixGeneralModel(GraphService graphService) {
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
        int valPlus = 0;
        if(place.getTransitionsFrom().get(transition) != null){
            valPlus = place.getTransitionsFrom().get(transition).getValue();
        }
        int valMinus = 0;
        if(place.getTransitionsTo().get(transition) != null){
            valMinus = place.getTransitionsTo().get(transition).getValue();
        }
        return valPlus-valMinus;
    }
}
