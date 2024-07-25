package org.openjfx.guiprog_ea_thiel_michael_5205110.control;

import org.openjfx.guiprog_ea_thiel_michael_5205110.model.Polygon;
import org.openjfx.guiprog_ea_thiel_michael_5205110.model.Vector;
import org.openjfx.guiprog_ea_thiel_michael_5205110.model.Vertex;
import org.openjfx.guiprog_ea_thiel_michael_5205110.util.Constants;

import java.util.List;

/**
 * Controller class for polygons. Provides methods to calculate the surface
 * area of a polygon.
 * The class is implemented as a singleton.
 *
 * @author mthiel
 *
 * @see Polygon
 */
public class PolygonController
{
    /** The singleton instance of the class. */
    private static final PolygonController instance = new PolygonController();

    /**
     * Default private Constructor.
     *
     * @Postcondition: The singleton instance of the class is created.
     * @Praecondition: -
     */
    private PolygonController()
    {}

    /**
     * Returns the singleton instance of the class.
     *
     * @return The singleton instance of the class.
     * @Postcondition: The singleton instance of the class is returned.
     * @Praecondition: -
     */
    public static PolygonController getInstance()
    {
        return instance;
    }

    /**
     * Calculates the area of a polygon.
     *
     * @param polygon The polygon for which the area is to be calculated.
     * @Postcondition: The area of the polygon is calculated and set.
     * @Praecondition: The polygon is not null.
     */
    public void calculateArea(Polygon polygon)
    {
        double area;
        List<Vertex> vertices = polygon.getVertices();
        if (vertices.size() >= Constants.THREE)
        {
            Vector v0 = new Vector(vertices.get(Constants.ZERO));
            Vector v1 = new Vector(vertices.get(Constants.ONE));
            Vector v2 = new Vector(vertices.get(Constants.TWO));
            Vector ab = v1.subtract(v0);
            Vector ac = v2.subtract(v0);
            area = Constants.ZERO_FIVE * ab.crossProduct(ac).magnitude();
            polygon.setArea(area);
        }
    }
}