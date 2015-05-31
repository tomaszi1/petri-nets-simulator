package org.petri.nets.gui.panel;

import org.petri.nets.model.DomainModel;
import org.petri.nets.model.InitialMarkingList;
import org.petri.nets.model.PetriNet;
import org.petri.nets.model.Place;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;


/**
 * Created by Asia on 2015-05-24.
 */
public class MarkingTableModel extends AbstractTableModel {
    private DomainModel domainModel ;
    private InitialMarkingList markingList;
    public MarkingTableModel (DomainModel domainModel){
        this.domainModel = domainModel;
        this.markingList = domainModel.getSyncModel().getInitialMarkingList();
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
        //PetriNet petriNet = getDomainModel().getPetriNet();

        //Place place = getPlaceByColumId(getColumnName(col),petriNet);
        int marking = markingList.getMarkingList().get(col)[1];
        return marking;
    }

    @Override
    public String getColumnName(int col) {
        //HashMap<Integer, Place> placeMap = getDomainModel().getPetriNet().getPlaceMap();
        String sufix = "P";
        int idPlace =  markingList.getMarkingList().get(col)[0] + 1;
        return sufix + idPlace ;
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
            //Place place = getPlaceByColumId(getColumnName(col), petriNet);
            Integer placeId =  markingList.getMarkingList().get(col)[0];
            if (value instanceof String) {
                petriNet.setInitialMarking(placeId, Integer.parseInt((String) value));
                markingList.getMarkingList().get(col)[1]= Integer.parseInt((String) value);
            } else if (!(value instanceof Integer))
                return;
            else {
                Integer intval = (Integer) value;
                if (intval < 0)
                    return;
                petriNet.setInitialMarking(placeId, (Integer) value);
                markingList.getMarkingList().get(col)[1]= (Integer) value;
            }
            fireTableCellUpdated(row, col);

        } catch (NumberFormatException e) {
            // do nothing
        }
    }

    public void addNewMarking(Place place, int marking){
        domainModel.getPetriNet().getInitialMarking().put(place.getIdPlace(),marking);
        Integer[] smallMarkingList = {place.getIdPlace(),marking};
        markingList.getMarkingList().add(smallMarkingList);
        //idPlace.add(place.getIdPlace());
        fireTableStructureChanged();
    }
    public void removeMarking(Place place){
        domainModel.getPetriNet().getInitialMarking().remove(place.getIdPlace());
        removeFromlistGUI(place.getIdPlace());
        fireTableStructureChanged();
    }
    public DomainModel getDomainModel() {
        return domainModel;
    }

    public void setDomainModel(DomainModel domainModel) {

        this.domainModel = domainModel;
    }
    private Integer getPlaceId(String name, PetriNet petriNet){
        Integer id = new Integer(name.substring(1));
        return id - 1;
    }
    private Place getPlaceByColumId(String name, PetriNet petriNet){
        Integer id = new Integer(name.substring(1));
        Place place = petriNet.getPlaceMap().get(id-1);
        return place;
    }


    private void removeFromlistGUI(int idPlace){
        for(int i= 0; i<  markingList.getMarkingList().size(); i++){
            if( markingList.getMarkingList().get(i)[0]==idPlace){
                markingList.getMarkingList().remove(i);
                return;
            }
        }
    }
}