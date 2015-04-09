package org.petri.nets.model;

import java.util.List;

public class PetriNetPropertyAnalyzerImpl implements PetriNetPropertyAnalyzer {

    public boolean isAlive(PetriNet net) {
        return !net.getAliveTransitions().isEmpty();
    }

    public int calculateBoundedness(PetriNet net) {
        return 0;
    }

    public boolean isConservative(PetriNet net) {
        for (int i = 0; i < net.getTransitionsCount(); i++) {
            List<Integer> inArcValues = net.getIngoingArcValuesForTransition(i);
            List<Integer> outArcValues = net.getOutgoingArcValuesForTransition(i);
            int inSum = 0, outSum = 0;
            for (int v : inArcValues)
                inSum += v;
            for (int v : outArcValues)
                outSum += v;

            if (inSum != outSum)
                return false;
        }
        return true;
    }
}
