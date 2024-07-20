package org.openjfx.guiprog_ea_thiel_michael_5205110.model;

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
    private List<Polygon> polygons;
    double surfaceArea = 0;
    int numPolygons = 0;

    public Polyhedron()
    {
        this.polygons = new ArrayList<>();
    }

    public Polyhedron(Polyhedron polyhedron)
    {
        this.polygons = new ArrayList<>();
    }



    public void calculateSurface()
    {
        for(int i=0; i<polygons.size(); i++)
        {
            surfaceArea += polygons.get(i).getArea();
        }
    }

    public void addPolygon(Polygon polygon)
    {
        polygons.add(polygon);
    }

    public List<Polygon> getPolygons()
    {
        return polygons;
    }

    public void setPolygons(List<Polygon> polygons)
    {
        this.polygons = polygons;
    }

    public double getSurfaceArea()
    {
        return surfaceArea;
    }

    public void setSurfaceArea(double surfaceArea)
    {
        this.surfaceArea = surfaceArea;
    }

    public void setNumPolygons(int numPolygons)
    {
        this.numPolygons = numPolygons;
    }

    @Override
    public String toString() {
        return "Polyhedron{" +
                "polygons=" + polygons +
                ", surfaceArea=" + surfaceArea + Literals.AREA_UNIT +
                '}';
    }
}