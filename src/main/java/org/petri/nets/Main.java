package org.petri.nets;

import org.jgraph.JGraph;
import org.jgraph.graph.GraphModel;
import org.petri.nets.gui.MainFrame;
import org.petri.nets.model.ListPetriNet;
import org.petri.nets.model.Model;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("Drop the bass");

        SwingUtilities.invokeLater(() -> {
            Model model = new Model();
            model.setPetriNet(new ListPetriNet());
            model.setPetriNetGraphModel(new JGraph().getModel());
            model.setReachabilityGraphModel(new JGraph().getModel());
            MainFrame mainFrame = new MainFrame(model);
        });

    }

}
