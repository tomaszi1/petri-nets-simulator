package org.petri.nets.gui.graph.reachability;

import com.google.common.collect.Maps;
import org.petri.nets.model.reachability.State;

import java.awt.geom.Point2D;
import java.util.Map;

/**
 * Created by Tomasz on 2015-06-06.
 */
public class CustomInitializer implements org.apache.commons.collections15.Transformer<State, Point2D> {
    private Map<Integer, Integer> verticesCountAtDepth = Maps.newHashMap();
    private final int distance = 200;

    public CustomInitializer() {
    }

    @Override
    public Point2D transform(State state) {
        int depth = state.getDepth();
        verticesCountAtDepth.putIfAbsent(depth, 0);

        int posX = 100 * verticesCountAtDepth.get(depth) + 100;
        int posY = distance * depth + 100;

        verticesCountAtDepth.put(depth, verticesCountAtDepth.get(depth) + 1);
        return new Point2D.Double(posX, posY);
    }
}
