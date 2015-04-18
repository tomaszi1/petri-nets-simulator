package org.petri.nets.model;

public class Arc {
    private int value;
    private int priority;

    public Arc(int value, int priority) {
        this.value = value;
        this.priority = priority;
    }

    public Arc(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
