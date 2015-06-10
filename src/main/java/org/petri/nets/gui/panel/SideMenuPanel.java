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
    JButton openButton, saveButton,exportButton;
    JFileChooser fc;
    public static final String PANEL_TITLE = "Menu";
    private final NetMatrixPanel netMatrixPanel;


    public SideMenuPanel(GraphService graphService) {
        //this.setSaveGraphAsFile(new SaveGraphAsFile(graphService.getDomainModel()));
        this.graphService = graphService;

        setLayout(new BorderLayout());

        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), PANEL_TITLE));
        setPreferredSize(new Dimension(MENU_WIDTH,500));
        //add(new "Menu",BorderLayout.CENTER);
        createSaveButtom();
        netMatrixPanel = new NetMatrixPanel(graphService);
        this.add(netMatrixPanel);

    }

    private void createSaveButtom(){

        fc = new JFileChooser();

        //Create the open button.
        openButton = new JButton("Otwórz plik");
        openButton.addActionListener(this::openGraph);

        //Create the save button.
        saveButton = new JButton("Zapisz graf");
        saveButton.addActionListener(this::saveGraph);

        //Create the save button.
        exportButton = new JButton("Eksportuj macierz do pliku");
        exportButton.addActionListener(this::exportMatrix);

        //For layout purposes, put the buttons in a separate panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(200,65));
        buttonPanel.add(openButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(exportButton, BorderLayout.PAGE_END);


        //Add the buttons to this panel.
        add(buttonPanel, BorderLayout.PAGE_START);

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

    private void openGraph(ActionEvent e) {
        int returnVal = fc.showOpenDialog(SideMenuPanel.this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            System.out.println("Opening: " + file.getName() + ".");
            graphService.openGraphfromFile(file);
        } else {
            System.out.println("Open command cancelled by user.");
        }
    }
    private void exportMatrix(ActionEvent e){
        int returnVal = fc.showOpenDialog(SideMenuPanel.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            System.out.println("Save graph matrix in : " + file.getName() + ".");
            graphService.exportMatrixToFile(file);
        } else {
            System.out.println("Open command cancelled by user.");
        }

    }


    public NetMatrixPanel getNetMatrixPanel() {
        return netMatrixPanel;
    }
}
