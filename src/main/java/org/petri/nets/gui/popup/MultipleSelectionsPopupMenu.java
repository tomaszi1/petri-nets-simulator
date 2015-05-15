package org.petri.nets.gui.popup;

import org.jgraph.graph.DefaultEdge;
import org.petri.nets.gui.graph.PetriNetGraphCell;
import org.petri.nets.service.GraphService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MultipleSelectionsPopupMenu extends CustomJPopupMenu {
    private GraphService graphService;

    public static final String CONNECT_CELLS_MENU_ITEM_TEXT = "Połącz miejsce z przejściem";
    public static final String DELETE_CELLS_MENU_ITEM_TEXT = "Usuń zaznaczone";

    private final JMenuItem connectCellsMenuItem;
    private final JMenuItem deleteCellsMenuItem;

    public MultipleSelectionsPopupMenu(GraphService graphService) {
        this.graphService = graphService;

        connectCellsMenuItem = new JMenuItem(CONNECT_CELLS_MENU_ITEM_TEXT);
        deleteCellsMenuItem = new JMenuItem(DELETE_CELLS_MENU_ITEM_TEXT);

        connectCellsMenuItem.addActionListener(this::connectCellsMenuItemClicked);
        deleteCellsMenuItem.addActionListener(this::deleteCellsMenuItemClicked);

        add(connectCellsMenuItem);
        add(deleteCellsMenuItem);
    }

    private void deleteCellsMenuItemClicked(ActionEvent event) {
        Object[] selectedCells = graphService.getSelectedCells();
        graphService.removeFromGraph(selectedCells);

    }

    @Override
    public void show(Component invoker, int x, int y) {
        connectCellsMenuItem.setEnabled(placeTransitionPairSelected());
        super.show(invoker, x, y);
    }

    private void connectCellsMenuItemClicked(ActionEvent e) {
        if (!placeTransitionPairSelected())
            return;

        PetriNetGraphCell[] selectedVertices = graphService.getSelectedVertices();


        graphService.addArc(selectedVertices[0], selectedVertices[1]);
    }

    private boolean placeTransitionPairSelected() {
        Object[] selectedCells = graphService.getSelectedCells();

        return selectedCells.length == 2
                && ((graphService.isPlace(selectedCells[0])
                && graphService.isTransition(selectedCells[1]))
                || (graphService.isTransition(selectedCells[0])
                && graphService.isPlace(selectedCells[1])));
    }


}
