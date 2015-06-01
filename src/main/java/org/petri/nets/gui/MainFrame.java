package org.petri.nets.gui;

import org.petri.nets.gui.panel.PetriNetWrapperPanel;
import org.petri.nets.gui.panel.ReachabilityGraphPanel;
import org.petri.nets.gui.panel.SideMenuWrapper;
import org.petri.nets.model.DomainModel;
import org.petri.nets.synhronize.SynchronizePanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public static final String FRAME_TITLE = "Symulator sieci Petriego 2015";
    public static final int FRAME_WIDTH = 900;
    public static final int FRAME_HEIGHT = 600;

    private SideMenuWrapper sideMenuWrapper;
    private JSplitPane graphsWrapper;
    private SynchronizePanel synchronizePanel;

    public MainFrame(DomainModel domainModel) {
        super(FRAME_TITLE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);

        initComponents(domainModel);
    }

    private void initComponents(DomainModel domainModel) {
        sideMenuWrapper = new SideMenuWrapper(domainModel);

        synchronizePanel = new SynchronizePanel();
        PetriNetWrapperPanel petriNetWrapperPanel = new PetriNetWrapperPanel(domainModel, synchronizePanel);
        synchronizePanel.setPetriNetWrapperPanel(petriNetWrapperPanel);

        ReachabilityGraphPanel reachGraphPanel = new ReachabilityGraphPanel(domainModel);
        synchronizePanel.setReachabilityGraphPanel(reachGraphPanel);

        setGraphsWrapper(new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                petriNetWrapperPanel,
                reachGraphPanel));
        getGraphsWrapper().setResizeWeight(0.5);

        add(getGraphsWrapper(), BorderLayout.CENTER);
        add(sideMenuWrapper, BorderLayout.LINE_END);
    }

    public JSplitPane getGraphsWrapper() {
        return graphsWrapper;
    }

    public void setGraphsWrapper(JSplitPane graphsWrapper) {
        this.graphsWrapper = graphsWrapper;
    }
}
