package org.petri.nets.gui.panel;

import org.petri.nets.gui.dialog.DialogCloseListener;

/**
 * Created by Tomasz on 2015-06-06.
 */
public abstract class DialogClosingAbstractPanel extends OkCancelListenerAbstractPanel{
    private DialogCloseListener dialogCloseListener;

    @Override
    public void okClicked() {
        closeDialog();
    }

    @Override
    public void cancelClicked() {
        closeDialog();
    }

    private void closeDialog(){
        if(dialogCloseListener!=null)
            dialogCloseListener.closeDialog();
    }

    public void setDialogCloseListener(DialogCloseListener dialogCloseListener) {
        this.dialogCloseListener = dialogCloseListener;
    }
}
