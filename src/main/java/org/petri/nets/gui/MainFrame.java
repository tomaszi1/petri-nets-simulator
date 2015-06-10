package org.petri.nets.gui;

import org.petri.nets.gui.dialog.GlobalDialogsHandler;
import org.petri.nets.gui.graph.petriNet.PetriNetGraphUI;
import org.petri.nets.gui.panel.PetriNetWrapperPanel;
import org.petri.nets.gui.panel.ReachabilityGraphPanel;
import org.petri.nets.gui.panel.SideMenuPanel;
import org.petri.nets.service.GraphService;
import org.petri.nets.synhronize.SynchronizePanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public static final String FRAME_TITLE = "Symulator sieci Petriego 2015";
    public static final int FRAME_WIDTH = 900;
    public static final int FRAME_HEIGHT = 600;

    private SideMenuPanel sideMenuWrapper;
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
        GlobalDialogsHandler globalDialogsHandler = new GlobalDialogsHandler(this);

        PetriNetGraphUI petriNetGraphUI = new PetriNetGraphUI(graphService, globalDialogsHandler);



        SynchronizePanel synchronizePanel = graphService.getSynchronizeService().getSynchronizePanel();
        PetriNetWrapperPanel petriNetWrapperPanel = new PetriNetWrapperPanel(graphService, petriNetGraphUI, synchronizePanel);
        synchronizePanel.setPetriNetWrapperPanel(petriNetWrapperPanel);

        ReachabilityGraphPanel reachGraphPanel = new ReachabilityGraphPanel(graphService);
        synchronizePanel.setReachabilityGraphPanel(reachGraphPanel);

        sideMenuWrapper = new SideMenuPanel(graphService);
        synchronizePanel.setNetMatrixPanel(sideMenuWrapper.getNetMatrixPanel());
        graphsWrapper = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                petriNetWrapperPanel,
                reachGraphPanel);
        graphsWrapper.setResizeWeight(0.5);

        add(getGraphsWrapper(), BorderLayout.CENTER);
        add(sideMenuWrapper, BorderLayout.LINE_END);
    }

    public JSplitPane getGraphsWrapper() {
        return graphsWrapper;
    }

}
