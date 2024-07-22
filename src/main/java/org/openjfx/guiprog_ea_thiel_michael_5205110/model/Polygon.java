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
    /**
     * The list of vertices of the polygon.
     */
    private List<Vertex> vertices;

    /**
     * The list of edges of the polygon.
     */
    private List<Edge> edges;

    /**
     * The normal of the polygon.
     */
    private Vector normal;

    /**
     * The area of the polygon.
     */
    private double area;

    /**
     * Default constructor.
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
     */
    public void addVertex(Vertex vertex)
    {
        vertices.add(vertex);
    }

    /**
     * Adds an edge to the polygon.
     *
     * @param edge The edge to add.
     */
    public void addEdge(Edge edge)
    {
        edges.add(edge);
    }

    /**
     * Getter for the list of vertices.
     *
     * @return The list of vertices.
     */
    public List<Vertex> getVertices()
    {
        return vertices;
    }

    /**
     * Setter for the list of vertices.
     *
     * @param vertices The list of vertices.
     */
    public void setVertices(List<Vertex> vertices)
    {
        this.vertices = vertices;
    }

    /**
     * Getter for the list of edges.
     *
     * @return The list of edges.
     */
    public List<Edge> getEdges()
    {
        return edges;
    }

    /**
     * Setter for the list of edges.
     *
     * @param edges The list of edges.
     */
    public void setEdges(List<Edge> edges)
    {
        this.edges = edges;
    }

    /**
     * Getter for the normal of the polygon.
     *
     * @return The normal of the polygon.
     */
    public Vector getNormal()
    {
        return normal;
    }

    /**
     * Setter for the normal of the polygon.
     *
     * @param normal The normal of the polygon.
     */
    public void setNormal(Vector normal)
    {
        this.normal = normal;
    }

    /**
     * Getter for the area of the polygon.
     *
     * @return The area of the polygon.
     */
    public double getArea()
    {
        return area;
    }

    /**
     * Setter for the area of the polygon.
     *
     * @param area The area of the polygon.
     */
    public void setArea(double area)
    {
        this.area = area;
    }

    /**
     * Returns the polygon as a string.
     *
     * @return The polygon as a string.
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