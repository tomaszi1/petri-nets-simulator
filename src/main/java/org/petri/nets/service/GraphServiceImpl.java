package org.petri.nets.service;

import org.jgraph.graph.*;
import org.petri.nets.gui.graph.*;
import org.petri.nets.model.DomainModel;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class GraphServiceImpl implements GraphService {
    private final DomainModel model;

    public GraphServiceImpl(DomainModel model) {
        this.model = model;
    }

    public void cascadeRemoveEdges(PetriNetGraphCell cell) {
        for (Object o : cell.getChildren()) {
            Port port = CustomCellViewFactory.tryCastToPort(o);
            if (port!=null) {
                Iterator edges = port.edges();
                while(edges.hasNext()){
                    Edge edge = (Edge) edges.next();
                    removeFromGraph(edge);
                }
            }
        }
    }

    @Override
    public void removeFromGraph(Object cell) {
        if (cell instanceof Object[])
            removeFromGraph((Object[])cell);
        else if (cell instanceof CellView) {
            CellView cellView = (CellView) cell;
            removeFromGraph(new Object[]{cellView.getCell()});
        } else {
            removeFromGraph(new Object[]{cell});
        }
    }

    public void removeFromGraph(Object[] cells){
        for (Object cell : cells) {
            DefaultGraphCell graphCell = CustomCellViewFactory.tryCastToCell(cell);
            if(graphCell!=null){
                for (Object child : graphCell.getChildren()) {
                    Port port = CustomCellViewFactory.tryCastToPort(child);
                    if(port!=null){
                        port.edges().forEachRemaining(this::removeFromGraph);
                    }
                }
            }
            model.getPetriNetGraph().getGraphLayoutCache().remove(new Object[]{cell});
        }
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
    public void addArc(PetriNetGraphCell start, PetriNetGraphCell end) {
        ArcGraphCell edge = new ArcGraphCell();
        edge.setSource(start.getFirstChild());
        edge.setTarget(end.getFirstChild());
        model.getPetriNetGraph().getGraphLayoutCache().insert(edge);
    }

    public DomainModel getModel() {
        return model;
    }

    @Override
    public CellView getLastFocusedCell() {
        PetriNetGraphUI ui = (PetriNetGraphUI) model.getPetriNetGraph().getUI();
        return ui.getLastFocus();
    }


    @Override
    public Object[] getSelectedCells() {
        return model.getPetriNetGraph().getSelectionCells();
    }

    @Override
    public boolean isTransition(Object cell) {
        return CustomCellViewFactory.isTransition(cell);
    }

    @Override
    public boolean isPlace(Object cell) {
        return CustomCellViewFactory.isPlace(cell);
    }

    @Override
    public boolean isVertex(Object cell) {
        return CustomCellViewFactory.isVertex(cell);
    }

    @Override
    public PetriNetGraphCell[] getSelectedVertices() {
        List<PetriNetGraphCell> collect = Arrays.stream(getSelectedCells())
                .filter(this::isVertex)
                .map(c -> (PetriNetGraphCell) c)
                .collect(Collectors.toList());
        return collect.toArray(new PetriNetGraphCell[collect.size()]);
    }


}
