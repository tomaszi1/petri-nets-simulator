package org.petri.nets.model.reachability;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Tomasz on 2015-06-12.
 */
public class StateTest {

    @Test
    public void testEquals() throws Exception {
        State a = new State(), b = new State();
        Map<Integer, Integer> marking = Maps.newHashMap();
        marking.put(1, 1);
        marking.put(2, 2);
        marking.put(3, 3);

        Map<Integer, Integer> marking2 = Maps.newHashMap(marking);
        marking2.put(1, -1);

        a.setMarking(marking);
        b.setMarking(marking2);

        assertTrue(a.equals(b));
        assertTrue(b.equals(a));

        marking2.put(4, 4);
        b.setMarking(marking2);
        assertFalse(a.equals(b));
        assertFalse(b.equals(a));

    }
}