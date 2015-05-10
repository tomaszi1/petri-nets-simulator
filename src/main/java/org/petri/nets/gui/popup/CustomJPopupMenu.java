package org.petri.nets.gui.popup;

import javax.swing.*;
import java.awt.*;

public class CustomJPopupMenu extends JPopupMenu {
    private Point position;

    public Point getPosition() {
        return position;
    }

    @Override
    public void show(Component invoker, int x, int y) {
        position = new Point(x,y);
        super.show(invoker, x, y);
    }
}
