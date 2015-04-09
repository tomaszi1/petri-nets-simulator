package org.petri.nets.model;

public interface PetriNetPropertyAnalyzer {
    boolean isAlive(PetriNet net);

    int calculateBoundedness(PetriNet net);

    boolean isConservative(PetriNet net);
}
