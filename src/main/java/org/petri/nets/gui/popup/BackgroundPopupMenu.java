package org.petri.nets.gui.popup;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class BackgroundPopupMenu extends JPopupMenu{

    public static final String ADD_PLACE_TEXT = "Dodaj miejsce";
    public static final String ADD_TRANSITION_TEXT = "Dodaj przejście";

    private final JMenuItem addPlaceMenuItem;
    private final JMenuItem addTransitionMenuItem;

    public BackgroundPopupMenu() {
        addPlaceMenuItem = new JMenuItem(ADD_PLACE_TEXT);
        addTransitionMenuItem = new JMenuItem(ADD_TRANSITION_TEXT);

        add(addPlaceMenuItem);
        add(addTransitionMenuItem);

        addPlaceMenuItem.addActionListener(this::addPlaceItemClicked);
        addTransitionMenuItem.addActionListener(this::addTransitionItemClicked);
    }

    private void addTransitionItemClicked(ActionEvent e) {
    }

    private void addPlaceItemClicked(ActionEvent e) {
    }

}
