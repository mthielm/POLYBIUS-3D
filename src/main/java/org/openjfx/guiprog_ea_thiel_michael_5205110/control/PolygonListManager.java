package org.openjfx.guiprog_ea_thiel_michael_5205110.control;

import org.openjfx.guiprog_ea_thiel_michael_5205110.model.Polygon;

import java.util.LinkedList;
import java.util.List;

public class PolygonListManager {
    private static final PolygonListManager instance = new PolygonListManager();
    private final List<Polygon> list;

    private PolygonListManager() {
        list = new LinkedList<>();
    }

    public static synchronized PolygonListManager getInstance() {
        return instance;
    }

    public synchronized void addPolygon(Polygon polygon) {
        list.add(polygon);
        //System.out.println("Polygon added to list");
        notifyAll();  // Notify any waiting threads that a new polygon is available
    }

    public synchronized Polygon getAndRemovePolygon() {
        while (list.isEmpty()) {
            try {
                wait();  // Wait for a new polygon to be added
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //System.out.println("Items remaining: " + list.size());
        return list.remove(0);
    }
}