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
    private final String SAVE_FILE_BUTTON_TEXT = "Zapisz graf";
    private GraphService graphService;
    private GlobalDialogsHandler dialogsHandler;
    JButton openButton, saveButton;
    JFileChooser fc;
    public static final String PANEL_TITLE = "Menu";


    public SideMenuPanel(GraphService graphService, GlobalDialogsHandler dialogsHandler) {
        this.graphService = graphService;
        this.dialogsHandler = dialogsHandler;

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), PANEL_TITLE));
        setPreferredSize(new Dimension(MENU_WIDTH, 500));
        createSaveLoadButtons();
    }

    private void createSaveLoadButtons() {

        fc = new JFileChooser();

        openButton = new JButton(OPEN_FILE_BUTTON_TEXT);
        openButton.addActionListener(this::openGraph);

        saveButton = new JButton(SAVE_FILE_BUTTON_TEXT);
        saveButton.addActionListener(this::saveGraph);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(openButton);
        buttonPanel.add(saveButton);

        add(buttonPanel, BorderLayout.PAGE_END);
    }

    private void saveGraph(ActionEvent e) {
        int returnVal = fc.showSaveDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            try {
                graphService.saveGraphAsFile(file);
            } catch (IOException e1) {
                dialogsHandler.showWarning("Zapis nieudany","Błąd przy zapisie, przyczyna:\n"+e1.getMessage());
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
            }
        }
    }


}
