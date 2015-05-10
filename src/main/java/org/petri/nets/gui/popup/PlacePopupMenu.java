package org.petri.nets.gui.popup;


import javax.swing.*;
import java.awt.event.ActionEvent;

public class PlacePopupMenu extends CustomJPopupMenu{

    public static final String CONNECT_ITEM_TEXT = "Połącz z...";
    private static final String RENAME_ITEM_TEXT = "Zmień nazwę";
    private static final String REMOVE_ITEM_TEXT = "Usuń";

    private final JMenuItem connectMenuItem;
    private final JMenuItem renameMenuItem;
    private final JMenuItem removeMenuItem;

    public PlacePopupMenu() {
        connectMenuItem = new JMenuItem(CONNECT_ITEM_TEXT);
        renameMenuItem = new JMenuItem(RENAME_ITEM_TEXT);
        removeMenuItem = new JMenuItem(REMOVE_ITEM_TEXT);

        add(connectMenuItem);
        add(renameMenuItem);
        add(removeMenuItem);

        connectMenuItem.addActionListener(this::connectMenuItemClicked);
        renameMenuItem.addActionListener(this::renameMenuItemClicked);
        removeMenuItem.addActionListener(this::removeMenuItemClicked);
    }

    private void removeMenuItemClicked(ActionEvent e) {
    }

    private void renameMenuItemClicked(ActionEvent e) {
    }

    private void connectMenuItemClicked(ActionEvent e) {
    }
}
