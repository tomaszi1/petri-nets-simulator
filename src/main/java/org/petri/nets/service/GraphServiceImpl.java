package org.petri.nets.service;

import com.google.common.collect.BiMap;
import edu.uci.ics.jung.graph.Graph;
import org.jgraph.JGraph;
import org.jgraph.graph.*;
import org.petri.nets.gui.graph.*;
import org.petri.nets.model.DomainModel;
import org.petri.nets.model.PetriNet;
import org.petri.nets.model.Place;
import org.petri.nets.model.Transition;
import org.petri.nets.synhronize.SynchronizePanel;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class GraphServiceImpl implements GraphService {
    private final DomainModel model;
    private SynchronizeService syncService;
    private SaveGraphAsFile saveGraphAsFile;
    private ReachGraph reachGraph;
    private BiMap<PlaceGraphCell, Place> placeGUI;
    private BiMap<TransitionGraphCell, Transition> transitonGUI;

    public GraphServiceImpl(DomainModel model, SynchronizePanel synchronizePanel) {
        this.model = model;
        this.syncService = new SynchronizeServiceImpl(model, synchronizePanel);
        saveGraphAsFile = new SaveGraphAsFile(model);
        this.placeGUI = this.model.getSyncModel().getPlaceGUI();
        this.transitonGUI = this.model.getSyncModel().getTransitonGUI();
    }

    @Override
    public void removeFromGraph(Object cell) {
        if (cell instanceof Object[])
            removeFromGraph((Object[]) cell);
        else if (cell instanceof CellView) {
            CellView cellView = (CellView) cell;
            removeFromGraph(new Object[]{cellView.getCell()});
        } else {
            removeFromGraph(new Object[]{cell});
        }
    }

    public void removeFromGraph(Object[] cells) {
        for (Object cell : cells) {
            DefaultGraphCell graphCell = CustomCellViewFactory.tryCastToCell(cell);
            if (graphCell != null) {
                for (Object child : graphCell.getChildren()) {
                    Port port = CustomCellViewFactory.tryCastToPort(child);
                    if (port != null) {
                        port.edges().forEachRemaining(this::removeFromGraph);
                    }
                }
            }
            model.getPetriNetGraph().getGraphLayoutCache().remove(new Object[]{cell});
            synhronizeRemoveFromGraph(cell);
        }
        invalidateReachabilityGraph();
    }

    private void synhronizeRemoveFromGraph(Object cell) {
        if (isPlace(cell)) {
            syncService.removePlace(placeGUI.get(cell));
            placeGUI.remove(cell);
        } else if (isTransition(cell)) {
            syncService.removeTransition(transitonGUI.get(cell));
            transitonGUI.remove(cell);
        } else {
            removeArc((ArcGraphCell) cell);
        }
    }

    @Override
    public PlaceGraphCell addPlace(Point position) {
        Place place = model.getPetriNet().addPlace();
        PlaceGraphCell cell = new PlaceGraphCell(
                place.getIdPlace(),
                position);
        model.getPetriNetGraph().getGraphLayoutCache().insert(cell);
        syncService.addPlace();
        placeGUI.put(cell, place);
        invalidateReachabilityGraph();
        return cell;
    }

    @Override
    public TransitionGraphCell addTransition(Point position) {
        Transition transition = model.getPetriNet().addTransition();
        TransitionGraphCell cell = new TransitionGraphCell(
                transition.getId(),
                position);
        model.getPetriNetGraph().getGraphLayoutCache().insert(cell);
        transitonGUI.put(cell, transition);
        invalidateReachabilityGraph();
        return cell;
    }

    @Override
    public void addArc(PetriNetGraphCell start, PetriNetGraphCell end) {
        ArcGraphCell edge = new ArcGraphCell();
        edge.setSource(start.getFirstChild());
        edge.setTarget(end.getFirstChild());
        edge.setStart(start);
        edge.setEnd(end);
        edge.setPriority(0);
        edge.setValue(0);
        model.getPetriNetGraph().getGraphLayoutCache().insert(edge);

        if (isPlace(start.getUserObject().toString()))
            model.getPetriNet().addArc(placeGUI.get(start), transitonGUI.get(end), 1, true);
        else
            model.getPetriNet().addArc(placeGUI.get(end), transitonGUI.get(start), 1, false);

        invalidateReachabilityGraph();
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

    @Override
    public void saveGraphAsFile() {
        saveGraphAsFile.saveGaph();
    }

    private int getId(String stringId) {
        int id = Integer.valueOf(stringId.substring(1));
        return id - 1;
    }

    private boolean isPlace(String stringId) {
        if (stringId.substring(0, 1).equals("P")) {
            return true;
        }
        return false;
    }

    private void removeArc(ArcGraphCell arc) {
        if (isPlace(arc.getStart())) {
            syncService.removeArc(transitonGUI.get(arc.getEnd()), placeGUI.get(arc.getStart()), true);
        } else {
            syncService.removeArc(transitonGUI.get(arc.getStart()), placeGUI.get(arc.getEnd()), false);
        }
    }

    @Override
    public void setInitialMarking(int placeId, int marking) {
        model.getPetriNet().setInitialMarking(placeId, marking);
        invalidateReachabilityGraph();
    }

    @Override
    public HashMap<Integer, Integer> getInitialMarking() {
        return model.getPetriNet().getInitialMarking();
    }

    @Override
    public int getInitialMarking(int placeId) {
        return model.getPetriNet().getInitialMarking(placeId);
    }

    private void invalidateReachabilityGraph() {
        reachGraph = new ReachGraph(model.getPetriNet(), 50);
        reachGraph.RunReachGraph();
        Graph<HashMap<Integer, Integer>, Transition> reachGraph = this.reachGraph.getReachGraph();
        model.setReachabilityGraph(reachGraph);
        syncService.updateReachabilityGraph();
    }

    @Override
    public JGraph getPetriNetGraph() {
        return model.getPetriNetGraph();
    }

    @Override
    public DomainModel getDomainModel() {
        return model;
    }

    @Override
    public Graph<HashMap<Integer, Integer>, Transition> getReachabilityGraph() {
        return model.getReachabilityGraph();
    }

    @Override
    public SynchronizeService getSynchronizeService() {
        return syncService;
    }
}
