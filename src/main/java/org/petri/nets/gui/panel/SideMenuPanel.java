package org.petri.nets.gui.panel;

import org.petri.nets.model.DomainModel;
import org.petri.nets.service.GraphService;
import org.petri.nets.service.SaveGraphAsFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SideMenuPanel extends JPanel {
    private SaveGraphAsFile saveGraphAsFile;
    private DomainModel domainModel;
    public SideMenuPanel(GraphService graphService) {
        this.domainModel = graphService.getDomainModel();
        this.setSaveGraphAsFile(new SaveGraphAsFile(graphService.getDomainModel()));
        setLayout(new FlowLayout());
        add(new JLabel("Menu"));
        //add(new JButton("Zapisz graf w pliku"));
        //add(new JButton("Odczytaj graf w pliku"));
        JButton imgbtnSave = new JButton("Zapisz graf w pliku");
        imgbtnSave.setSize(10, 10);
        imgbtnSave.setLocation(10, 200);
        imgbtnSave.addActionListener(this::saveGraph);
        add(imgbtnSave);
        //JFileChooser fileChooser = new JFileChooser();
        JButton imgbtn = new JButton("Odczytaj graf w pliku");
        imgbtn.setSize(10, 10);
        imgbtn.setLocation(30, 200);
        imgbtn.addActionListener(this::openGraph);
        add(imgbtn);

    }

    private void saveGraph(ActionEvent e) {

        getSaveGraphAsFile().saveGaph();
    }

    private void openGraph(ActionEvent e){
        getSaveGraphAsFile().openGraph();
    }

    public SaveGraphAsFile getSaveGraphAsFile() {
        return saveGraphAsFile;
    }

    public void setSaveGraphAsFile(SaveGraphAsFile saveGraphAsFile) {
        this.saveGraphAsFile = saveGraphAsFile;
    }
}
