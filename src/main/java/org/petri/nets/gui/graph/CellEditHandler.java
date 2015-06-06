package org.petri.nets.gui.graph;

import com.sun.istack.internal.Nullable;
import org.petri.nets.gui.dialog.DialogCloseListener;
import org.petri.nets.gui.dialog.GlobalDialogsHandler;
import org.petri.nets.gui.panel.PlaceEditorPanel;
import org.petri.nets.gui.panel.TransitionEditorPanel;
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

    public CellEditHandler(GraphService graphService, GlobalDialogsHandler globalDialogsHandler) {
        this.graphService = graphService;
        this.globalDialogsHandler = globalDialogsHandler;

        this.transitionEditorPanel = new TransitionEditorPanel();
        this.placeEditorPanel = new PlaceEditorPanel();
    }

    public void editCell(Object cell) {
        if(graphService.isPlace(cell)){
            PlaceGraphCell placeGraphCell = graphService.tryCastToPlace(cell);
            Place place = graphService.getModelRepresentative(placeGraphCell);
            preparePlaceEditorPanel(place);
            DialogCloseListener dialogCloseListener = globalDialogsHandler.showDialog(place.getName(), placeEditorPanel);
        }else if(graphService.isTransition(cell)){
            TransitionGraphCell transitionGraphCell = graphService.tryCastToTransition(cell);
            Transition transition = graphService.getModelRepresentative(transitionGraphCell);
            prepareTransitionEditorPanel(transition);
            globalDialogsHandler.showDialog(transition.getName(), transitionEditorPanel);
        }
    }

    private void prepareTransitionEditorPanel(Transition transition) {
        transitionEditorPanel.setDescription(transition.getDescription());
        transitionEditorPanel.setPriority(transition.getPriority());
    }

    private void preparePlaceEditorPanel(Place place) {
        placeEditorPanel.setDescription(place.getDescription());
    }
}
