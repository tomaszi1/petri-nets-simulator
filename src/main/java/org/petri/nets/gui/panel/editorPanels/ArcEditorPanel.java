package org.petri.nets.gui.panel.editorPanels;

import com.google.common.base.Strings;
import com.google.common.primitives.Ints;
import org.petri.nets.gui.panel.PropertyEditorAbstractPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Tomasz on 2015-06-06.
 */
public class ArcEditorPanel extends PropertyEditorAbstractPanel<Integer> {
    public static final String VALUE_LABEL_TEXT = "Wartość łuku";

    private final JTextField valueTextField;

    public ArcEditorPanel() {
        GridLayout mgr = new GridLayout(2, 0);
        mgr.setVgap(10);
        mgr.setHgap(20);
        setLayout(mgr);

        JLabel descriptionLabel = new JLabel(VALUE_LABEL_TEXT);
        valueTextField = new JTextField(20);

        add(descriptionLabel);
        add(valueTextField);
    }

    @Override
    public void okClicked() {
        String text = Strings.nullToEmpty(valueTextField.getText());
        Integer value = Ints.tryParse(text);
        if (value == null) {
            Toolkit.getDefaultToolkit().beep();
            return;
        }
        publishChanges(value);
        super.okClicked();
    }

    public void setValue(int value) {
        valueTextField.setText(String.valueOf(value));
    }
}
