package org.petri.nets.service;

import org.petri.nets.gui.MainFrame;
import org.petri.nets.model.DomainModel;
import org.petri.nets.model.Place;
import org.petri.nets.model.Transition;
import org.petri.nets.model.reachability.TransitionEdge;
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
    public void exportMatrixToFile(File file){
        int x = domainModel.getPetriNet().getPlaceMap().size();
        int y = domainModel.getPetriNet().getTransitionMap().size();
        Integer[][] plusMatrix = createPlusMatrix(x,y);
        Integer[][] minusMatrix = createMinusMatrix(x,y);
        Integer[][] generalMatrix = createGeneralMatrix(plusMatrix, minusMatrix,x,y);
        try {
            // create a new file with an ObjectOutputStream
            FileOutputStream out = new FileOutputStream(file);
            PrintWriter oout = new PrintWriter(out);

            // write domainModel in the file
            oout.println("Input matrix: ");
            oout.println("");
            //oout.println(matrixToString(plusMatrix, oout));
            writeMatrixToFile(plusMatrix, oout);
            oout.println("");
            oout.println("");
            oout.println("Output matrix: ");
            oout.println("");
            //oout.println(matrixToString(minusMatrix));
            writeMatrixToFile(minusMatrix, oout);
            oout.println("");
            oout.println("");
            oout.println("Graph matrix: ");
            oout.println("");
            //oout.println(matrixToString(generalMatrix));
            writeMatrixToFile(generalMatrix, oout);
            oout.flush();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private Integer[][] createPlusMatrix(int x, int y){
        Integer[][] plusMatrix = new Integer[x][y];

        for (Integer placeKey: domainModel.getSyncModel().getPlaceIds()){
            Place place = domainModel.getPetriNet().getPlaceMap().get(placeKey);
            for(Integer transitKey: domainModel.getSyncModel().getTransitionIds()){
                Transition transition = domainModel.getPetriNet().getTransitionMap().get(transitKey);
                if(place.getTransitionsFrom().get(transition) == null){
                    plusMatrix[placeKey-1][transitKey-1] =  0;
                }else{
                    plusMatrix[placeKey-1][transitKey-1] =  place.getTransitionsFrom().get(transition).getValue();
                }
            }
        }
        return plusMatrix;
    }
    private Integer[][] createMinusMatrix(int x, int y){
        Integer[][] minusMatrix = new Integer[x][y];
        for (Integer placeKey: domainModel.getSyncModel().getPlaceIds()){
            Place place = domainModel.getPetriNet().getPlaceMap().get(placeKey);
            for(Integer transitKey: domainModel.getSyncModel().getTransitionIds()){
                Transition transition = domainModel.getPetriNet().getTransitionMap().get(transitKey);
                if(place.getTransitionsTo().get(transition) == null){
                    minusMatrix[placeKey-1][transitKey-1] =  0;
                }else{
                    minusMatrix[placeKey-1][transitKey-1] =  place.getTransitionsTo().get(transition).getValue();
                }
            }
        }
        return minusMatrix;
    }
    private Integer[][] createGeneralMatrix(Integer[][] plus, Integer[][] minus,int x,int y){
        Integer[][] generalMatrix = new Integer[x][y];

        for (int i=0 ; i<generalMatrix.length ; i++){
            for (int j=0 ; j<generalMatrix[i].length ; j++){
                generalMatrix[i][j] = plus[i][j] - minus[i][j];
            }
        }
        return generalMatrix;
    }
    private void writeMatrixToFile(Integer[][] matrix, PrintWriter out){
        String matrixString = "";
        for(int i=0; i<matrix.length;i++){
            for(int j =0; j<matrix[i].length;j++){
                matrixString += matrix[i][j];
            }
            out.println(matrixString);
            matrixString ="";
        }

    }
    public DomainModel getDomainModel() {
        return domainModel;
    }

    public void setDomainModel(DomainModel domainModel) {
        this.domainModel = domainModel;
    }
}
