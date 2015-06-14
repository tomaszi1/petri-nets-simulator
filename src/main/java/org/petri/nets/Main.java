package org.petri.nets;

import org.jgraph.JGraph;
import org.petri.nets.gui.MainFrame;
import org.petri.nets.gui.graph.petriNet.JGraphFactory;
import org.petri.nets.model.ListPetriNet;
import org.petri.nets.model.DomainModel;
import org.petri.nets.service.GraphService;
import org.petri.nets.service.GraphServiceImpl;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        setLookAndFeel();

        DomainModel domainModel = new DomainModel();
        domainModel.setPetriNet(new ListPetriNet());

        JGraph graph = JGraphFactory.createGraph();
        domainModel.setPetriNetGraph(graph);

        GraphService graphService = new GraphServiceImpl(domainModel);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame mainFrame = new MainFrame(graphService);
                mainFrame.setVisible(true);
            }
        });
    }


    private static void setLookAndFeel(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException |
                InstantiationException |
                UnsupportedLookAndFeelException |
                IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
