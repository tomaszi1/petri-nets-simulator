package org.petri.nets.gui.popup;

import org.petri.nets.gui.graph.PlaceGraphCell;
import org.petri.nets.gui.graph.TransitionGraphCell;
import org.petri.nets.model.DomainModel;
import org.petri.nets.service.GraphService;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

public class BackgroundPopupMenu extends CustomJPopupMenu {

    private final GraphService graphService;

    public static final String ADD_PLACE_TEXT = "Dodaj miejsce";
    public static final String ADD_TRANSITION_TEXT = "Dodaj przejście";

    private final JMenuItem addPlaceMenuItem;
    private final JMenuItem addTransitionMenuItem;

    public BackgroundPopupMenu(GraphService graphService) {
        this.graphService = graphService;
        addPlaceMenuItem = new JMenuItem(ADD_PLACE_TEXT);
        addTransitionMenuItem = new JMenuItem(ADD_TRANSITION_TEXT);

        addPlaceMenuItem.addActionListener(this::addPlaceItemClicked);
        addTransitionMenuItem.addActionListener(this::addTransitionItemClicked);

        add(addPlaceMenuItem);
        add(addTransitionMenuItem);
    }

    private void addTransitionItemClicked(ActionEvent e) {

        graphService.addTransition(getPosition());
    }

    private void addPlaceItemClicked(ActionEvent e) {
        graphService.addPlace(getPosition());
    }

}
