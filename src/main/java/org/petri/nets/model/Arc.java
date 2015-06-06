package org.petri.nets.model;

import com.google.common.base.Preconditions;

import java.io.Serializable;

public class Arc implements Serializable {
    private int value;
    private PetriNetElement start, end;

    public Arc(int value) {
        setValue(value);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        Preconditions.checkArgument(value > 0);
        this.value = value;
    }

    public void setStart(PetriNetElement start) {
        this.start = start;
    }

    public void setEnd(PetriNetElement end) {
        this.end = end;
    }

    public PetriNetElement getEnd() {
        return end;
    }

    public PetriNetElement getStart() {
        return start;
    }
}
