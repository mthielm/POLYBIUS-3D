package org.openjfx.guiprog_ea_thiel_michael_5205110.control;

import org.openjfx.guiprog_ea_thiel_michael_5205110.model.Polygon;
import org.openjfx.guiprog_ea_thiel_michael_5205110.util.Constants;

import java.util.LinkedList;
import java.util.List;

/**
 * Manages a list of polygons. The list is used to store polygons that are
 * created by the PolygonCreator-class and consumed by the
 * PolygonConsumer-class.
 * The class is implemented as a singleton.
 *
 * @see Polygon
 */
public class PolygonListManager {
    /**
     * The singleton instance of the class.
     */
    private static final PolygonListManager instance = new PolygonListManager();
    /**
     * The list of polygons.
     */
    private final List<Polygon> list;

    /**
     * Default private Constructor.
     */
    private PolygonListManager() {
        list = new LinkedList<>();
    }

    /**
     * Returns the singleton instance of the class.
     *
     * @return The singleton instance of the class.
     */
    public static synchronized PolygonListManager getInstance() {
        return instance;
    }

    /**
     * Adds a polygon to the list.
     *
     * @param polygon The polygon to be added.
     */
    public synchronized void addPolygon(Polygon polygon) {
        list.add(polygon);
        notifyAll();  // Notify any waiting threads that a new polygon is
                      // available
    }

    /**
     * Removes and returns the first polygon from the list.
     *
     * @return The first polygon from the list.
     */
    public synchronized Polygon getAndRemovePolygon() {
        while (list.isEmpty()) {
            try {
                wait();  // Wait for a new polygon to be added
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return list.remove(Constants.ZERO);
    }
}