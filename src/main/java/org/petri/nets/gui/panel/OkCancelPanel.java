package org.petri.nets.gui.panel;

import javax.swing.*;
import java.awt.*;

public class OkCancelPanel extends JPanel{
    private static final String OK_MESSAGE = "OK";
    public static final String CANCEL_MESSAGE = "Anuluj";

    public OkCancelPanel(OkCancelListenerAbstractPanel listenerPanel) {
        setLayout(new BorderLayout());
        JButton okButton = new JButton(OK_MESSAGE);
        JButton cancelButton = new JButton(CANCEL_MESSAGE);

        okButton.addActionListener(e -> listenerPanel.okClicked());
        cancelButton.addActionListener(e -> listenerPanel.cancelClicked());

        JPanel buttonsWrapper = new JPanel();
        buttonsWrapper.add(okButton);
        buttonsWrapper.add(cancelButton);

        add(listenerPanel, BorderLayout.CENTER);
        add(buttonsWrapper, BorderLayout.PAGE_END);
    }
}
