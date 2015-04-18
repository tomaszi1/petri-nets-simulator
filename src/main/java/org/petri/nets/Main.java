package org.petri.nets;

import org.jgraph.graph.DefaultGraphModel;
import org.petri.nets.gui.MainFrame;
import org.petri.nets.model.ListPetriNet;
import org.petri.nets.model.DomainModel;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("Drop the bass");

        SwingUtilities.invokeLater(() -> {
            DomainModel domainModel = new DomainModel();
            domainModel.setPetriNet(new ListPetriNet());
            domainModel.setPetriNetGraphModel(new DefaultGraphModel());
            domainModel.setReachabilityGraphModel(new DefaultGraphModel());

            MainFrame mainFrame = new MainFrame(domainModel);
        });

    }

}
