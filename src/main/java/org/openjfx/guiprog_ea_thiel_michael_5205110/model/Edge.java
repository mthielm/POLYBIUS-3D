package org.openjfx.guiprog_ea_thiel_michael_5205110.model;

import org.openjfx.guiprog_ea_thiel_michael_5205110.util.Literals;

/**
 * Represents an edge of a polygon defined by two vertices.
 *
 * @author mthiel
 */
public class Edge
{
    private Vertex start;
    private Vertex end;
    private double length;

    public Edge(Vertex start, Vertex end)
    {
        this.start = start;
        this.end = end;
        this.length = calculateLength(start,end);
    }

    private double calculateLength(Vertex v1, Vertex v2)
    {
        double x = v1.getX() - v2.getX();
        double y = v1.getY() - v2.getY();
        double z = v1.getZ() - v2.getZ();

        return Math.sqrt(x * x + y * y + z * z);
    }

    public boolean isValid()
    {
        return !start.equals(end);
    }

    public Vertex getStart()
    {
        return start;
    }

    public Vertex getEnd()
    {
        return end;
    }

    public void setStart(Vertex start)
    {
        this.start = start;
    }

    public void setEnd(Vertex end)
    {
        this.end = end;
    }

    public double getLength()
    {
        return length;
    }

    public void setLength(double length)
    {
        this.length = length;
    }

    @Override
    public String toString()
    {
        return start +
                "-->" + end +
                ", length=" + length + '\n' + Literals.INDENT;
    }
}
