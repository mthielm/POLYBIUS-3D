package org.openjfx.guiprog_ea_thiel_michael_5205110.control;

import org.openjfx.guiprog_ea_thiel_michael_5205110.model.Polygon;
import org.openjfx.guiprog_ea_thiel_michael_5205110.model.Vector;
import org.openjfx.guiprog_ea_thiel_michael_5205110.model.Vertex;
import org.openjfx.guiprog_ea_thiel_michael_5205110.view.Console;

import java.util.List;

public class PolygonController
{
    private static final PolygonController instance = new PolygonController();

    private PolygonController()
    {
        //Console.log("Polygon Controller wurde erstellt.");
    }

    public void calculateArea(Polygon polygon)
    {
        double area = 0.0;
        List<Vertex> vertices = polygon.getVertices();
        if (vertices.size() >= 3)
        {
            Vector v0 = new Vector(vertices.get(0));
            Vector v1 = new Vector(vertices.get(1));
            Vector v2 = new Vector(vertices.get(2));
            Vector ab = v1.subtract(v0);
            Vector ac = v2.subtract(v0);
            area = 0.5 * ab.crossProduct(ac).magnitude();
            polygon.setArea(area);
        }
    }

    public static PolygonController getInstance()
    {
        return instance;
    }

}
