package org.openjfx.guiprog_ea_thiel_michael_5205110.model;

import org.openjfx.guiprog_ea_thiel_michael_5205110.util.Constants;
import org.openjfx.guiprog_ea_thiel_michael_5205110.util.Literals;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a polyhedron defined by a set of polygons.
 *
 * @author mthiel
 */
public class Polyhedron
{
    /** The list of polygons that make up the polyhedron. */
    private final List<Polygon> polygons;

    /** The number of polygons in the polyhedron. */
    int numPolygons = Constants.ZERO;

    /** The surface area of the polyhedron. */
    double surfaceArea = Constants.ZERO;

    /**
     * Default constructor.
     *
     * @Praecondition: -
     * @Postcondition: The list of polygons is initialized.
     */
    public Polyhedron()
    {
        this.polygons = new ArrayList<>();
    }

    /**
     * Adds a polygon to the polyhedron.
     *
     * @param polygon The polygon to add.
     * @Praecondition: polygon != null.
     * @Postcondition: The polygon is added to the list of polygons.
     */
    public void addPolygon(Polygon polygon)
    {
        polygons.add(polygon);
    }

    /**
     * Getter for the list of polygons.
     *
     * @return The list of polygons in the polyhedron.
     * @Praecondition: -
     * @Postcondition: The list of polygons is returned.
     */
    public List<Polygon> getPolygons()
    {
        return polygons;
    }

    /**
     * Setter for the number of polygons.
     *
     * @param numPolygons The number of polygons to set.
     * @Praecondition: numPolygons >= 0.
     * @Postcondition: The number of polygons is set.
     */
    public void setNumPolygons(int numPolygons)
    {
        this.numPolygons = numPolygons;
    }

    /**
     * Returns a string representation of the polyhedron.
     * Overrides the toString method of the Object class.
     *
     * @return The polyhedron as a string.
     * @Praecondition: -
     * @Postcondition: The polyhedron is returned as a string.
     */
    @Override
    public String toString() {
        return Literals.POLYHEDRON_PREFIX +
               Literals.POLYGONS_PREFIX + polygons +
               Literals.SURFACE_AREA_PREFIX + surfaceArea + Literals.AREA_UNIT +
               Literals.POLYHEDRON_SUFFIX;
    }
}