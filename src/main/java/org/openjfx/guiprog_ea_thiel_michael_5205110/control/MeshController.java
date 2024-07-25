package org.openjfx.guiprog_ea_thiel_michael_5205110.control;

import org.openjfx.guiprog_ea_thiel_michael_5205110.model.Polygon;
import org.openjfx.guiprog_ea_thiel_michael_5205110.model.Polyhedron;
import org.openjfx.guiprog_ea_thiel_michael_5205110.model.Vertex;
import org.openjfx.guiprog_ea_thiel_michael_5205110.util.Constants;
import org.openjfx.guiprog_ea_thiel_michael_5205110.util.Literals;
import org.openjfx.guiprog_ea_thiel_michael_5205110.view.Console;

/**
 * This class is responsible for mapping the points of the polyhedron to a
 * float array, creating the faces of the polyhedron, creating the textures of
 * the polyhedron, mapping the faces and textures to an int array and finding
 * the origin of the polyhedron.
 */
public class MeshController {
    public float[] mapPoints(Polyhedron polyhedron) {
        Console.log(Literals.MAPPING_POINTS);
        float[] points =
                new float[polyhedron.getPolygons().size() * Constants.NINE];

        for (int i = Constants.ZERO; i < polyhedron.getPolygons().size(); i++) {
            Polygon polygon = polyhedron.getPolygons().get(i);

            for (int j = Constants.ZERO; j < Constants.THREE; j++) {
                Vertex vertex = polygon.getVertices().get(j);

                points[(i * Constants.NINE) + (j * Constants.THREE)] =
                        polygon.getVertices().get(j).getX() *
                        Constants.ONEHUNDRED;
                points[(i * Constants.NINE) + (j * Constants.THREE) +
                        Constants.ONE] = polygon.getVertices().get(j).getY() *
                        Constants.ONEHUNDRED;
                points[(i * Constants.NINE) + (j * Constants.THREE) +
                        Constants.TWO] = polygon.getVertices().get(j).getZ() *
                        Constants.ONEHUNDRED;
            }
        }
        return points;
    }

    /**
     * Creates the faces of the polyhedron.
     *
     * @param polyhedron The polyhedron to create the faces for.
     * @return The faces of the polyhedron.
     */
    public int[] createFaces(Polyhedron polyhedron) {
        int[] faces = new int[polyhedron.getPolygons().size() * Constants.THREE];
        //Change back to *1

        for (int i = Constants.ZERO; i < polyhedron.getPolygons().size() *
                                         Constants.THREE; i++)
        //Change back to *1
        {
            faces[i] = i;
        }
        return faces;
    }

    /**
     * Creates the textures of the polyhedron.
     *
     * @param polyhedron The polyhedron to create the textures for.
     * @return The textures of the polyhedron.
     */
    public float[] createTextures(Polyhedron polyhedron) {
        float[] textures =
                new float[polyhedron.getPolygons().size() * Constants.THREE];

        for (int i = Constants.ZERO; i < polyhedron.getPolygons().size(); i++) {
            textures[i] = Constants.ZERO;
        }
        return textures;
    }

    /**
     * Maps the faces and textures to an int array.
     *
     * @param faces The faces of the polyhedron.
     * @param textures The textures of the polyhedron.
     * @return The combined faces and textures.
     */
    public int[] mapFaces(int[] faces, float[] textures) {
        int[] combinedFaces = new int[faces.length * Constants.TWO];

        for (int i = Constants.ZERO; i < (faces.length); i++) {
            combinedFaces[i * Constants.TWO] = faces[i];
            combinedFaces[(i * Constants.TWO) + Constants.ONE] =
                    (int) textures[i];
        }

        return combinedFaces;
    }

    /**
     * Finds the origin of the polyhedron.
     *
     * @param points The points of the polyhedron.
     * @return The origin of the polyhedron.
     */
    public double[] findOrigin(float[] points) {
        double sumX = Constants.ZERO;
        double sumY = Constants.ZERO;
        double sumZ = Constants.ZERO;
        int countX = Constants.ZERO;
        int countY = Constants.ZERO;
        int countZ = Constants.ZERO;

        for (int i = Constants.ZERO; i < points.length; i += Constants.THREE) {
            sumX += points[i];
            countX++;
            if (i + Constants.ONE < points.length) {
                sumY += points[i + Constants.ONE];
                countY++;
            }
            if (i + Constants.TWO < points.length) {
                sumZ += points[i + Constants.TWO];
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
