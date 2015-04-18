package org.petri.nets;

import org.jgraph.JGraph;
import org.jgraph.graph.GraphModel;
import org.petri.nets.gui.MainFrame;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("Drop the bass");

        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
        });

    }

}
