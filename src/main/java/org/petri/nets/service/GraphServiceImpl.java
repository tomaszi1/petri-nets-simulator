package org.petri.nets.service;

import com.google.common.collect.BiMap;
import edu.uci.ics.jung.graph.Graph;
import org.jgraph.JGraph;
import org.jgraph.graph.*;
import org.petri.nets.gui.graph.petriNet.*;
import org.petri.nets.model.*;
import org.petri.nets.model.reachability.State;
import org.petri.nets.model.reachability.TransitionEdge;
import org.petri.nets.synhronize.SynchronizePanel;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class GraphServiceImpl implements GraphService {
    private final DomainModel model;
    private SaveGraphAsFile saveGraphAsFile;
    private BiMap<PlaceGraphCell, Place> placeGUI;
    private BiMap<TransitionGraphCell, Transition> transitonGUI;
    private SynchronizePanel synchronizePanel;

    public GraphServiceImpl(DomainModel model) {
        this.model = model;
        this.synchronizePanel = new SynchronizePanel();
        saveGraphAsFile = new SaveGraphAsFile(model);
        this.placeGUI = this.model.getSyncModel().getPlaceGUI();
        this.transitonGUI = this.model.getSyncModel().getTransitonGUI();
    }

    @Override
    public SynchronizePanel getSynchronizePanel() {
        return synchronizePanel;
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
        refreshMatrix();
        invalidateReachabilityGraph();
    }

    private void synhronizeRemoveFromGraph(Object cell) {
        if (isPlace(cell)) {
            model.getPetriNet().removePlace(placeGUI.get(cell));
            placeGUI.remove(cell);
        } else if (isTransition(cell)) {
            model.getPetriNet().removeTransition(transitonGUI.get(cell));
            transitonGUI.remove(cell);
        } else {
            removeArc((ArcGraphCell) cell);
        }
        synchronizePanel.updateMarking();
        refreshMatrix();
    }

    @Override
    public PlaceGraphCell addPlace(Point position) {
        Place place = model.getPetriNet().addPlace();
        PlaceGraphCell cell = new PlaceGraphCell(
                place.getId(),
                position);
        model.getPetriNetGraph().getGraphLayoutCache().insert(cell);
        placeGUI.put(cell, place);
        synchronizePanel.updateMarking();
        refreshMatrix();
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
//        syncService.addTransition(transition.getId()); // !!!!
        refreshMatrix();
        invalidateReachabilityGraph();
        return cell;
    }

    @Override
    public void addArc(PetriNetGraphCell start, PetriNetGraphCell end) {
        Place place = placeGUI.get(start);
        Transition transition = transitonGUI.get(end);
        if (place != null && transition != null && model.getPetriNet().hasArc(place, transition))
            return;

        ArcGraphCell edge = new ArcGraphCell();
        edge.setSource(start.getFirstChild());
        edge.setTarget(end.getFirstChild());
        edge.setStart(start);
        edge.setEnd(end);
        model.getPetriNetGraph().getGraphLayoutCache().insert(edge);

        if (isPlace(start))
            model.getPetriNet().addArc(placeGUI.get(start), transitonGUI.get(end), 1, true);
        else
            model.getPetriNet().addArc(placeGUI.get(end), transitonGUI.get(start), 1, false);

        refreshMatrix();
        invalidateReachabilityGraph();
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
    public PlaceGraphCell tryCastToPlace(Object cell) {
        return CustomCellViewFactory.tryCastToPlace(cell);
    }

    @Override
    public TransitionGraphCell tryCastToTransition(Object cell) {
        return CustomCellViewFactory.tryCastToTransition(cell);
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
    public void saveGraphAsFile(File file) throws IOException {
        saveGraphAsFile.saveGaph(file);
    }

    @Override
    public void openGraphFromFile(File file) throws Exception {
        saveGraphAsFile.openGraph(file);
    }
    @Override
    public void exportMatrixToFile(File file){
        saveGraphAsFile.exportMatrixToFile(file);
    }

    private void removeArc(ArcGraphCell arc) {
        if (isPlace(arc.getStart())) {
            model.getPetriNet().removeArc(placeGUI.get(arc.getStart()), transitonGUI.get(arc.getEnd()), true);
        } else {
            model.getPetriNet().removeArc(placeGUI.get(arc.getEnd()), transitonGUI.get(arc.getStart()), false);
        }
        synchronizePanel.updateNetMatrix();
    }

    @Override
    public void setInitialMarking(int placeId, int marking) {
        model.getPetriNet().setInitialMarking(placeId, marking);
        invalidateReachabilityGraph();
        synchronizePanel.updateNetMatrix();
    }

    @Override
    public HashMap<Integer, Integer> getInitialMarking() {
        return model.getPetriNet().getInitialMarking();
    }

    @Override
    public int getInitialMarking(int placeId) {
        return model.getPetriNet().getInitialMarking(placeId);
    }

    @Override
    public void invalidateReachabilityGraph() {
        ReachabilityGraphGenerator reachGraph = new ReachabilityGraphGenerator(model.getPetriNet(), 50);
        Graph<State, TransitionEdge> reachabilityGraph = reachGraph.generateGraph();
        model.setReachabilityGraph(reachabilityGraph);
        synchronizePanel.updateReachabilityGraph();
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
    public Graph<State, TransitionEdge> getReachabilityGraph() {
        return model.getReachabilityGraph();
    }

    @Override
    public Place getModelRepresentative(PlaceGraphCell placeGraphCell) {
        return placeGUI.get(placeGraphCell);
    }

    @Override
    public Transition getModelRepresentative(TransitionGraphCell transitionGraphCell) {
        return transitonGUI.get(transitionGraphCell);
    }

    @Override
    public boolean isArc(Object cell) {
        return CustomCellViewFactory.isArc(cell);
    }

    @Override
    public ArcGraphCell tryCastToArc(Object cell) {
        return CustomCellViewFactory.tryCastToArc(cell);
    }

    @Override
    public Arc getModelRepresentative(ArcGraphCell arcGraphCell) {
        PetriNetGraphCell start = arcGraphCell.getStart();
        PetriNetGraphCell end = arcGraphCell.getEnd();
        if (isPlace(start)) {
            PlaceGraphCell placeGraphCell = tryCastToPlace(start);
            TransitionGraphCell transitionGraphCell = tryCastToTransition(end);
            Place place = getModelRepresentative(placeGraphCell);
            Transition transition = getModelRepresentative(transitionGraphCell);
            return getArc(place, transition, true);
        }

        TransitionGraphCell transitionGraphCell = tryCastToTransition(start);
        PlaceGraphCell placeGraphCell = tryCastToPlace(end);
        Place place = getModelRepresentative(placeGraphCell);
        Transition transition = getModelRepresentative(transitionGraphCell);
        return getArc(place, transition, false);
    }

    @Override
    public void refreshGraph() {
        model.getPetriNetGraph().invalidate();
        model.getPetriNetGraph().refresh();
    }
    @Override
    public void refreshMatrix(){
        synchronizePanel.updateNetMatrix();
    }

    @Override
    public void displayMarkingOnGraph(Map<Integer, Integer> marking) {
        if(marking==null){
            for (Map.Entry<PlaceGraphCell, Place> entry : placeGUI.entrySet())
                entry.getKey().setUserObject(entry.getValue().getName());
            refreshGraph();
            return;
        }
        marking.forEach((placeId, token) -> {
            Place place = model.getPetriNet().getPlace(placeId);
            if (place == null)
                return;
            PlaceGraphCell placeGraphCell = placeGUI.inverse().get(place);
            placeGraphCell.setUserObject(place.getName() + "|" + token);
        });
        refreshGraph();
    }

    @Override
    public String getDescriptionOfCell(Object cell) {
        Place place = placeGUI.get(cell);
        if(place!=null)
            return place.getDescription();
        Transition transition = transitonGUI.get(cell);
        if(transition!=null)
            return transition.getDescription();
        return null;
    }

    public Arc getArc(Place place, Transition transition, boolean startsInPlace) {
        if (startsInPlace) {
            return place.getTransitionsTo().get(transition);
        } else {
            return place.getTransitionsFrom().get(transition);
        }
    }
}
