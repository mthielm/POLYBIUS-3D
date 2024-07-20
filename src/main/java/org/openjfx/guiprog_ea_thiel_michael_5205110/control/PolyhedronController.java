package org.openjfx.guiprog_ea_thiel_michael_5205110.control;

import org.openjfx.guiprog_ea_thiel_michael_5205110.model.Polygon;
import org.openjfx.guiprog_ea_thiel_michael_5205110.model.Polyhedron;
import org.openjfx.guiprog_ea_thiel_michael_5205110.model.Vector;
import org.openjfx.guiprog_ea_thiel_michael_5205110.model.Vertex;
import org.openjfx.guiprog_ea_thiel_michael_5205110.view.Console;

import java.util.Comparator;
import java.util.List;

public class PolyhedronController
{
    private static final PolyhedronController instance = new PolyhedronController();

    private PolyhedronController()
    {
        Console.log("Polyhedron Controller wurde erstellt.");
    }

    public static PolyhedronController getInstance()
    {
        return instance;
    }

    public void calculateSurface(Polyhedron polyhedron)
    {
        List<Polygon> polygons = polyhedron.getPolygons();

        for (Polygon polygon : polygons)
        {
            PolygonController.getInstance().calculateArea(polygon);
        }

        double tempArea = 0;

        for (Polygon polygon : polygons)
        {
            tempArea += polygon.getArea();
        }
        polyhedron.setSurfaceArea(tempArea);
    }


    //Rewrite
    public void sortByArea(Polyhedron polyhedron)
    {
        System.out.println("Vor Sortierung:");
        List<Polygon> polygons = polyhedron.getPolygons();
        Console.log(polygons.toString());
        polygons.sort(new Comparator<Polygon>()
        {
            @Override
            public int compare(Polygon p1, Polygon p2)
            {
                return Double.compare(p1.getArea(), p2.getArea());
            }
        });
        System.out.println("Nach Sortierung:");
        Console.log(polygons.toString());
    }

    //Rewrite
    public void calculateVolume(Polyhedron polyhedron)
    {
        List<Polygon> polygons = polyhedron.getPolygons();
        {
            double volume = 0;
            for (Polygon polygon : polygons)
            {
                Vector normal = polygon.getNormal();
                List<Vertex> vertices = polygon.getVertices();
                if (vertices.size() >= 3)
                {
                    Vertex v0 = vertices.get(0);
                    Vertex v1 = vertices.get(1);
                    Vertex v2 = vertices.get(2);
                    double signedVolume = (v0.getX() * v1.getY() * v2.getZ() + v1.getX() * v2.getY() * v0.getZ() +
                            v2.getX() * v0.getY() * v1.getZ() - v2.getX() * v1.getY() * v0.getZ() -
                            v1.getX() * v0.getY() * v2.getZ() - v0.getX() * v2.getY() * v1.getZ()) / 6.0;
                    volume += signedVolume;
                    //System.out.println(volume);
                }
            }
            Console.log("Finished volume calculation: " + Math.abs(volume) + " cubic units");
        }
    }
}
