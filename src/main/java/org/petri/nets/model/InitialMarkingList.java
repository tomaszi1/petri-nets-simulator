package org.petri.nets.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Tomasz on 2015-06-01.
 */
public class InitialMarkingList implements Serializable{
    private ArrayList<Integer[]> markingList = new ArrayList<>();

    public ArrayList<Integer[]> getMarkingList() {
        return markingList;
    }

    public void setMarkingList(ArrayList<Integer[]> markingList) {
        this.markingList = markingList;
    }
}
