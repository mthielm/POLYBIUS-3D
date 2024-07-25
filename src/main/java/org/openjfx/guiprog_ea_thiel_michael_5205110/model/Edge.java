package org.openjfx.guiprog_ea_thiel_michael_5205110.model;

import org.openjfx.guiprog_ea_thiel_michael_5205110.util.Literals;

/**
 * Represents an edge of a polygon defined by two vertices.
 *
 * @author mthiel
 */
public class Edge
{
    /** The start vertex of the edge. */
    private final Vertex start;

    /** The end vertex of the edge. */
    private final Vertex end;

    /**
     * Default constructor.
     *
     * @param start The start vertex of the edge.
     * @param end  The end vertex of the edge.
     * @Precondition: start != null && end != null.
     * @Postcondition: this.start == start && this.end == end.
     */
    public Edge(Vertex start, Vertex end)
    {
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the Edge as a string representation.
     * Overrides the default toString method.
     *
     * @return The Edge as a string representation.
     * @Precondition: true.
     * @Postcondition: result != null.
     */
    @Override
    public String toString()
    {
        return start + Literals.ARROW + end + Literals.NEW_LINE + Literals.INDENT;
    }
}