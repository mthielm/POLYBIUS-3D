package org.openjfx.guiprog_ea_thiel_michael_5205110.control;

import org.openjfx.guiprog_ea_thiel_michael_5205110.model.Polygon;
import org.openjfx.guiprog_ea_thiel_michael_5205110.model.Polyhedron;
import org.openjfx.guiprog_ea_thiel_michael_5205110.model.Vector;
import org.openjfx.guiprog_ea_thiel_michael_5205110.model.Vertex;
import org.openjfx.guiprog_ea_thiel_michael_5205110.util.Constants;
import org.openjfx.guiprog_ea_thiel_michael_5205110.util.Literals;
import org.openjfx.guiprog_ea_thiel_michael_5205110.view.Console;

import java.util.Comparator;
import java.util.List;

/**
 * Controller class for polyhedrons. Provides methods to calculate the surface
 * area and volume of a polyhedron.
 * Also provides a method to sort the polygons of a polyhedron by their area.
 * The class is implemented as a singleton.
 *
 * @see Polyhedron
 *
 * @author mthiel
 */
public class PolyhedronController
{
    /**
     * The singleton instance of the class.
     */
    private static final PolyhedronController instance =
            new PolyhedronController();

    /**
     * Default private Constructor.
     */
    private PolyhedronController()
    {}

    /**
     * Returns the singleton instance of the class.
     *
     * @return The singleton instance of the class.
     */
    public static PolyhedronController getInstance()
    {
        return instance;
    }

    /**
     * Calculates the surface area of a given polyhedron.
     *
     * @param polyhedron The polyhedron for which the surface area is to be
     *                   calculated.
     */
    public void calculateSurface(Polyhedron polyhedron)
    {
        List<Polygon> polygons = polyhedron.getPolygons();

        for (Polygon polygon : polygons)
        {
            PolygonController.getInstance().calculateArea(polygon);
        }

        double tempArea = Constants.DEFAULT_AREA;

        for (Polygon polygon : polygons)
        {
            tempArea += polygon.getArea();
        }
        polyhedron.setSurfaceArea(tempArea);
    }


    /**
     * Sorts the polygons of a polyhedron by their area.
     *
     * @param polyhedron The polyhedron to be sorted.
     */
    public void sortByArea(Polyhedron polyhedron)
    {
        Console.log(Literals.SORT_BEFORE);
        List<Polygon> polygons = polyhedron.getPolygons();
        Console.log(polygons.toString());
        polygons.sort(new Comparator<Polygon>()
        {
            @Override
            public int compare(Polygon p1, Polygon p2)
            {
                return Double.compare(p1.getArea(), p2.getArea());
            }
        });
        Console.log(Literals.SORT_AFTER);
        Console.log(polygons.toString());
    }

    /**
     * Calculates the volume of a given polyhedron.
     *
     * @param polyhedron The polyhedron for which the volume is to be calculated.
     */
    public void calculateVolume(Polyhedron polyhedron)
    {
        List<Polygon> polygons = polyhedron.getPolygons();
        {
            double volume = Constants.DEFAULT_VOLUME;
            for (Polygon polygon : polygons)
            {
                Vector normal = polygon.getNormal();
                List<Vertex> vertices = polygon.getVertices();
                if (vertices.size() >= Constants.THREE)
                {
                    Vector vector0 =
                            new Vector(vertices.get(Constants.ZERO).getX(),
                                    vertices.get(Constants.ZERO).getY(),
                                    vertices.get(Constants.ZERO).getZ());
                    Vector vector1 =
                            new Vector(vertices.get(Constants.ONE).getX(),
                                    vertices.get(Constants.ONE).getY(),
                                    vertices.get(Constants.ONE).getZ());
                    Vector vector2 =
                            new Vector(vertices.get(Constants.TWO).getX(),
                                    vertices.get(Constants.TWO).getY(),
                                    vertices.get(Constants.TWO).getZ());

                    Vector crossProduct = vector1.crossProduct(vector2);

                    float dotProduct = vector0.dotProduct(crossProduct);

                    double tempVolume = dotProduct / Constants.SIX_ZERO;
                    volume += tempVolume;
                }
            }
            Console.log(Literals.VOLUME_CALCULATION_COMPLETE +
                    Math.abs(volume) + Literals.VOLUME_UNIT);
        }
    }
}