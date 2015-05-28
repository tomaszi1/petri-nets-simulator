package org.petri.nets.synhronize;

import org.petri.nets.gui.panel.InitialMarkingPanel;
import org.petri.nets.gui.panel.PetriNetWrapperPanel;

/**
 * Created by Asia on 2015-05-28.
 */
public class SynchronizePanel {
    private PetriNetWrapperPanel petriNetWrapperPanel;
    private InitialMarkingPanel initialMarkingPanel;

    public SynchronizePanel(PetriNetWrapperPanel petriNetWrapperPanel){
        this.petriNetWrapperPanel = petriNetWrapperPanel;
        this.initialMarkingPanel = petriNetWrapperPanel.getInitialMarkingPanel();
    }


    public InitialMarkingPanel getInitialMarkingPanel() {
        return initialMarkingPanel;
    }

    public void setInitialMarkingPanel(InitialMarkingPanel initialMarkingPanel) {
        this.initialMarkingPanel = initialMarkingPanel;
    }
}
