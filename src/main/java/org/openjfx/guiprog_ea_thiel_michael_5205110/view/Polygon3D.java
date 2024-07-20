package org.openjfx.guiprog_ea_thiel_michael_5205110.view;

import javafx.scene.shape.TriangleMesh;

public class Polygon3D extends TriangleMesh
{
    private static final Polygon3D instance = new Polygon3D();

/*    private float[] points;
    private float[] texCoords;
    private int[] faces;*/

    public void setupMesh(int[] points, int[] texCoords, int[] faces)
    {
        float[] floatPoints = new float[points.length];
        for (int i = 0; i < points.length; i++) {
            floatPoints[i] = (float) points[i];
        }

        float[] floatTexCoords = new float[texCoords.length];
        for (int i = 0; i < texCoords.length; i++) {
            floatTexCoords[i] = (float) texCoords[i];
        }

        this.getPoints().setAll(floatPoints);
        this.getTexCoords().setAll(floatTexCoords);
        this.getFaces().setAll(faces);
    }

    public void setupMesh(float[] points, float[] texCoords, int[] faces)
    {
        Console.log("Drawing Mesh...");
        this.getPoints().setAll(points);
        this.getTexCoords().setAll(texCoords);
        this.getFaces().setAll(faces);
    }


    public static Polygon3D getInstance()
    {
        return instance;
    }
}
