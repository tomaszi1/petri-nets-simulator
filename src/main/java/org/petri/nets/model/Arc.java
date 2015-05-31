package org.petri.nets.model;

import java.io.Serializable;

public class Arc implements Serializable{
    private int value;


    public Arc(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
