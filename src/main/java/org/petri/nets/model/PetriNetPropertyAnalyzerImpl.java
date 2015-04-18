package org.petri.nets.model;

import java.util.List;

public class PetriNetPropertyAnalyzerImpl implements PetriNetPropertyAnalyzer {

    public boolean isAlive(PetriNet net) {
        return false;
    }

    public int calculateBoundedness(PetriNet net) {
        return 0;
    }

    public boolean isConservative(PetriNet net) {
        return false;
    }
}
