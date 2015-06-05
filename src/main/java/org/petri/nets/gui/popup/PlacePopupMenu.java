package org.petri.nets.gui.popup;


import org.petri.nets.service.GraphService;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class PlacePopupMenu extends CustomJPopupMenu {


    private static final String RENAME_ITEM_TEXT = "Zmień nazwę";
    private static final String REMOVE_ITEM_TEXT = "Usuń";

    private final JMenuItem editCellMenuItem;
    private final JMenuItem removeCellMenuItem;

    private final GraphService graphService;

    public PlacePopupMenu(GraphService graphService) {
        this.graphService = graphService;
        editCellMenuItem = new JMenuItem(RENAME_ITEM_TEXT);
        removeCellMenuItem = new JMenuItem(REMOVE_ITEM_TEXT);

        add(editCellMenuItem);
        add(removeCellMenuItem);

        editCellMenuItem.addActionListener(this::editCellMenuItemClicked);
        removeCellMenuItem.addActionListener(this::removeCellMenuItemClicked);
    }

    private void removeCellMenuItemClicked(ActionEvent e) {
        graphService.removeFromGraph(graphService.getSelectedCells());
    }

    private void editCellMenuItemClicked(ActionEvent e) {
        // TODO small frame over window?
    }

}
