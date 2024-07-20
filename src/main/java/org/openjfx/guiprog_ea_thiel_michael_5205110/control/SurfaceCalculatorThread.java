package org.openjfx.guiprog_ea_thiel_michael_5205110.control;

import org.openjfx.guiprog_ea_thiel_michael_5205110.model.Polygon;
import org.openjfx.guiprog_ea_thiel_michael_5205110.util.Constants;
import org.openjfx.guiprog_ea_thiel_michael_5205110.util.Literals;

public class SurfaceCalculatorThread extends Thread {
    private double surfaceArea = 0;

    @Override
    public void run() {
        //System.out.println("Surface calculator thread started");
        int iterations = 1;
        while (true) {
            Polygon polygon = PolygonListManager.getInstance().getAndRemovePolygon();
            // Stop when we see the "end" polygon
            if (polygon.getVertices().isEmpty()) {
                System.out.println("Finished surface calculation: " + surfaceArea + Literals.AREA_UNIT);
                break;
            }
            // calculate surface area
            PolygonController.getInstance().calculateArea(polygon);
            surfaceArea += polygon.getArea();
            //System.out.println("Calculated Polygon Number" + iterations);
            iterations++;
        }
    }

    public double getSurfaceArea() {
        return surfaceArea;
    }
}