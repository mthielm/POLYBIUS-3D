package org.openjfx.guiprog_ea_thiel_michael_5205110.model;

import org.openjfx.guiprog_ea_thiel_michael_5205110.util.Constants;
import org.openjfx.guiprog_ea_thiel_michael_5205110.util.Literals;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a polygon defined by a list of edges and vertices in 3D space.
 *
 * @author mthiel
 */
public class Polygon
{
    /** The list of vertices of the polygon. */
    private final List<Vertex> vertices;

    /** The list of edges of the polygon. */
    private final List<Edge> edges;

    /** The normal of the polygon. */
    private Vector normal;

    /** The area of the polygon. */
    private double area;

    /**
     * Default constructor.
     *
     * @Precondition: -
     * @Postcondition: A new polygon object has been created.
     */
    public Polygon()
    {
        this.normal = new Vector();
        this.vertices = new ArrayList<>();
        this.edges = new ArrayList<>();
        this.area = Constants.ZERO;
    }

    /**
     * Adds a vertex to the polygon.
     *
     * @param vertex The vertex to add.
     * @Precondition: vertex != null.
     * @Postcondition: The vertex has been added to the polygon.
     */
    public void addVertex(Vertex vertex)
    {
        vertices.add(vertex);
    }

    /**
     * Adds an edge to the polygon.
     *
     * @param edge The edge to add.
     * @Precondition: edge != null.
     * @Postcondition: The edge has been added to the polygon.
     */
    public void addEdge(Edge edge)
    {
        edges.add(edge);
    }

    /**
     * Getter for the list of vertices.
     *
     * @return The list of vertices.
     * @Precondition: -
     * @Postcondition: The list of vertices has been returned.
     */
    public List<Vertex> getVertices()
    {
        return vertices;
    }

    /**
     * Setter for the normal of the polygon.
     *
     * @param normal The normal of the polygon.
     * @Precondition: normal != null.
     * @Postcondition: The normal of the polygon has been set.
     */
    public void setNormal(Vector normal)
    {
        this.normal = normal;
    }

    /**
     * Getter for the area of the polygon.
     *
     * @return The area of the polygon.
     * @Precondition: -
     * @Postcondition: The area of the polygon has been returned.
     */
    public double getArea()
    {
        return area;
    }

    /**
     * Setter for the area of the polygon.
     *
     * @param area The area of the polygon.
     * @Precondition: area >= 0.
     * @Postcondition: The area of the polygon has been set.
     */
    public void setArea(double area)
    {
        this.area = area;
    }

    /**
     * Returns the polygon as a string.
     * Overrides the toString method of the Object class.
     *
     * @return The polygon as a string.
     * @Precondition: -
     * @Postcondition: The polygon has been returned as a string.
     */
    @Override
    public String toString()
    {
        return Literals.POLYGON_PREFIX +
               Literals.VERTICES_PREFIX + vertices +
               Literals.EDGES_PREFIX + edges +
               Literals.NORMAL_PREFIX + normal +
               Literals.AREA_PREFIX + area +
               Literals.POLYGON_SUFFIX;
    }
}