package org.openjfx.guiprog_ea_thiel_michael_5205110.control;

import org.openjfx.guiprog_ea_thiel_michael_5205110.model.Polygon;
import org.openjfx.guiprog_ea_thiel_michael_5205110.util.Constants;
import org.openjfx.guiprog_ea_thiel_michael_5205110.util.Literals;
import org.openjfx.guiprog_ea_thiel_michael_5205110.view.Console;

/**
 * This class is responsible for calculating the surface area of a polygon.
 *
 * @author mthiel
 */
public class SurfaceCalculatorThread extends Thread {
    /** The surface area of the polygon. */
    private double surfaceArea = Constants.ZERO;

    /**
     * The run method of the thread.
     * Overrides the run method of the Thread class.
     *
     * @Precondition: The polygon list is not empty.
     * @Postcondition: The surface area of the polygon is calculated.
     */
    @Override
    public void run() {
        while (true) {
            Polygon polygon = PolygonStack.getInstance().
                                                 getAndRemovePolygon();
            // Stop when we see the "end" polygon
            if (polygon.getVertices().isEmpty()) {
                Console.log(Literals.FINISHED_SURFACE_CALCULATION +
                            surfaceArea + Literals.AREA_UNIT);
                break;
            }
            // calculate surface area
            PolygonController.getInstance().calculateArea(polygon);
            surfaceArea += polygon.getArea();
        }
    }
}