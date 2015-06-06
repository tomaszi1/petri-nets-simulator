package org.petri.nets.gui.panel;

import org.petri.nets.gui.dialog.DialogCloseListener;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Tomasz on 2015-06-06.
 */
public class TransitionEditorPanel extends DialogClosingAbstractPanel{
    public static final String DESCRIPTION_LABEL_TEXT = "Opis";
    public static final String PRIORITY_LABEL_TEXT = "Priorytet przejścia";
    private final JTextField descriptionTextField;
    private final JTextField priorityTextField;

    public TransitionEditorPanel() {
        setLayout(new GridLayout(4, 0));

        JLabel descriptionLabel = new JLabel(DESCRIPTION_LABEL_TEXT);
        descriptionTextField = new JTextField(20);
        JLabel priorityLabel = new JLabel(PRIORITY_LABEL_TEXT);
        priorityTextField = new JTextField(5);

        add(descriptionLabel);
        add(descriptionTextField);
        add(priorityLabel);
        add(priorityTextField);
    }

    public void setDescription(String description){
        descriptionTextField.setText(description);
    }

    public void setPriority(int priority){
        priorityTextField.setText(String.valueOf(priority));
    }
}
