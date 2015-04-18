package org.petri.nets.gui;

import org.jgraph.JGraph;
import org.petri.nets.model.Model;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public static final String FRAME_TITLE = "Symulator sieci Petriego 2015";
    public static final int FRAME_WIDTH = 900;
    public static final int FRAME_HEIGHT = 600;

    private SideMenuWrapper sideMenuWrapper;
    private JSplitPane graphsWrapper;

    public MainFrame(Model model) {
        super(FRAME_TITLE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);

        initComponents(model);
    }

    private void initComponents(Model model) {
        sideMenuWrapper = new SideMenuWrapper();

        PetriNetWrapperPanel petriNetWrapperPanel = new PetriNetWrapperPanel(model);
        ReachabilityGraphPanel reachGraphPanel = new ReachabilityGraphPanel(model);

        graphsWrapper = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                petriNetWrapperPanel,
                reachGraphPanel);
        graphsWrapper.setResizeWeight(0.5);

        add(graphsWrapper, BorderLayout.CENTER);
        add(sideMenuWrapper, BorderLayout.LINE_END);

    }
}
