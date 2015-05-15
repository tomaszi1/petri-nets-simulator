package org.petri.nets;

import org.jgraph.JGraph;
import org.petri.nets.gui.MainFrame;
import org.petri.nets.model.JGraphFactory;
import org.petri.nets.gui.graph.PlaceGraphCell;
import org.petri.nets.gui.graph.TransitionGraphCell;
import org.petri.nets.model.ListPetriNet;
import org.petri.nets.model.DomainModel;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        DomainModel domainModel = new DomainModel();
        domainModel.setPetriNet(new ListPetriNet());
        JGraph graph = JGraphFactory.createGraph();
        graph.getGraphLayoutCache().insert(new PlaceGraphCell(1, 20, 20));
        graph.getGraphLayoutCache().insert(new TransitionGraphCell(1, 60, 60));
        domainModel.setPetriNetGraph(graph);
        domainModel.setReachabilityGraph(JGraphFactory.createGraph());


        SwingUtilities.invokeLater(() -> {
            new MainFrame(domainModel);
        });

    }

}
