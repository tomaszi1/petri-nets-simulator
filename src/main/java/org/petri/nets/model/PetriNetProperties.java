package org.petri.nets.model;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.Map;
import java.util.OptionalInt;
import java.util.Set;

/**
 * Created by Tomasz on 2015-06-12.
 */
public class PetriNetProperties {
    private boolean isPetriNetConservative = false;
    private boolean isPetriNetBounded = false;
    private boolean isPetriNetReversible = false;
    private boolean isPetriNetLive = false;
    private boolean isPetriNetSafe = false;
    private Map<Integer, Integer> kBoundedness = Maps.newHashMap();
    private Set<Transition> transitionLiveness = Sets.newHashSet();

    public PetriNetProperties() {
    }

    public PetriNetProperties(PetriNetProperties properties) {
        Preconditions.checkNotNull(properties);

        isPetriNetBounded = properties.isPetriNetBounded;
        isPetriNetReversible = properties.isPetriNetReversible;
        isPetriNetConservative = properties.isPetriNetConservative;
        isPetriNetLive = properties.isPetriNetLive;
        isPetriNetSafe = properties.isPetriNetSafe;

        kBoundedness = Maps.newHashMap(kBoundedness);
        transitionLiveness = Sets.newHashSet(transitionLiveness);
    }

    public boolean isPetriNetConservative() {
        return isPetriNetConservative;
    }

    public boolean isPetriNetBounded() {
        return isPetriNetBounded;
    }

    public boolean isPetriNetReversible() {
        return isPetriNetReversible;
    }

    public boolean isPetriNetLive() {
        return isPetriNetLive;
    }

    public boolean isPetriNetSafe() {
        return isPetriNetSafe;
    }

    public Map<Integer, Integer> getkBoundedness() {
        return Maps.newHashMap(kBoundedness);
    }

    public Set<Transition> getTransitionLiveness() {
        return Sets.newHashSet(transitionLiveness);
    }

    // setters
    public void setIsPetriNetBounded(boolean isPetriNetBounded) {
        this.isPetriNetBounded = isPetriNetBounded;
    }

    public void setIsPetriNetConservative(boolean isPetriNetConservative) {
        this.isPetriNetConservative = isPetriNetConservative;
    }

    public void setIsReversible(boolean isReversible) {
        this.isPetriNetReversible = isReversible;
    }

    public void setkBoundedness(Map<Integer, Integer> kBoundedness) {
        this.kBoundedness = Maps.newHashMap(kBoundedness);
        OptionalInt max = kBoundedness.values().stream().mapToInt(i -> i).max();
        if (!max.isPresent()) {
            isPetriNetSafe = true;
            return;
        }
        isPetriNetSafe = (max.getAsInt() == 1);
    }

    public void setTransitionLiveness(Set<Transition> transitionLiveness) {
        this.transitionLiveness = Sets.newHashSet(transitionLiveness);
    }

    public void setIsPetriNetLive(boolean petriNetLive) {
        this.isPetriNetLive = petriNetLive;
    }
}
