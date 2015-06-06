package org.petri.nets.gui.dialog;

import com.google.common.base.Preconditions;
import com.sun.istack.internal.NotNull;
import org.petri.nets.gui.MainFrame;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Tomasz on 2015-06-05.
 */
public class GlobalDialogsHandler {
    private JFrame mainFrame;
    private JDialog dialog;

    public GlobalDialogsHandler(JFrame mainFrame) {
//        Preconditions.checkNotNull(mainFrame);
        this.mainFrame = mainFrame;
        dialog = new JDialog();
    }

    public DialogCloseListener showDialog(String title, JPanel dialogContent) {
        if (dialog.isVisible())
            throw new IllegalStateException("Another dialog is active");
        dialog.setModal(mainFrame!=null);
        dialog.setTitle(title);
        dialog.setContentPane(dialogContent);
        dialog.pack();
        dialog.setVisible(true);
        return dialogCloseListener;
    }

    public boolean isDialogShowing() {
        return dialog.isVisible();
    }

    private DialogCloseListener dialogCloseListener = new DialogCloseListener() {
        @Override
        public void closeDialog() {
            dialog.setVisible(false);
        }
    };
}
