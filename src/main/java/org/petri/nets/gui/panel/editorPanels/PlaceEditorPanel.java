package org.petri.nets.gui.panel.editorPanels;

import org.petri.nets.gui.panel.PropertyEditorAbstractPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Tomasz on 2015-06-06.
 */
public class PlaceEditorPanel extends PropertyEditorAbstractPanel<String> {
    public static final String DESCRIPTION_LABEL_TEXT = "Opis";
    private final JTextField descriptionTextField;

    public PlaceEditorPanel() {
        GridLayout mgr = new GridLayout(2, 0);
        mgr.setVgap(10);
        mgr.setHgap(20);
        setLayout(mgr);

        JLabel descriptionLabel = new JLabel(DESCRIPTION_LABEL_TEXT);
        descriptionTextField = new JTextField(20);

        add(descriptionLabel);
        add(descriptionTextField);
    }

    @Override
    public void okClicked() {
        publishChanges(descriptionTextField.getText());
        super.okClicked();
    }

    public void setDescription(String description) {
        descriptionTextField.setText(description);
    }
}
