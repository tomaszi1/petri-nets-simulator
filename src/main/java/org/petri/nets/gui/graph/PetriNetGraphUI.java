package org.petri.nets.gui.graph;

import org.jgraph.graph.VertexView;
import org.jgraph.plaf.basic.BasicGraphUI;
import org.petri.nets.gui.PlacePopupMenu;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PetriNetGraphUI extends BasicGraphUI {
    private PlacePopupMenu placePopupMenu = new PlacePopupMenu();
    private BackgroundPopupMenu backgroundPopupMenu = new BackgroundPopupMenu();

    @Override
    protected MouseListener createMouseListener() {
        return new CustomMouseHandler();
    }

    private class CustomMouseHandler extends MouseHandler {
        @Override
        public void mousePressed(MouseEvent e) {
            handleEvent(e);
            super.mousePressed(e);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            handleEvent(e);
            super.mouseReleased(e);
        }

        private void handleEvent(MouseEvent e) {
            if (e != null && !e.isConsumed() && e.isPopupTrigger()) {
                if(focus instanceof VertexView)
                    showPopupMenu(e);
                e.consume();
            }
        }

        private void showPopupMenu(MouseEvent e) {
            if(focus instanceof VertexView)
                placePopupMenu.show(e.getComponent(), e.getX(), e.getY()); // TODO show popup
        }
    }
}
