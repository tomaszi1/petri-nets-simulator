package org.petri.nets.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Asia on 2015-05-31.
 */
public class InitialMarkingList implements Serializable {
    private ArrayList<Integer[]> markingList;

    public InitialMarkingList(){
        markingList =  new ArrayList<Integer[]>();
    }

    public ArrayList<Integer[]> getMarkingList() {
        return markingList;
    }

    public void setMarkingList(ArrayList<Integer[]> markingList) {
        this.markingList = markingList;
    }
}
