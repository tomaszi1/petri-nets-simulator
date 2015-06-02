package org.petri.nets.service;

import org.jgraph.JGraph;
import org.petri.nets.gui.MainFrame;
import org.petri.nets.model.DomainModel;
import org.petri.nets.synhronize.SynchronizePanel;

import javax.swing.*;
import java.io.*;

/**
 * Created by Asia on 2015-05-31.
 */

public class SaveGraphAsFile {
    private DomainModel domainModel ;
    public SaveGraphAsFile(DomainModel domainModel){
        this.domainModel = domainModel;
    }

    public void saveGaph(File file){
        try {
           // create a new file with an ObjectOutputStream
            FileOutputStream out = new FileOutputStream(file);
            ObjectOutputStream oout = new ObjectOutputStream(out);

            // write domainModel in the file
            oout.writeObject(domainModel);
            oout.flush();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    public void openGraph(File file){
        try{
            // create an ObjectInputStream for the file we created before
            ObjectInputStream ois =
                    new ObjectInputStream(new FileInputStream(file));

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
            SynchronizePanel synchronizePanel = new SynchronizePanel();

            GraphService graphService = new GraphServiceImpl(domainModel, synchronizePanel);
            SwingUtilities.invokeLater(() -> new MainFrame(graphService));

        }catch (StreamCorruptedException e){
         System.out.println("Nieprawidłowy plik");
        }
        catch(Exception e){
            e.printStackTrace();
        }


    }

    public DomainModel getDomainModel() {
        return domainModel;
    }

    public void setDomainModel(DomainModel domainModel) {
        this.domainModel = domainModel;
    }
}
