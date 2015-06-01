package org.petri.nets.synhronize;

import org.petri.nets.gui.panel.InitialMarkingPanel;
import org.petri.nets.gui.panel.PetriNetWrapperPanel;
import org.petri.nets.gui.panel.ReachabilityGraphPanel;

/**
 * Created by Asia on 2015-05-28.
 */
public class SynchronizePanel {
    private PetriNetWrapperPanel petriNetWrapperPanel;
    private ReachabilityGraphPanel reachabilityGraphPanel;

    public InitialMarkingPanel getInitialMarkingPanel() {
        return petriNetWrapperPanel.getInitialMarkingPanel();
    }

    public void updateReachabilityGraph() {
        reachabilityGraphPanel.updateGraph();
    }

    public void setReachabilityGraphPanel(ReachabilityGraphPanel reachabilityGraphPanel) {
        this.reachabilityGraphPanel = reachabilityGraphPanel;
    }

    public void setPetriNetWrapperPanel(PetriNetWrapperPanel petriNetWrapperPanel) {
        this.petriNetWrapperPanel = petriNetWrapperPanel;
    }
}
