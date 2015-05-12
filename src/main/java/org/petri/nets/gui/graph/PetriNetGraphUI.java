package org.petri.nets.gui.graph;

import javafx.scene.control.Cell;
import org.jgraph.graph.CellHandle;
import org.jgraph.graph.CellView;
import org.jgraph.graph.GraphContext;
import org.jgraph.graph.VertexView;
import org.jgraph.plaf.basic.BasicGraphUI;
import org.petri.nets.gui.popup.BackgroundPopupMenu;
import org.petri.nets.gui.popup.PlacePopupMenu;
import org.petri.nets.model.DomainModel;
import org.petri.nets.model.PetriNet;
import org.petri.nets.service.GraphService;
import org.petri.nets.service.GraphServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

public class PetriNetGraphUI extends BasicGraphUI {
    private PlacePopupMenu placePopupMenu;
    private BackgroundPopupMenu backgroundPopupMenu;

    public PetriNetGraphUI(DomainModel model) { // maybe graphService
        GraphService graphService = new GraphServiceImpl(model);
        backgroundPopupMenu = new BackgroundPopupMenu(graphService);
        placePopupMenu = new PlacePopupMenu(graphService);
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
        if (focus instanceof VertexView) {
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
                boolean isEditable = graph.isGroupsEditable()
                        || (focus != null && focus.isLeaf());
                if (!isForceMarquee) {
                    if (e.getClickCount() == graph.getEditClickCount()
                            && focus != null && isEditable
                            && focus.getParentView() == null
                            && graph.isCellEditable(focus.getCell())
                            && handleEditTrigger(cell.getCell(), e)) {
                        e.consume();
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
                        graphLayoutCache.remove(new Object[]{focus.getCell()});
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
}
