package org.petri.nets.gui.graph;

import org.jgraph.graph.*;
import org.jgraph.plaf.basic.BasicGraphUI;
import org.petri.nets.gui.dialog.GlobalDialogsHandler;
import org.petri.nets.gui.popup.BackgroundPopupMenu;
import org.petri.nets.gui.popup.MultipleSelectionsPopupMenu;
import org.petri.nets.gui.popup.PlacePopupMenu;
import org.petri.nets.service.GraphService;
import org.petri.nets.service.GraphServiceImpl;
import org.petri.nets.synhronize.SynchronizePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

public class PetriNetGraphUI extends BasicGraphUI {
    private PlacePopupMenu placePopupMenu;
    private BackgroundPopupMenu backgroundPopupMenu;
    private MultipleSelectionsPopupMenu multipleSelectionsPopupMenu;
    private GraphService graphService;
    private GlobalDialogsHandler globalDialogsHandler;
    private CellEditHandler cellEditHandler;

    public PetriNetGraphUI(GraphService graphService, SynchronizePanel synchronizePanel) { // maybe graphService
        this.graphService = new GraphServiceImpl(graphService.getDomainModel(), synchronizePanel);
        backgroundPopupMenu = new BackgroundPopupMenu(graphService);
        placePopupMenu = new PlacePopupMenu(graphService);
        multipleSelectionsPopupMenu = new MultipleSelectionsPopupMenu(graphService);
    }

    @Override
    public CellHandle createHandle(GraphContext context) {
        CellHandle rootHandle = super.createHandle(context);
        return new DecoratorHandle(rootHandle);
    }

    @Override
    protected KeyListener createKeyListener() {
        return new CustomKeyHandler();
    }

    public CellView getLastFocus() {
        return lastFocus;
    }

    public class DecoratorHandle implements CellHandle, Serializable {
        private final CellHandle cellHandle;

        public DecoratorHandle(CellHandle cellHandle) {
            this.cellHandle = cellHandle;
        }

        @Override
        public void paint(Graphics g) {
            cellHandle.paint(g);
        }

        @Override
        public void overlay(Graphics g) {
            cellHandle.overlay(g);
        }

        @Override
        public void mouseMoved(MouseEvent event) {
            cellHandle.mouseMoved(event);
        }

        @Override
        public void mousePressed(MouseEvent event) {
            if (!SwingUtilities.isLeftMouseButton(event)) {
                showPopupMenu(event);
            }
            cellHandle.mousePressed(event);
        }

        @Override
        public void mouseDragged(MouseEvent event) {
            cellHandle.mouseDragged(event);
        }

        @Override
        public void mouseReleased(MouseEvent event) {
            cellHandle.mouseReleased(event);
        }
    }

    private void showPopupMenu(MouseEvent event) {
        if (graph.getSelectionCells().length > 1) {
            multipleSelectionsPopupMenu.show(event.getComponent(), event.getX(), event.getY());
        } else if (focus instanceof VertexView) {
            placePopupMenu.setFocus(focus);
            placePopupMenu.show(event.getComponent(), event.getX(), event.getY());
        } else if (focus == null)
            backgroundPopupMenu.show(event.getComponent(), event.getX(), event.getY());

    }


    @Override
    protected MouseListener createMouseListener() {
        return new CustomMouseHandler();
    }

    private class CustomMouseHandler extends MouseHandler {

        @Override
        public void mousePressed(MouseEvent e) {
            handler = null;
            if (!e.isConsumed() && graph.isEnabled()) {
                graph.requestFocus();
                int s = graph.getTolerance();
                Rectangle2D r = graph.fromScreen(new Rectangle2D.Double(e
                        .getX()
                        - s, e.getY() - s, 2 * s, 2 * s));
                lastFocus = focus;
                focus = (focus != null && focus.intersects(graph, r)) ? focus
                        : null;
                cell = graph.getNextSelectableViewAt(focus, e.getX(), e.getY());
                if (focus == null)
                    focus = cell;
                completeEditing();
                boolean isForceMarquee = isForceMarqueeEvent(e);
                if (!isForceMarquee) {
                    if (e.getClickCount() == graph.getEditClickCount() && focus != null) {
                        e.consume();
                        cellEditHandler.editCell();
                        cell = null;
                    } else if (!isToggleSelectionEvent(e)) {
                        if (handle != null) {
                            handle.mousePressed(e);
                            handler = handle;
                        } else if (!SwingUtilities.isLeftMouseButton(e)) {
                            showPopupMenu(e);
                            e.consume();
                        }
                        // Immediate Selection
                        if (!e.isConsumed() && cell != null
                                && !graph.isCellSelected(cell.getCell())) {
                            selectCellForEvent(cell.getCell(), e);
                            focus = cell;
                            if (handle != null) {
                                handle.mousePressed(e);
                                handler = handle;
                            }
                            e.consume();
                            cell = null;
                        }
                    }
                }
                // Marquee Selection
                if (!e.isConsumed()
                        && marquee != null
                        && (!isToggleSelectionEvent(e) || focus == null || isForceMarquee)) {
                    marquee.mousePressed(e);
                    handler = marquee;
                }
            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            autoscroll(graph, e.getPoint());
            if (graph.isEnabled() && SwingUtilities.isLeftMouseButton(e)) {
                if (handler != null && handler == marquee)
                    marquee.mouseDragged(e);
                else if (handler == null && !isEditing(graph) && focus != null) {
                    if (!graph.isCellSelected(focus.getCell())) {
                        selectCellForEvent(focus.getCell(), e);
                        cell = null;
                    }
                    if (handle != null)
                        handle.mousePressed(e);
                    handler = handle;
                }
                if (handle != null && handler == handle)
                    handle.mouseDragged(e);
            }
        }
    }

    private class CustomKeyHandler extends KeyHandler {

        @Override
        public void keyPressed(KeyEvent e) {
            if (graph != null && graph.hasFocus() && graph.isEnabled()) {
                KeyStroke keyStroke = KeyStroke.getKeyStroke(e.getKeyCode(), e
                        .getModifiers());

                if (graph.getConditionForKeyStroke(keyStroke) == JComponent.WHEN_FOCUSED) {
                    ActionListener listener = graph
                            .getActionForKeyStroke(keyStroke);

                    if (listener instanceof Action)
                        repeatKeyAction = (Action) listener;
                    else
                        repeatKeyAction = null;
                } else {
                    repeatKeyAction = null;
                    if (keyStroke.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        if (marquee != null)
                            marquee.mouseReleased(null);
                        if (mouseListener != null)
                            mouseListener.mouseReleased(null);
                        updateHandle();
                        graph.repaint();
                    } else if (keyStroke.getKeyCode() == KeyEvent.VK_DELETE && focus != null) {
                        graphService.removeFromGraph(graphService.getSelectedCells());
                    } else if (keyStroke.getKeyCode() == KeyEvent.VK_Z) {
                        int mods = keyStroke.getModifiers();
                        if ((mods & InputEvent.CTRL_MASK) != 0)
                            System.out.println("Implement undo action!"); // TODO implement UNDO
                    }
                }
                if (isKeyDown && repeatKeyAction != null) {
                    repeatKeyAction.actionPerformed(new ActionEvent(graph,
                            ActionEvent.ACTION_PERFORMED, ""));
                    e.consume();
                } else
                    isKeyDown = true;
            }
        }
    }

    private class CellEditHandler {

        private void editCell(){

        }
    }
}
