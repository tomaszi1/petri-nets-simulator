package org.petri.nets.model.reachability;

import org.petri.nets.model.Transition;

import java.io.Serializable;

/**
 * Created by Tomasz on 2015-06-05.
 */
public class TransitionEdge implements Serializable{
    private Transition transition;

    public TransitionEdge(Transition transition) {
        this.transition = transition;
    }

    public Transition getTransition() {
        return transition;
    }
}
