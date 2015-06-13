package org.petri.nets.gui.panel.matrixPanels;

import org.petri.nets.service.GraphService;

import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asia on 2015-06-07.
 */
public class NetMatrixModel extends AbstractTableModel {
    private GraphService graphService;
    private List<Integer> placeIds;
    private List<Integer> transitionIds;

    public NetMatrixModel(GraphService graphService) {
        transitionIds = new ArrayList<>();
        placeIds = new ArrayList<>();
        this.graphService = graphService;
    }

    public void fireTableChanged(TableModelEvent e) {
        transitionIds = new ArrayList<>(graphService.getDomainModel().getPetriNet().getTransitionMap().keySet());
        placeIds = new ArrayList<>(graphService.getInitialMarking().keySet());
        super.fireTableChanged(e);
    }

    @Override
    public int getRowCount() {
        return placeIds.size();
    }

    @Override
    public int getColumnCount() {
        return transitionIds.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }

    @Override
    public String getColumnName(int col) {
        return "T" + transitionIds.get(col);
    }

    public GraphService getGraphService() {
        return graphService;
    }

    public List<Integer> getPlaceIds() {
        return placeIds;
    }

    public List<Integer> getTransitionIds() {
        return transitionIds;
    }

}
