package org.petri.nets.model.reachability;

import org.petri.nets.model.Transition;

/**
 * Created by Tomasz on 2015-06-05.
 */
public class TransitionEdge {
    private Transition transition;

    public TransitionEdge(Transition transition) {
        this.transition = transition;
    }

    public Transition getTransition() {
        return transition;
    }
}
