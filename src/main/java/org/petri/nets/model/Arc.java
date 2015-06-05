package org.petri.nets.model;

import com.google.common.base.Preconditions;

import java.io.Serializable;

public class Arc implements Serializable {
    private int value;


    public Arc(int value) {
        Preconditions.checkArgument(value > 0);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        Preconditions.checkArgument(value > 0);
        this.value = value;
    }

}
