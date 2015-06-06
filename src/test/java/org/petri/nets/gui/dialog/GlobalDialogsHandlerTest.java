package org.petri.nets.gui.dialog;

import org.junit.Test;
import org.petri.nets.gui.panel.OkCancelPanel;
import org.petri.nets.gui.panel.TransitionEditorPanel;

import javax.swing.*;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by Tomasz on 2015-06-06.
 */
public class GlobalDialogsHandlerTest {

    @Test
    public void testDialog() throws InvocationTargetException, InterruptedException {

        SwingUtilities.invokeAndWait(() -> {
            JFrame frame = new JFrame();
            frame.setVisible(true);
            GlobalDialogsHandler globalDialogsHandler = new GlobalDialogsHandler(frame);

            TransitionEditorPanel listenerPanel = new TransitionEditorPanel();
            OkCancelPanel okCancelPanel = new OkCancelPanel(listenerPanel);

            DialogCloseListener dialogCloseListener = globalDialogsHandler.showDialog("Hello", okCancelPanel);
            listenerPanel.setDialogCloseListener(dialogCloseListener);
        });
    }

}