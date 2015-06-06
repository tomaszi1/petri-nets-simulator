package org.petri.nets.gui.panel;

import org.petri.nets.gui.dialog.DialogCloseListener;
import org.petri.nets.utils.Listener;

/**
 * Created by Tomasz on 2015-06-06.
 */
public abstract class PropertyEditorAbstractPanel<T> extends OkCancelListenerAbstractPanel{
    private DialogCloseListener dialogCloseListener;
    private Listener<T> listener;

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

    public void setListener(Listener<T> listener) {
        this.listener = listener;
    }

    public void publishChanges(T changes){
        if(listener!=null)
            listener.publish(changes);
    }
}
