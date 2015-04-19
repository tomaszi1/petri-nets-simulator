package org.petri.nets;

import org.jgraph.graph.DefaultGraphModel;
import org.petri.nets.gui.MainFrame;
import org.petri.nets.model.ListPetriNet;
import org.petri.nets.model.DomainModel;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        DomainModel domainModel = new DomainModel();
        domainModel.setPetriNet(new ListPetriNet());
        domainModel.setPetriNetGraphModel(new DefaultGraphModel());
        domainModel.setReachabilityGraphModel(new DefaultGraphModel());

        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame(domainModel);
        });

    }

}
