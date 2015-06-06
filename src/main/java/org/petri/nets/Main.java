package org.petri.nets;

import org.jgraph.JGraph;
import org.petri.nets.gui.MainFrame;
import org.petri.nets.gui.graph.JGraphFactory;
import org.petri.nets.model.ListPetriNet;
import org.petri.nets.model.DomainModel;
import org.petri.nets.service.GraphService;
import org.petri.nets.service.GraphServiceImpl;
import org.petri.nets.synhronize.SynchronizePanel;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        DomainModel domainModel = new DomainModel();
        domainModel.setPetriNet(new ListPetriNet());
        JGraph graph = JGraphFactory.createGraph();

        domainModel.setPetriNetGraph(graph);

        SynchronizePanel synchronizePanel = new SynchronizePanel();

        GraphService graphService = new GraphServiceImpl(domainModel, synchronizePanel);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException |
                InstantiationException |
                UnsupportedLookAndFeelException |
                IllegalAccessException e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> new MainFrame(graphService));

    }

}
