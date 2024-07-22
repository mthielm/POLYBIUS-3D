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
    /**
     * The list of polygons that make up the polyhedron.
     */
    private List<Polygon> polygons;

    /**
     * The number of polygons in the polyhedron.
     */
    int numPolygons = Constants.ZERO;

    /**
     * The surface area of the polyhedron.
     */
    double surfaceArea = Constants.ZERO;

    /**
     * Default constructor.
     */
    public Polyhedron()
    {
        this.polygons = new ArrayList<>();
    }

    /**
     * Adds a polygon to the polyhedron.
     *
     * @param polygon The polygon to add.
     */
    public void addPolygon(Polygon polygon)
    {
        polygons.add(polygon);
    }

    /**
     * Getter for the list of polygons.
     *
     * @return The list of polygons in the polyhedron.
     */
    public List<Polygon> getPolygons()
    {
        return polygons;
    }

    /**
     * Setter for the list of polygons.
     *
     * @param polygons The list of polygons to set.
     */
    public void setPolygons(List<Polygon> polygons)
    {
        this.polygons = polygons;
    }

    /**
     * Getter for the surface area.
     *
     * @return The surface area of the polyhedron.
     */
    public double getSurfaceArea()
    {
        return surfaceArea;
    }

    /**
     * Setter for the surface area.
     *
     * @param surfaceArea The surface area to set.
     */
    public void setSurfaceArea(double surfaceArea)
    {
        this.surfaceArea = surfaceArea;
    }

    /**
     * Setter for the number of polygons.
     *
     * @param numPolygons The number of polygons to set.
     */
    public void setNumPolygons(int numPolygons)
    {
        this.numPolygons = numPolygons;
    }

    /**
     * Returns a string representation of the polyhedron.
     *
     * @return The polyhedron as a string.
     */
    @Override
    public String toString() {
        return Literals.POLYHEDRON_PREFIX +
               Literals.POLYGONS_PREFIX + polygons +
               Literals.SURFACE_AREA_PREFIX + surfaceArea + Literals.AREA_UNIT +
               Literals.POLYHEDRON_SUFFIX;
    }
}