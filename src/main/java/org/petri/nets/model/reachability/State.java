package org.petri.nets.model.reachability;

import com.google.common.collect.Maps;
import org.petri.nets.model.Place;
import org.petri.nets.model.Transition;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class State  implements Serializable {
    private Map<Integer, Integer> marking;
    private List<Transition> path;
    private int depth = 0;

    public State(Map<Integer, Integer> marking) {
        this.marking = Maps.newHashMap(marking);
    }

    public State() {
        marking = Maps.newHashMap();
    }

    public Map<Integer, Integer> getMarking() {
        return Maps.newHashMap(marking);
    }

    public void setMarking(Map<Integer, Integer> marking) {
        this.marking = Maps.newHashMap(marking);
    }

    public int getMarkingForPlace(Place place) {
        return marking.get(place.getId());
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public boolean isInfinite(){
        return marking.values().stream().filter(token -> token.equals(-1)).count() > 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state1 = (State) o;
        return Maps.difference(state1.marking,marking).areEqual();
/*        MapDifference<Integer, Integer> markingDiff = Maps.difference(state1.marking, marking);
        Map<Integer, MapDifference.ValueDifference<Integer>> differences = markingDiff.entriesDiffering();
        if(!markingDiff.entriesOnlyOnLeft().isEmpty() || !markingDiff.entriesOnlyOnRight().isEmpty())
            return false;
        for (Map.Entry<Integer, MapDifference.ValueDifference<Integer>> diffEntry : differences.entrySet()) {
            MapDifference.ValueDifference<Integer> diff = diffEntry.getValue();
            Integer left = diff.leftValue();
            Integer right = diff.rightValue();
            if (left != null && (right == null || !right.equals(-1) && !left.equals(-1))) {
                return false;
            }
        }*/
        //return true;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return "State{" +
                "marking=" + marking.values() +
                ", depth=" + depth +
                '}';
    }


    public List<Transition> getPath() {
        return path;
    }

    public void setPath(List<Transition> path) {
        this.path = path;
    }
}
