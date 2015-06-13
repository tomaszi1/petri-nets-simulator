package org.petri.nets.service;

import org.jgraph.JGraph;
import org.jgraph.event.GraphLayoutCacheListener;
import org.petri.nets.gui.MainFrame;
import org.petri.nets.gui.graph.petriNet.JGraphFactory;
import org.petri.nets.model.DomainModel;

import javax.swing.*;
import java.io.*;

/**
 * Created by Asia on 2015-05-31.
 */

public class SaveGraphAsFile {
    private DomainModel domainModel;

    public SaveGraphAsFile(DomainModel domainModel) {
        this.domainModel = domainModel;
    }

    public void saveGaph(File file) throws IOException {
        ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(file));
        domainModel.validate();
        for (GraphLayoutCacheListener listener : domainModel.getGraphLayoutCache().getGraphLayoutCacheListeners()) {
            domainModel.getGraphLayoutCache().removeGraphLayoutCacheListener(listener);
        }
        stream.writeObject(domainModel);
        stream.flush();
    }

    public void openGraph(File file) throws IOException, ClassNotFoundException {
        ObjectInputStream ois =
                new ObjectInputStream(new FileInputStream(file));

        DomainModel domainModel = (DomainModel) ois.readObject();
        JGraph graph = JGraphFactory.createGraph();
        graph.setGraphLayoutCache(domainModel.getGraphLayoutCache());
        domainModel.setPetriNetGraph(graph);

        GraphService graphService = new GraphServiceImpl(domainModel);
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame(graphService);
            mainFrame.setVisible(true);
        });
    }
}
