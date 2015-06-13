package org.petri.nets.gui.graph.petriNet;

import org.petri.nets.gui.dialog.DialogCloseListener;
import org.petri.nets.gui.dialog.GlobalDialogsHandler;
import org.petri.nets.gui.panel.OkCancelPanel;
import org.petri.nets.gui.panel.PropertyEditorAbstractPanel;
import org.petri.nets.gui.panel.editorPanels.ArcEditorPanel;
import org.petri.nets.gui.panel.editorPanels.PlaceEditorPanel;
import org.petri.nets.gui.panel.editorPanels.TransitionEditorPanel;
import org.petri.nets.model.Arc;
import org.petri.nets.model.Place;
import org.petri.nets.model.Transition;
import org.petri.nets.service.GraphService;

/**
 * Created by Tomasz on 2015-06-06.
 */
class CellEditHandler {
    private GraphService graphService;
    private GlobalDialogsHandler globalDialogsHandler;

    private TransitionEditorPanel transitionEditorPanel;
    private PlaceEditorPanel placeEditorPanel;
    private ArcEditorPanel arcEditorPanel;
    private ArcGraphCell arcGraphCell;
    private PetriNetGraphCell petriNetGraphCell;

    public CellEditHandler(GraphService graphService, GlobalDialogsHandler globalDialogsHandler) {
        this.graphService = graphService;
        this.globalDialogsHandler = globalDialogsHandler;

        this.transitionEditorPanel = new TransitionEditorPanel();
        this.placeEditorPanel = new PlaceEditorPanel();
        this.arcEditorPanel = new ArcEditorPanel();
    }

    public void editCell(Object cell) {
        PropertyEditorAbstractPanel panel = null;

        if (graphService.isPlace(cell)) {
            PlaceGraphCell placeGraphCell = graphService.tryCastToPlace(cell);
            petriNetGraphCell = placeGraphCell;
            Place place = graphService.getModelRepresentative(placeGraphCell);
            preparePlaceEditorPanel(place);
            panel = placeEditorPanel;
        } else if (graphService.isTransition(cell)) {
            TransitionGraphCell transitionGraphCell = graphService.tryCastToTransition(cell);
            petriNetGraphCell = transitionGraphCell;
            Transition transition = graphService.getModelRepresentative(transitionGraphCell);
            prepareTransitionEditorPanel(transition);
            panel = transitionEditorPanel;
        } else if (graphService.isArc(cell)) {
            arcGraphCell = graphService.tryCastToArc(cell);
            Arc arc = graphService.getModelRepresentative(arcGraphCell);
            prepareArcEditorPanel(arc);
            panel = arcEditorPanel;
        }
        if (panel != null) {
            DialogCloseListener dialogCloseListener = globalDialogsHandler.setDialog(panel.getName(), new OkCancelPanel(panel));
            panel.setDialogCloseListener(dialogCloseListener);
            globalDialogsHandler.showDialog();
        }
    }

    private void prepareArcEditorPanel(Arc arc) {
        arcEditorPanel.setValue(arc.getValue());
        arcEditorPanel.setListener(value -> {
            if (arc.getValue() != value) {
                arc.setValue(value);
                arcGraphCell.setUserObject(value);
                graphService.refreshGraph();
                graphService.invalidateReachabilityGraph();
                graphService.refreshMatrix();
            }
        });
    }

    private void prepareTransitionEditorPanel(Transition transition) {
        transitionEditorPanel.setDescription(transition.getDescription());
        transitionEditorPanel.setPriority(transition.getPriority());
        transitionEditorPanel.setListener(props -> {
            transition.setPriority(props.priority);
            transition.setDescription(props.description);
            petriNetGraphCell.setDescription(props.description);
            graphService.invalidateReachabilityGraph();
        });
    }

    private void preparePlaceEditorPanel(Place place) {
        placeEditorPanel.setDescription(place.getDescription());
        placeEditorPanel.setListener(description -> {
            place.setDescription(description);
            petriNetGraphCell.setDescription(description);
        });
    }
}
