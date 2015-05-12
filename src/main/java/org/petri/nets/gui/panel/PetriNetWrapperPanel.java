package org.petri.nets.gui.panel;

import org.petri.nets.model.DomainModel;

import javax.swing.*;
import java.awt.*;

public class PetriNetWrapperPanel extends JPanel{

    private final PetriNetGraphPanel petriNetGraphPanel;
    private final InitialMarkingPanel initialMarkingPanel;

    public PetriNetWrapperPanel(DomainModel domainModel) {
        setLayout(new BorderLayout());
        
        petriNetGraphPanel = new PetriNetGraphPanel(domainModel);
        initialMarkingPanel = new InitialMarkingPanel(domainModel);

        add(petriNetGraphPanel, BorderLayout.CENTER);
        add(initialMarkingPanel, BorderLayout.PAGE_END);
    }
}
