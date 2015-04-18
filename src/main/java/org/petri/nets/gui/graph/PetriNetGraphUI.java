package org.petri.nets.gui.graph;

import org.jgraph.plaf.basic.BasicGraphUI;

import java.awt.event.MouseListener;

public class PetriNetGraphUI extends BasicGraphUI{
    @Override
    protected MouseListener createMouseListener() {
        return new BasicGraphUI.MouseHandler();
    }
}
