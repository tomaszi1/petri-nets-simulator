package org.petri.nets.gui;

import org.petri.nets.model.DomainModel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public static final String FRAME_TITLE = "Symulator sieci Petriego 2015";
    public static final int FRAME_WIDTH = 900;
    public static final int FRAME_HEIGHT = 600;

    private SideMenuWrapper sideMenuWrapper;
    private JSplitPane graphsWrapper;

    public MainFrame(DomainModel domainModel) {
        super(FRAME_TITLE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);

        initComponents(domainModel);
    }

    private void initComponents(DomainModel domainModel) {
        sideMenuWrapper = new SideMenuWrapper();

        PetriNetWrapperPanel petriNetWrapperPanel = new PetriNetWrapperPanel(domainModel);
        ReachabilityGraphPanel reachGraphPanel = new ReachabilityGraphPanel(domainModel);

        graphsWrapper = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                petriNetWrapperPanel,
                reachGraphPanel);
        graphsWrapper.setResizeWeight(0.5);

        add(graphsWrapper, BorderLayout.CENTER);
        add(sideMenuWrapper, BorderLayout.LINE_END);
    }
}
