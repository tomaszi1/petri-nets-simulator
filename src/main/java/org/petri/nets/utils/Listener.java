package org.petri.nets.utils;

/**
 * Created by Tomasz on 2015-06-06.
 */
public interface Listener<T> {
    void publish(T object);
}
