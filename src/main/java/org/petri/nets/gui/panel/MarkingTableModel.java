package org.petri.nets.gui.panel;

import org.petri.nets.model.DomainModel;
import org.petri.nets.model.PetriNet;

import javax.swing.table.AbstractTableModel;


/**
 * Created by Asia on 2015-05-24.
 */
public class MarkingTableModel extends AbstractTableModel {
    private DomainModel domainModel ;
    public MarkingTableModel (DomainModel domainModel){
        this.domainModel = domainModel;
    }
    @Override
    public int getRowCount() {
        return 1;
    }

    @Override
    public int getColumnCount() {
        return getDomainModel().getPetriNet().getInitialMarking().size(); //FIXME
    }

    @Override
    public Object getValueAt(int row, int col) {
        return getDomainModel().getPetriNet().getInitialMarking().get(col);
    }

    @Override
    public String getColumnName(int col) {

        return "P" + (getDomainModel().getPetriNet().getPlaceMap().get(col).getIdPlace()+1);
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
            PetriNet petriNet = getDomainModel().getPetriNet();

            if (value instanceof String) {
                petriNet.setInitialMarking(col, Integer.parseInt((String) value));
            } else if (!(value instanceof Integer))
                return;
            else {
                Integer intval = (Integer) value;
                if (intval < 0)
                    return;
                petriNet.setInitialMarking(col, (Integer) value);
            }
            fireTableCellUpdated(row, col);

        } catch (NumberFormatException e) {
            // do nothing
        }
    }

    public void addNewMarking(int id, int marking){
        domainModel.getPetriNet().getInitialMarking().add(id,marking);
    }

    public void refreshTableModel(){
        fireTableStructureChanged();
    }

    public DomainModel getDomainModel() {
        return domainModel;
    }

    public void setDomainModel(DomainModel domainModel) {
        this.domainModel = domainModel;
    }
}