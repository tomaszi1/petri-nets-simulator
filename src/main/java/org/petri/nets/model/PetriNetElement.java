package org.petri.nets.model;

import java.io.Serializable;

/**
 * Created by Tomasz on 2015-06-06.
 */
public abstract class PetriNetElement implements Serializable{
    private String description;
    private int id;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public abstract String getName();
}
