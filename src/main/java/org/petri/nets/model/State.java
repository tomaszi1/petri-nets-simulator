package org.petri.nets.model;

import com.google.common.collect.Maps;

import java.util.Map;

public class State {
    private Map<Integer, Integer> stateMap;

    public State(Map<Integer, Integer> stateMap) {
        this.stateMap = stateMap;
    }

    public State() {
        stateMap = Maps.newHashMap();
    }

    public Map<Integer, Integer> getStateMap() {
        return stateMap;
    }

    public void setStateMap(Map<Integer, Integer> stateMap) {
        this.stateMap = stateMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state1 = (State) o;
        return !(stateMap != null ? !stateMap.equals(state1.stateMap) : state1.stateMap != null);
    }
}
