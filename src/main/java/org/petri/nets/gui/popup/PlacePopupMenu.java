package org.petri.nets.gui.popup;


import org.petri.nets.service.GraphService;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class PlacePopupMenu extends CustomJPopupMenu {

    public static final String CONNECT_ITEM_TEXT = "Połącz z...";
    private static final String RENAME_ITEM_TEXT = "Zmień nazwę";
    private static final String REMOVE_ITEM_TEXT = "Usuń";

    private final JMenuItem connectCellMenuItem;
    private final JMenuItem editCellMenuItem;
    private final JMenuItem removeCellMenuItem;

    private final GraphService graphService;

    public PlacePopupMenu(GraphService graphService) {
        this.graphService = graphService;
        connectCellMenuItem = new JMenuItem(CONNECT_ITEM_TEXT);
        editCellMenuItem = new JMenuItem(RENAME_ITEM_TEXT);
        removeCellMenuItem = new JMenuItem(REMOVE_ITEM_TEXT);

        add(connectCellMenuItem);
        add(editCellMenuItem);
        add(removeCellMenuItem);

        connectCellMenuItem.addActionListener(this::connectCellMenuItemClicked);
        editCellMenuItem.addActionListener(this::editCellMenuItemClicked);
        removeCellMenuItem.addActionListener(this::removeCellMenuItemClicked);
    }

    private void removeCellMenuItemClicked(ActionEvent e) {
        graphService.removeFromGraph(graphService.getLastFocusedCell());
    }

    private void editCellMenuItemClicked(ActionEvent e) {
        // TODO small frame over window?
    }

    private void connectCellMenuItemClicked(ActionEvent e) {
        // not necessary for now
    }
}
