package org.petri.nets.service;

import org.jgraph.graph.CellView;
import org.jgraph.graph.GraphCell;
import org.petri.nets.gui.graph.PlaceGraphCell;
import org.petri.nets.gui.graph.TransitionGraphCell;
import org.petri.nets.model.DomainModel;

import java.awt.*;
import java.util.Random;

public class GraphServiceImpl implements GraphService {
    private final DomainModel model;

    public GraphServiceImpl(DomainModel model) {
        this.model = model;
    }

    @Override
    public void removeFromGraph(Object cell) {
        if (cell instanceof CellView) {
            CellView cellView = (CellView) cell;
            model.getPetriNetGraph().getGraphLayoutCache().remove(new Object[]{cellView.getCell()});
        } else
            model.getPetriNetGraph().getGraphLayoutCache().remove(new Object[]{cell});
    }

    @Override
    public PlaceGraphCell addPlace(Point position) {
        PlaceGraphCell cell = new PlaceGraphCell(
                new Random().nextInt(), // FIXME
                position);
        model.getPetriNetGraph().getGraphLayoutCache().insert(cell);
        return cell;
    }

    @Override
    public TransitionGraphCell addTransition(Point position) {
        TransitionGraphCell cell = new TransitionGraphCell(
                new Random().nextInt(), // FIXME
                position);
        model.getPetriNetGraph().getGraphLayoutCache().insert(cell);
        return cell;
    }

    @Override
    public void addArc(Object place, Object transition) {
        throw new UnsupportedOperationException("implement");
    }

    public DomainModel getModel() {
        return model;
    }
}
