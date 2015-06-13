package org.petri.nets.gui.panel;

import org.petri.nets.gui.dialog.GlobalDialogsHandler;
import org.petri.nets.service.GraphService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class SideMenuPanel extends JPanel {
    private static final int MENU_WIDTH = 220;
    private static final String OPEN_FILE_BUTTON_TEXT = "Otwórz plik";
    private static final String SAVE_FILE_BUTTON_TEXT = "Zapisz graf";
    private static final String EXPORT_MATRIX_TO_FILE_BUTTON_TEXT = "Eksportuj macierz do pliku";
    private static final String PANEL_TITLE = "Menu";

    private GraphService graphService;
    private GlobalDialogsHandler dialogsHandler;
    JButton openButton, saveButton, exportButton;
    JFileChooser fc;
    private final NetMatrixPanel netMatrixPanel;


    public SideMenuPanel(GraphService graphService, GlobalDialogsHandler dialogsHandler) {
        this.graphService = graphService;
        this.dialogsHandler = dialogsHandler;

        setLayout(new BorderLayout());

        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), PANEL_TITLE));
        setPreferredSize(new Dimension(MENU_WIDTH, 500));
        //add(new "Menu",BorderLayout.CENTER);
        createButtons();
        netMatrixPanel = new NetMatrixPanel(graphService);
        this.add(netMatrixPanel);
    }

    private void createButtons() {

        fc = new JFileChooser();

        //Create the open button.
        openButton = new JButton(OPEN_FILE_BUTTON_TEXT);
        openButton.addActionListener(this::openGraph);

        //Create the save button.
        saveButton = new JButton(SAVE_FILE_BUTTON_TEXT);
        saveButton.addActionListener(this::saveGraph);

        //Create the save button.
        exportButton = new JButton(EXPORT_MATRIX_TO_FILE_BUTTON_TEXT);
        exportButton.addActionListener(this::exportMatrix);

        //For layout purposes, put the buttons in a separate panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(200, 65));
        buttonPanel.add(openButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(exportButton, BorderLayout.PAGE_END);

        //Add the buttons to this panel.
        add(buttonPanel, BorderLayout.PAGE_START);
    }

    private void saveGraph(ActionEvent e) {
        int returnVal = fc.showSaveDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            try {
                graphService.saveGraphAsFile(file);
            } catch (IOException e1) {
                dialogsHandler.showWarning("Zapis nieudany", "Błąd przy zapisie, przyczyna:\n" + e1.getMessage());
                e1.printStackTrace();
            }
        }
    }

    private void openGraph(ActionEvent e) {
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            try {
                graphService.openGraphFromFile(file);
            } catch (Exception ex) {
                dialogsHandler.showWarning("Nieudane otwarcie pliku", "Nieprawidłowy plik lub błąd przy wczytywaniu");
                ex.printStackTrace();
            }
        }
    }

    private void exportMatrix(ActionEvent e) {
        int returnVal = fc.showOpenDialog(SideMenuPanel.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            try {
                graphService.exportMatrixToFile(file);
            } catch (Exception ex) {
                dialogsHandler.showWarning("Nieudane zapisanie macierzy", "Błąd przy zapisie macierzy, przyczyna:\n" + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }


    public NetMatrixPanel getNetMatrixPanel() {
        return netMatrixPanel;
    }
}
