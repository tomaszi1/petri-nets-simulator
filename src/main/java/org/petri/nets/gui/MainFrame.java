package org.petri.nets.gui;

import org.petri.nets.gui.panel.PetriNetWrapperPanel;
import org.petri.nets.gui.panel.ReachabilityGraphPanel;
import org.petri.nets.gui.panel.SideMenuWrapper;
import org.petri.nets.service.GraphService;
import org.petri.nets.synhronize.SynchronizePanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public static final String FRAME_TITLE = "Symulator sieci Petriego 2015";
    public static final int FRAME_WIDTH = 900;
    public static final int FRAME_HEIGHT = 600;

    private SideMenuWrapper sideMenuWrapper;
    private JSplitPane graphsWrapper;

    public MainFrame(GraphService graphService) {
        super(FRAME_TITLE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);

        initComponents(graphService);
    }

    private void initComponents(GraphService graphService) {
        sideMenuWrapper = new SideMenuWrapper(graphService);

        SynchronizePanel synchronizePanel = graphService.getSynchronizeService().getSynchronizePanel();
        PetriNetWrapperPanel petriNetWrapperPanel = new PetriNetWrapperPanel(graphService, synchronizePanel);
        synchronizePanel.setPetriNetWrapperPanel(petriNetWrapperPanel);

        ReachabilityGraphPanel reachGraphPanel = new ReachabilityGraphPanel(graphService);
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
