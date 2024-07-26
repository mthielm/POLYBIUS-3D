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
 * @author mthiel
 *
 * @see List
 * @see LinkedList
 */
public class PolygonStack {
    /** The singleton instance of the class. */
    private static final PolygonStack instance = new PolygonStack();

    /** The list of polygons. */
    private final List<Polygon> list = new LinkedList<>();

    /**
     * Default private Constructor.
     *
     * @Postcondition: The list is initialized as a new LinkedList.
     * @Precondition: -
     */
    private PolygonStack(){}

    /**
     * Returns the singleton instance of the class.
     *
     * @return The singleton instance of the class.
     * @Precondition: -
     * @Postcondition: The singleton instance of the class is returned
     */
    public static synchronized PolygonStack getInstance() {
        return instance;
    }

    /**
     * Adds a polygon to the list.
     *
     * @param polygon The polygon to be added.
     * @Precondition: The polygon is not null.
     * @Postcondition: The polygon is added to the list.
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
     * @Precondition: The list is not empty.
     * @Postcondition: The first polygon from the list is removed and returned.
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