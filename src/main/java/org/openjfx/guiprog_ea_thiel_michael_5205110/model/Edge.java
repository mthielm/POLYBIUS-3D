package org.openjfx.guiprog_ea_thiel_michael_5205110.model;

import org.openjfx.guiprog_ea_thiel_michael_5205110.util.Literals;

/**
 * Represents an edge of a polygon defined by two vertices.
 *
 * @author mthiel
 */
public class Edge
{
    /**
     * The start vertex of the edge.
     */
    private Vertex start;

    /**
     * The end vertex of the edge.
     */
    private Vertex end;

    /**
     * Default constructor.
     *
     * @param start The start vertex of the edge.
     * @param end  The end vertex of the edge.
     */
    public Edge(Vertex start, Vertex end)
    {
        this.start = start;
        this.end = end;
    }

    /**
     * Getter for the start vertex.
     *
     * @return The start vertex of the edge.
     */
    public Vertex getStart()
    {
        return start;
    }

    /**
     * Getter for the end vertex.
     *
     * @return The end vertex of the edge.
     */
    public Vertex getEnd()
    {
        return end;
    }

    /**
     * Setter for the start vertex.
     *
     * @param start The start vertex of the edge.
     */
    public void setStart(Vertex start)
    {
        this.start = start;
    }

    /**
     * Setter for the end vertex.
     *
     * @param end The end vertex of the edge.
     */
    public void setEnd(Vertex end)
    {
        this.end = end;
    }

    /**
     * Returns the Edge as a string representation.
     *
     * @return The Edge as a string representation.
     */
    @Override
    public String toString()
    {
        return start + Literals.ARROW + end + Literals.NEW_LINE + Literals.INDENT;
    }
}
