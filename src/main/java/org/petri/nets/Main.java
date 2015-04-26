package org.petri.nets;

import org.jgraph.graph.DefaultGraphModel;
import org.jgraph.graph.GraphLayoutCache;
import org.petri.nets.gui.MainFrame;
import org.petri.nets.gui.graph.PlaceGraphCell;
import org.petri.nets.model.ListPetriNet;
import org.petri.nets.model.DomainModel;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        DomainModel domainModel = new DomainModel();
        domainModel.setPetriNet(new ListPetriNet());
        GraphLayoutCache petriNetGraphLayoutCache = new GraphLayoutCache();
        petriNetGraphLayoutCache.insert(new PlaceGraphCell("P1"));
        domainModel.setPetriNetGraphLayoutCache(petriNetGraphLayoutCache);
        domainModel.setReachabilityGraphLayoutCache(new GraphLayoutCache());

        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame(domainModel);
        });

    }

}
