package org.petri.nets.gui.graph;

import org.jgraph.graph.*;

public class CustomCellViewFactory extends DefaultCellViewFactory {
    @Override
    protected VertexView createVertexView(Object cell) {
        if (cell instanceof PlaceGraphCell)
            return new PlaceVertexView(cell);
        else return new TransitionVertexView(cell);
    }

    public static boolean isPlaceView(CellView cellView) {
        return cellView instanceof PlaceVertexView;
    }

    public static boolean isTransitionView(CellView cellView) {
        return cellView instanceof TransitionVertexView;
    }

    public static boolean isTransition(Object cell) {
        return cell instanceof TransitionGraphCell;
    }

    public static boolean isPlace(Object cell) {
        return cell instanceof PlaceGraphCell;
    }

    public static boolean isVertex(Object cell) {
        return cell instanceof PetriNetGraphCell;
    }

    public static boolean isPort(Object obj) {
        return obj instanceof Port;
    }

    public static Port tryCastToPort(Object o) {
        if (!isPort(o))
            return null;
        return (Port) o;
    }

    public static DefaultGraphCell tryCastToCell(Object obj) {
        if(!isCell(obj))
            return null;
        return (DefaultGraphCell) obj;
    }

    private static boolean isCell(Object obj) {
        return obj instanceof GraphCell;
    }
}
