package org.petri.nets.service;

import org.jgraph.JGraph;
import org.petri.nets.gui.MainFrame;
import org.petri.nets.model.DomainModel;
import org.petri.nets.model.JGraphFactory;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Asia on 2015-05-31.
 */

public class SaveGraphAsFile {
    private DomainModel domainModel ;
    public SaveGraphAsFile(DomainModel domainModel){
        this.domainModel = domainModel;
    }
    public void saveGaph(){
        System.out.println("kurwa..");
        try {
           // create a new file with an ObjectOutputStream
            FileOutputStream out = new FileOutputStream("test.txt");
            ObjectOutputStream oout = new ObjectOutputStream(out);

            // write something in the file
            oout.writeObject(domainModel);
            oout.flush();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    public void openGraph(){
        try{
            // create an ObjectInputStream for the file we created before
            ObjectInputStream ois =
                    new ObjectInputStream(new FileInputStream("test.txt"));


            // read and print an object and cast it as string
            DomainModel domainModel = (DomainModel) ois.readObject();

            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException |
                    InstantiationException |
                    UnsupportedLookAndFeelException |
                    IllegalAccessException e) {
                e.printStackTrace();
            }

            SwingUtilities.invokeLater(() -> new MainFrame(domainModel));

        }catch(Exception e){
            e.printStackTrace();
        }


    }
    public DomainModel getDomainModel() {
        return domainModel;
    }

    public void setDomainModel(DomainModel domainModel) {
        this.domainModel = domainModel;
    }

    /*        DomainModel domainModel = new DomainModel();
        domainModel.setPetriNet(new ListPetriNet());
        JGraph graph = JGraphFactory.createGraph();
    domainModel.setPetriNetGraph(graph);
    domainModel.setReachabilityGraph(JGraphFactory.createGraph());

    try {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (ClassNotFoundException |
    InstantiationException |
    UnsupportedLookAndFeelException |
    IllegalAccessException e) {
        e.printStackTrace();
    }

    SwingUtilities.invokeLater(() -> new MainFrame(domainModel));*/
}
