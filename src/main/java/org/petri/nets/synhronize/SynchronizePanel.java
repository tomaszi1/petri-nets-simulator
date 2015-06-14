package org.petri.nets.synhronize;

import org.petri.nets.gui.panel.*;
import org.petri.nets.model.PetriNetProperties;

/**
 * Created by Asia on 2015-05-28.
 */
public class SynchronizePanel {
    private PetriNetWrapperPanel petriNetWrapperPanel;
    private ReachabilityGraphPanel reachabilityGraphPanel;
    private NetMatrixPanel netMatrixPanel;
    private PetriNetPropertiesPanel petriNetPropertiesPanel;
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

    public void updateMarking() {
        getInitialMarkingPanel().updateMarking();
    }

    public void updateNetMatrix(){
        netMatrixPanel.updateTable();
    }

    public void setNetMatrixPanel(NetMatrixPanel netMatrixPanel) {
        this.netMatrixPanel = netMatrixPanel;
    }

    public void updatePetriNetProperties(PetriNetProperties properties) {
        petriNetPropertiesPanel.updateProperties(properties);
    }

    public PetriNetPropertiesPanel getPetriNetPropertiesPanel() {
        return petriNetPropertiesPanel;
    }

    public void setPetriNetPropertiesPanel(PetriNetPropertiesPanel petriNetPropertiesPanel) {
        this.petriNetPropertiesPanel = petriNetPropertiesPanel;
    }
}
