package org.petri.nets.model.reachability;

import com.google.common.collect.Lists;
import org.petri.nets.model.Transition;

import java.util.List;
import java.io.Serializable;

/**
 * Created by Tomasz on 2015-06-05.
 */
public class TransitionEdge implements Serializable{
    private Transition transition;
    private List<TransitionEdge> pathFromRoot;

    public TransitionEdge(Transition transition) {
        this.transition = transition;
    }

    public Transition getTransition() {
        return transition;
    }

    public List<TransitionEdge> getPathFromRoot() {
        return Lists.newArrayList(pathFromRoot);
    }

    public void setPathFromRoot(List<TransitionEdge> pathFromRoot) {
        this.pathFromRoot = Lists.newArrayList(pathFromRoot);
    }
}
