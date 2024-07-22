package org.openjfx.guiprog_ea_thiel_michael_5205110.control;

import org.openjfx.guiprog_ea_thiel_michael_5205110.model.Polygon;
import org.openjfx.guiprog_ea_thiel_michael_5205110.util.Constants;
import org.openjfx.guiprog_ea_thiel_michael_5205110.util.Literals;
import org.openjfx.guiprog_ea_thiel_michael_5205110.view.Console;

public class SurfaceCalculatorThread extends Thread {
    private double surfaceArea = 0;

    @Override
    public void run() {
        //Console.log("Surface calculator thread started");
        int iterations = 1;
        while (true) {
            Polygon polygon = PolygonListManager.getInstance().getAndRemovePolygon();
            // Stop when we see the "end" polygon
            if (polygon.getVertices().isEmpty()) {
                Console.log("Finished surface calculation: " + surfaceArea + Literals.AREA_UNIT);
                break;
            }
            // calculate surface area
            PolygonController.getInstance().calculateArea(polygon);
            surfaceArea += polygon.getArea();
            //Console.log("Calculated Polygon Number" + iterations);
            iterations++;
        }
    }

    public double getSurfaceArea() {
        return surfaceArea;
    }
}