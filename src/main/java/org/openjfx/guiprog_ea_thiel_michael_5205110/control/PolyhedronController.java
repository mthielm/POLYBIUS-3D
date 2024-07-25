package org.openjfx.guiprog_ea_thiel_michael_5205110.control;

import org.openjfx.guiprog_ea_thiel_michael_5205110.model.Polygon;
import org.openjfx.guiprog_ea_thiel_michael_5205110.model.Polyhedron;
import org.openjfx.guiprog_ea_thiel_michael_5205110.model.Vector;
import org.openjfx.guiprog_ea_thiel_michael_5205110.model.Vertex;
import org.openjfx.guiprog_ea_thiel_michael_5205110.util.Constants;
import org.openjfx.guiprog_ea_thiel_michael_5205110.util.Literals;
import org.openjfx.guiprog_ea_thiel_michael_5205110.view.Console;

import java.util.List;

/**
 * Controller class for polyhedrons. Provides methods to calculate the surface
 * area and volume of a polyhedron.
 * Also provides a method to sort the polygons of a polyhedron by their area.
 * The class is implemented as a singleton.
 *
 * @author mthiel
 *
 * @see Polyhedron
 */
public class PolyhedronController
{
    /** The singleton instance of the class. */
    private static final PolyhedronController instance =
            new PolyhedronController();

    /**
     * Default private Constructor.
     *
     * @Precondition: -
     * @Postcondition: The instance of the class is initialized.
     */
    private PolyhedronController()
    {}

    /**
     * Returns the singleton instance of the class.
     *
     * @return The singleton instance of the class.
     * @Postcondition: The instance of the class is returned.
     * @Precondition: -
     */
    public static PolyhedronController getInstance()
    {
        return instance;
    }

    /**
     * Calculates the volume of a given polyhedron.
     *
     * @param polyhedron The polyhedron for which the volume is to be calculated.
     * @Precondition: The polyhedron is not null.
     * @Postcondition: The volume of the polyhedron is calculated and printed to the console.
     */
    public void calculateVolume(Polyhedron polyhedron)
    {
        List<Polygon> polygons = polyhedron.getPolygons();
        {
            double volume = Constants.DEFAULT_VOLUME;
            for (Polygon polygon : polygons)
            {
                List<Vertex> vertices = polygon.getVertices();
                if (vertices.size() >= Constants.THREE)
                {
                    Vector vector0 =
                            new Vector(vertices.get(Constants.ZERO).x(),
                                    vertices.get(Constants.ZERO).y(),
                                    vertices.get(Constants.ZERO).z());
                    Vector vector1 =
                            new Vector(vertices.get(Constants.ONE).x(),
                                    vertices.get(Constants.ONE).y(),
                                    vertices.get(Constants.ONE).z());
                    Vector vector2 =
                            new Vector(vertices.get(Constants.TWO).x(),
                                    vertices.get(Constants.TWO).y(),
                                    vertices.get(Constants.TWO).z());

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