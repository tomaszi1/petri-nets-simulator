package org.petri.nets.gui.panel;

import org.petri.nets.gui.dialog.DialogCloseListener;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Tomasz on 2015-06-06.
 */
public class PlaceEditorPanel extends DialogClosingAbstractPanel {
    public static final String DESCRIPTION_LABEL_TEXT = "Opis";
    private final JTextField descriptionTextField;

    public PlaceEditorPanel() {
        setLayout(new GridLayout(4, 0));

        JLabel descriptionLabel = new JLabel(DESCRIPTION_LABEL_TEXT);
        descriptionTextField = new JTextField(20);

        add(descriptionLabel);
        add(descriptionTextField);
    }

    public void setDescription(String description) {
        descriptionTextField.setText(description);
    }
}
