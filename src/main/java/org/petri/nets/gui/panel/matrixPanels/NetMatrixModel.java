package org.petri.nets.gui.panel.matrixPanels;

import org.petri.nets.service.GraphService;

import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asia on 2015-06-07.
 */
public class NetMatrixModel  extends AbstractTableModel {
    private GraphService graphService;
    private NetMatrixModel netMatrixModel;
    private List<Integer> placeIds;
    private List<Integer> transitionIds;

    public NetMatrixModel(GraphService graphService){
        setTransitionIds(graphService.getDomainModel().getSyncModel().getTransitionIds());
        setPlaceIds(graphService.getDomainModel().getSyncModel().getPlaceIds());
        this.setGraphService(graphService);
    }
    public void fireTableChanged(TableModelEvent e) {
        transitionIds = new ArrayList<>(graphService.getDomainModel().getPetriNet().getTransitionMap().keySet());
        placeIds = new ArrayList<>(graphService.getInitialMarking().keySet());
        super.fireTableChanged(e);
    }
    @Override
    public int getRowCount() {
        return getPlaceIds().size();
    }

    @Override
    public int getColumnCount() {
        return getTransitionIds().size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }


    public GraphService getGraphService() {
        return graphService;
    }

    public void setGraphService(GraphService graphService) {
        this.graphService = graphService;
    }

    public NetMatrixModel getNetMatrixModel() {
        return netMatrixModel;
    }

    public void setNetMatrixModel(NetMatrixModel netMatrixModel) {
        this.netMatrixModel = netMatrixModel;
    }

    public List<Integer> getPlaceIds() {
        return placeIds;
    }

    public void setPlaceIds(List<Integer> placeIds) {
        this.placeIds = placeIds;
    }

    public List<Integer> getTransitionIds() {
        return transitionIds;
    }

    public void setTransitionIds(List<Integer> transitionIds) {
        this.transitionIds = transitionIds;
    }
}
