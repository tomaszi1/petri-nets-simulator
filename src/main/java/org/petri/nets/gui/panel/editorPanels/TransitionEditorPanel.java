package org.petri.nets.gui.panel.editorPanels;

import com.google.common.base.Strings;
import com.google.common.primitives.Ints;
import org.petri.nets.gui.panel.PropertyEditorAbstractPanel;
import org.petri.nets.utils.TransitionProperties;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Tomasz on 2015-06-06.
 */
public class TransitionEditorPanel extends PropertyEditorAbstractPanel<TransitionProperties> {
    public static final String DESCRIPTION_LABEL_TEXT = "Opis";
    public static final String PRIORITY_LABEL_TEXT = "Priorytet przejścia";
    private final JTextField descriptionTextField;
    private final JTextField priorityTextField;

    public TransitionEditorPanel() {
        GridLayout mgr = new GridLayout(4, 0);
        mgr.setVgap(10);
        mgr.setHgap(20);
        setLayout(mgr);

        JLabel descriptionLabel = new JLabel(DESCRIPTION_LABEL_TEXT);
        descriptionTextField = new JTextField(20);
        JLabel priorityLabel = new JLabel(PRIORITY_LABEL_TEXT);
        priorityTextField = new JTextField(5);

        add(descriptionLabel);
        add(descriptionTextField);
        add(priorityLabel);
        add(priorityTextField);
    }

    @Override
    public void okClicked() {
        TransitionProperties props = new TransitionProperties();
        props.description = Strings.emptyToNull(descriptionTextField.getText());

        String priorityText = priorityTextField.getText();
        Integer priority;
        if (Strings.isNullOrEmpty(priorityText) || (priority = Ints.tryParse(priorityText)) == null) {
            Toolkit.getDefaultToolkit().beep();
            return;
        }
        props.priority = priority;
        publishChanges(props); // FIXME
        super.okClicked();
    }

    public void setDescription(String description) {
        descriptionTextField.setText(description);
    }

    public void setPriority(int priority) {
        priorityTextField.setText(String.valueOf(priority));
    }
}
