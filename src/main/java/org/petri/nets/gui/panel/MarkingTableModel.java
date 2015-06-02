package org.petri.nets.gui.panel;

import org.petri.nets.service.GraphService;

import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Asia on 2015-05-24.
 */
public class MarkingTableModel extends AbstractTableModel {
    private GraphService graphService;
    private List<Integer> placeIds;

    public MarkingTableModel(GraphService graphService) {
        this.graphService = graphService;
        placeIds =graphService.getDomainModel().getSyncModel().getPlaceIds();
    }
    @Override
    public int getRowCount() {
        return 1;
    }

    @Override
    public int getColumnCount() {
        return placeIds.size(); //FIXME
    }

    @Override
    public Object getValueAt(int ignore, int col) {
        return graphService.getInitialMarking(placeIds.get(col));
    }

    @Override
    public String getColumnName(int col) {
        return "P" + placeIds.get(col);
    }

    @Override
    public Class<?> getColumnClass(int col) {
        return Integer.class;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return row == 0;
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        try {
            if (value instanceof String) {
                graphService.setInitialMarking(placeIds.get(col), Integer.parseInt((String) value));
            } else if ((value instanceof Integer)) {
                Integer intval = (Integer) value;
                if (intval < 0)
                    return;
                graphService.setInitialMarking(placeIds.get(col), intval);
            } else return;

            fireTableCellUpdated(row, col);
        } catch (NumberFormatException e) {
            // do nothing
        }
    }

    @Override
    public void fireTableChanged(TableModelEvent e) {
        placeIds = new ArrayList<>(graphService.getInitialMarking().keySet());
        super.fireTableChanged(e);
    }

}