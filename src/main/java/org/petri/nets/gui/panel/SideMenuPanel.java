package org.petri.nets.gui.panel;

import org.petri.nets.model.DomainModel;
import org.petri.nets.service.GraphService;
import org.petri.nets.service.SaveGraphAsFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class SideMenuPanel extends JPanel {
    private static final int MENU_WIDTH = 220;
    private GraphService graphService;
    JButton openButton, saveButton;
    JFileChooser fc;
    public static final String PANEL_TITLE = "Menu";


    public SideMenuPanel(GraphService graphService) {
        //this.setSaveGraphAsFile(new SaveGraphAsFile(graphService.getDomainModel()));
        this.graphService = graphService;

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), PANEL_TITLE));
        setPreferredSize(new Dimension(MENU_WIDTH,500));
        //add(new "Menu",BorderLayout.CENTER);
        createSaveButtom();

    }

    private void createSaveButtom(){

        fc = new JFileChooser();

        //Create the open button.
        openButton = new JButton("Otwórz plik");
        openButton.addActionListener(this::openGraph);

        //Create the save button.
        saveButton = new JButton("Zapisz graf");
        saveButton.addActionListener(this::saveGraph);

        //For layout purposes, put the buttons in a separate panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(openButton);
        buttonPanel.add(saveButton);

        //Add the buttons to this panel.
        add(buttonPanel, BorderLayout.PAGE_END);

    }

    private void saveGraph(ActionEvent e) {
        int returnVal = fc.showOpenDialog(SideMenuPanel.this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            graphService.saveGraphAsFile(file);
            System.out.println("Save in : " + file.getName() + ".");
        } else {
            System.out.println("Open command cancelled by user.");
        }

    }

    private void openGraph(ActionEvent e){
        int returnVal = fc.showOpenDialog(SideMenuPanel.this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            System.out.println("Opening: " + file.getName() + ".");
            graphService.openGraphfromFile(file);
        } else {
            System.out.println("Open command cancelled by user.");
        }


    }


}
