package org.petri.nets;

import org.jgraph.JGraph;
import org.jgraph.graph.GraphModel;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Drop the bass");

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                HelloJGraphFrame frame = new HelloJGraphFrame();
                frame.setVisible(true);
            }
        });


    }


    public static class HelloJGraphFrame extends JFrame {
        public HelloJGraphFrame() throws HeadlessException {
            super("Hello");
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setSize(400, 320);

            JGraph jGraph = new JGraph();
            GraphModel model = jGraph.getModel();

            model.beginUpdate();
            try{
                JGraph.addSampleData(model);
            }finally {
                model.endUpdate();
            }

            add(jGraph);
        }
    }

}
