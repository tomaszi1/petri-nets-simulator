package org.petri.nets.gui.graph.reachability;

import com.google.common.collect.Maps;
import org.petri.nets.model.reachability.State;

import java.awt.geom.Point2D;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Tomasz on 2015-06-06.
 */
public class CustomInitializer implements org.apache.commons.collections15.Transformer<State, Point2D> {
    private static final int letterWidth = 10;
    private static final int distanceY = 200;
    private final Collection<State> vertices;
    private final int distanceX;
    private Map<Integer, Integer> verticesCountAtDepth = Maps.newHashMap();

    public CustomInitializer(Collection<State> vertices) {
        this.vertices = vertices;
        Iterator<State> it = vertices.iterator();
        distanceX = it.hasNext() ? letterWidth * it.next().getMarking().values().toString().length() : 0;
    }

    @Override
    public Point2D transform(State state) {
        int depth = state.getDepth();
        verticesCountAtDepth.putIfAbsent(depth, 0);

        int posX = distanceX * verticesCountAtDepth.get(depth) + 100;
        int posY = distanceY * depth + 100;

        verticesCountAtDepth.put(depth, verticesCountAtDepth.get(depth) + 1);
        return new Point2D.Double(posX, posY);
    }
}
