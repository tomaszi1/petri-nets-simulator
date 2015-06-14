package org.petri.nets.gui;

import org.petri.nets.gui.dialog.GlobalDialogsHandler;
import org.petri.nets.gui.graph.petriNet.PetriNetGraphUI;
import org.petri.nets.gui.panel.PetriNetWrapperPanel;
import org.petri.nets.gui.panel.ReachabilityGraphPanel;
import org.petri.nets.gui.panel.SideMenuPanel;
import org.petri.nets.service.GraphService;
import org.petri.nets.synhronize.SynchronizePanel;
import org.petri.nets.utils.ClosingWindowListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame {
    public static final String FRAME_TITLE = "Symulator sieci Petriego 2015";
    public static final int FRAME_WIDTH = 900;
    public static final int FRAME_HEIGHT = 600;

    private SideMenuPanel sideMenuWrapper;
    private JSplitPane graphsWrapper;

    public MainFrame(GraphService graphService) {
        super(FRAME_TITLE);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);

        addWindowListener(new ClosingWindowListener() {
            @Override
            public void windowClosing(WindowEvent e) {
                confirmClosing();
            }
        });

        initComponents(graphService);
        graphService.invalidateReachabilityGraph();
    }

    private void initComponents(GraphService graphService) {
        GlobalDialogsHandler globalDialogsHandler = new GlobalDialogsHandler(this);

        PetriNetGraphUI petriNetGraphUI = new PetriNetGraphUI(graphService, globalDialogsHandler);
        graphService.getPetriNetGraph().setUI(petriNetGraphUI);

        sideMenuWrapper = new SideMenuPanel(graphService, globalDialogsHandler);

        SynchronizePanel synchronizePanel = graphService.getSynchronizePanel();
        PetriNetWrapperPanel petriNetWrapperPanel = new PetriNetWrapperPanel(graphService);
        synchronizePanel.setPetriNetWrapperPanel(petriNetWrapperPanel);

        synchronizePanel.setPetriNetPropertiesPanel(sideMenuWrapper.getPetriNetPropertiesPanel());

        ReachabilityGraphPanel reachGraphPanel = new ReachabilityGraphPanel(graphService);
        synchronizePanel.setReachabilityGraphPanel(reachGraphPanel);

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

    private void confirmClosing() {
        Object[] options = {"Tak", "Nie"};
        int close = JOptionPane.showOptionDialog(this, "Czy na pewno chcesz zamknąć?", "Zamykanie", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        if (close == 0)
            dispose();
    }
}
