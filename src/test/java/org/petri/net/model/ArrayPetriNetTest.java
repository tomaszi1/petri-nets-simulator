package org.petri.net.model;


import org.junit.Test;
import org.petri.nets.model.ArrayPetriNet;
import org.petri.nets.model.PetriNet;
import org.petri.nets.model.PetriNetPropertyAnalyzerImpl;
import org.petri.nets.utils.Utils;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

public class ArrayPetriNetTest {

    @Test
    public void simulationTest() {
        // given
        PetriNet net = new ArrayPetriNet(2, 2);
        net.setPlaceToTransitionArcValue(0, 0, 1);
        net.setTransitionToPlaceArcValue(0, 1, 1);
        net.setMarking(Arrays.asList(1, 0));

        // do
        List<Integer> possibleTransitions = net.getAliveTransitions();
        List<Integer> transitionsDone = net.doTransitions(possibleTransitions);
        boolean conservative = new PetriNetPropertyAnalyzerImpl().isConservative(net);

        // verify
        assertTrue(possibleTransitions.size() == 1);
        assertArrayEquals(new int[]{0, 1}, Utils.toArray(net.getMarking()));
        assertArrayEquals(new int[]{0}, Utils.toArray(transitionsDone));
        assertTrue(conservative);
    }
}