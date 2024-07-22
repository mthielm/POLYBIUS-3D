package org.openjfx.guiprog_ea_thiel_michael_5205110.control;

import org.openjfx.guiprog_ea_thiel_michael_5205110.model.Polygon;
import org.openjfx.guiprog_ea_thiel_michael_5205110.model.Polyhedron;
import org.openjfx.guiprog_ea_thiel_michael_5205110.model.Vertex;
import org.openjfx.guiprog_ea_thiel_michael_5205110.view.Console;

public class MeshController {
    public float[] mapPoints(Polyhedron polyhedron) {
        Console.log("Mapping Points...");
        float[] points = new float[polyhedron.getPolygons().size() * 9];

        for (int i = 0; i < polyhedron.getPolygons().size(); i++) {
            Polygon polygon = polyhedron.getPolygons().get(i);

            for (int j = 0; j < 3; j++) {
                Vertex vertex = polygon.getVertices().get(j);

                points[(i * 9) + (j * 3)] = polygon.getVertices().get(j).getX() * 100;
                points[(i * 9) + (j * 3) + 1] = polygon.getVertices().get(j).getY() * 100;
                points[(i * 9) + (j * 3) + 2] = polygon.getVertices().get(j).getZ() * 100;
            }
        }
        return points;
    }

    public int[] createFaces(Polyhedron polyhedron) {
        int[] faces = new int[polyhedron.getPolygons().size() * 3];//Change back to *1

        for (int i = 0; i < polyhedron.getPolygons().size() * 3; i++)//Change back to *1
        {
            faces[i] = i;
        }
        return faces;
    }

    public float[] createTextures(Polyhedron polyhedron) {
        float[] textures = new float[polyhedron.getPolygons().size() * 3];

        for (int i = 0; i < polyhedron.getPolygons().size(); i++) {
            textures[i] = 0;
        }
        return textures;
    }

    public int[] mapFaces(int[] faces, float[] textures) {
        int[] combinedFaces = new int[faces.length * 2];

        for (int i = 0; i < (faces.length); i++) {
            combinedFaces[i * 2] = faces[i];
            combinedFaces[(i * 2) + 1] = (int) textures[i];
        }

        return combinedFaces;
    }

    public double[] findOrigin(float[] points) {
        double sumX = 0;
        double sumY = 0;
        double sumZ = 0;
        int countX = 0;
        int countY = 0;
        int countZ = 0;

        for (int i = 0; i < points.length; i += 3) {
            sumX += points[i];
            countX++;
            if (i + 1 < points.length) {
                sumY += points[i + 1];
                countY++;
            }
            if (i + 2 < points.length) {
                sumZ += points[i + 2];
                countZ++;
            }
        }

        double originX = sumX / countX;
        double originY = sumY / countY;
        double originZ = sumZ / countZ;

        double[] origin = {originX, originY, originZ};

        return origin;
    }

/*    public double[] findOrigin(float[] points) {
        double x = 0;
        double y = 0;
        double z = 0;
        for (int i = 0; i < points.length; i += 3) {
            x += points[i];
            y += points[i + 1];
            z += points[i + 2];
        }
        x /= points.length / 3;
        y /= points.length / 3;
        z /= points.length / 3;

        double[] origin = {x, y, z};

        return origin;
    }*/
}
