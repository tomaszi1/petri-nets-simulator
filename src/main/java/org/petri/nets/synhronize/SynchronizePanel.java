package org.petri.nets.synhronize;

import org.petri.nets.gui.MainFrame;
import org.petri.nets.gui.panel.InitialMarkingPanel;
import org.petri.nets.gui.panel.NetMatrixPanel;
import org.petri.nets.gui.panel.PetriNetWrapperPanel;
import org.petri.nets.gui.panel.ReachabilityGraphPanel;
import org.petri.nets.gui.panel.matrixPanels.NetMatrixModel;

/**
 * Created by Asia on 2015-05-28.
 */
public class SynchronizePanel {
    private PetriNetWrapperPanel petriNetWrapperPanel;
    private ReachabilityGraphPanel reachabilityGraphPanel;
    private NetMatrixPanel netMatrixPanel;
    private MainFrame mainFrame;

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

    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void updateMarking() {
        getInitialMarkingPanel().updateMarking();
    }

    public void updateNetMatrix(){
        netMatrixPanel.updateTable();
    }

    public NetMatrixPanel getNetMatrixPanel() {
        return netMatrixPanel;
    }

    public void setNetMatrixPanel(NetMatrixPanel netMatrixPanel) {
        this.netMatrixPanel = netMatrixPanel;
    }
}
