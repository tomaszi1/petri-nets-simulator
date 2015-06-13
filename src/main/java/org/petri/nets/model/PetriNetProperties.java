package org.petri.nets.model;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.Map;
import java.util.Set;

/**
 * Created by Tomasz on 2015-06-12.
 */
public class PetriNetProperties {
    private boolean isPetriNetConservative;
    private boolean isPetriNetBounded;
    private boolean isPetriNetReversible;
    private Map<Integer, Integer> kBoundedness = Maps.newHashMap();
    private Set<Transition> transitionLiveness = Sets.newHashSet();

    public PetriNetProperties() {
    }

    public PetriNetProperties(PetriNetProperties properties) {
        Preconditions.checkNotNull(properties);
        isPetriNetBounded = properties.isPetriNetBounded;
        isPetriNetReversible = properties.isPetriNetReversible;
        isPetriNetConservative = properties.isPetriNetConservative;
        kBoundedness = Maps.newHashMap(kBoundedness);
        transitionLiveness = Sets.newHashSet(transitionLiveness);
    }

    public boolean isPetriNetConservative() {
        return isPetriNetConservative;
    }

    public void setIsPetriNetConservative(boolean isPetriNetConservative) {
        this.isPetriNetConservative = isPetriNetConservative;
    }

    public boolean isPetriNetBounded() {
        return isPetriNetBounded;
    }

    public void setIsPetriNetBounded(boolean isPetriNetBounded) {
        this.isPetriNetBounded = isPetriNetBounded;
    }

    public boolean isPetriNetReversible() {
        return isPetriNetReversible;
    }

    public void setIsReversible(boolean isReversible) {
        this.isPetriNetReversible = isReversible;
    }

    public Map<Integer, Integer> getkBoundedness() {
        return kBoundedness;
    }

    public void setkBoundedness(Map<Integer, Integer> kBoundedness) {
        this.kBoundedness = kBoundedness;
    }

    public Set<Transition> getTransitionLiveness() {
        return transitionLiveness;
    }

    public void setTransitionLiveness(Set<Transition> transitionLiveness) {
        this.transitionLiveness = transitionLiveness;
    }
}
