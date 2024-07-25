package org.openjfx.guiprog_ea_thiel_michael_5205110.control;

import org.openjfx.guiprog_ea_thiel_michael_5205110.model.Polygon;
import org.openjfx.guiprog_ea_thiel_michael_5205110.util.Constants;
import org.openjfx.guiprog_ea_thiel_michael_5205110.util.Literals;
import org.openjfx.guiprog_ea_thiel_michael_5205110.view.Console;

/**
 * This class is responsible for calculating the surface area of a polygon.
 */
public class SurfaceCalculatorThread extends Thread {
    /**
     * The surface area of the polygon.
     */
    private double surfaceArea = Constants.ZERO;

    /**
     * The run method of the thread.
     */
    @Override
    public void run() {
        //Console.log("Surface calculator thread started");
        int iterations = Constants.ONE;
        while (true) {
            Polygon polygon = PolygonListManager.getInstance().
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
            //Console.log("Calculated Polygon Number" + iterations);
            iterations++;
        }
    }

    /**
     * Getter for the surface area.
     *
     * @return the surface area
     */
    public double getSurfaceArea() {
        return surfaceArea;
    }
}