package org.petri.nets.gui.popup;

import org.petri.nets.service.GraphService;

import javax.swing.*;
import java.awt.*;

public class CustomJPopupMenu extends JPopupMenu {
    private Point position;
    private Object focus;

    public Point getPosition() {
        return position;
    }

    @Override
    public void show(Component invoker, int x, int y) {
        position = new Point(x,y);
        super.show(invoker, x, y);
    }

    public Object getFocus() {
        return focus;
    }

    public void setFocus(Object focus) {
        this.focus = focus;
    }
}
