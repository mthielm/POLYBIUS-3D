package org.openjfx.guiprog_ea_thiel_michael_5205110.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a polygon defined by a list of edges and vertices in 3D space.
 *
 * @author mthiel
 */
public class Polygon
{
    private List<Vertex> vertices;
    private List<Edge> edges;
    private Vector normal;
    private double area;

    public Polygon()
    {
        this.normal = new Vector();
        this.vertices = new ArrayList<>();
        this.edges = new ArrayList<>();
        this.area = 0;
    }

    public Polygon(List<Edge> edges, List<Vertex> vertices)
    {
        this.edges = edges;
        this.vertices = vertices;
    }

    public void calculateArea(double e1, double e2, double e3)
    {
        double s = (e1 + e2 + e3)/2;
        this.area = Math.sqrt(s*(s-e1)*(s-e2)*(s-e3));
    }

/*    public void calculateArea(List<Edge> edges)
    {
        double e1 = edges.get(0).getLength();
        double e2 = edges.get(1).getLength();
        double e3 = edges.get(2).getLength();
        double s = (e1 + e2 + e3)/2;
        this.area = Math.sqrt(s*(s-e1)*(s-e2)*(s-e3));
    }*/

    public void addVertex(Vertex vertex)
    {
        vertices.add(vertex);
    }

/*    public void addVertex(Vector vertex)
    {
        vertices.add(vector);
    }*/

    public void addEdge(Edge edge)
    {
        edges.add(edge);
    }

    public List<Vertex> getVertices()
    {
        return vertices;
    }

    public void setVertices(List<Vertex> vertices)
    {
        this.vertices = vertices;
    }

    public List<Edge> getEdges()
    {
        return edges;
    }

    public void setEdges(List<Edge> edges)
    {
        this.edges = edges;
    }

    public Vector getNormal()
    {
        return normal;
    }

    public void setNormal(Vector normal)
    {
        this.normal = normal;
    }

    public double getArea()
    {
        return area;
    }

    public void setArea(double area)
    {
        this.area = area;
    }

    @Override
    public String toString()
    {
        return "Polygon:\n" +
                "\tvertices=" + vertices +
                "\n\tedges=   " + edges +
                "\n\tnormal=" + normal +
                ", area=" + area +
                "}\n";
    }

    /*   public boolean isValid()
    {
        for (Edge edge : edges)
        {
            if (!edge.isValid())
            {
                return false;
            }
        }
        return true;
    }

    public Face getNormal() {
        return normal;
    }

    public void setNormal(Face normal) {
        this.normal = normal;
    }*/
}