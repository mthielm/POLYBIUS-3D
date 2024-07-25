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
 * @see List
 * @see LinkedList
 */
public class PolygonListManager {
    /** The singleton instance of the class. */
    private static final PolygonListManager instance = new PolygonListManager();
    /** The list of polygons. */
    private final List<Polygon> list;

    /**
     * Default private Constructor.
     *
     * @Postcondition: The list is initialized as a new LinkedList.
     * @Praecondition: -
     */
    private PolygonListManager() {
        list = new LinkedList<>();
    }

    /**
     * Returns the singleton instance of the class.
     *
     * @return The singleton instance of the class.
     * @Postcondition: The singleton instance of the class is returned
     * @Praecondition: -
     */
    public static synchronized PolygonListManager getInstance() {
        return instance;
    }

    /**
     * Adds a polygon to the list.
     *
     * @param polygon The polygon to be added.
     * @Postcondition: The polygon is added to the list.
     * @Praecondition: The polygon is not null.
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
     * @Postcondition: The first polygon from the list is removed and returned.
     * @Praecondition: The list is not empty.
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