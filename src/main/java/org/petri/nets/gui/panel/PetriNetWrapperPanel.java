package org.petri.nets.gui.panel;

import org.petri.nets.model.DomainModel;
import org.petri.nets.synhronize.SynchronizePanel;

import javax.swing.*;
import java.awt.*;

public class PetriNetWrapperPanel extends JPanel{

    private final PetriNetGraphPanel petriNetGraphPanel;
    private final InitialMarkingPanel initialMarkingPanel;
    private SynchronizePanel synchronizePanel;

    public PetriNetWrapperPanel(DomainModel domainModel) {
        setLayout(new BorderLayout());

        initialMarkingPanel = new InitialMarkingPanel(domainModel);
        synchronizePanel = new SynchronizePanel(this);
        petriNetGraphPanel = new PetriNetGraphPanel(domainModel, synchronizePanel);

        add(petriNetGraphPanel, BorderLayout.CENTER);
        add(getInitialMarkingPanel(), BorderLayout.PAGE_END);
    }

    public InitialMarkingPanel getInitialMarkingPanel() {
        return initialMarkingPanel;
    }
}
