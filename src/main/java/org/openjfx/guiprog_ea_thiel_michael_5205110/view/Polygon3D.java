package org.openjfx.guiprog_ea_thiel_michael_5205110.view;

import javafx.scene.shape.TriangleMesh;
import org.openjfx.guiprog_ea_thiel_michael_5205110.util.Literals;

/**
 * This class is a singleton class that represents a 3D polygon.
 * It extends the TriangleMesh class from JavaFX.
 * It is used to draw 3D polygons in the JavaFX scene.
 *
 * @author mthiel
 */
public class Polygon3D extends TriangleMesh {
    /** Singleton instance of the Polygon3D class. */
    private static final Polygon3D instance = new Polygon3D();

    /**
     * Private constructor of the Polygon3D class.
     *
     * @Precondition: -
     * @Postcondition: A new instance of the Polygon3D class is created.
     */
    public void setupMesh(float[] points, float[] texCoords, int[] faces)
    {
        Console.log(Literals.DRAWING_MESH);
        this.getPoints().setAll(points);
        this.getTexCoords().setAll(texCoords);
        this.getFaces().setAll(faces);
    }

    /**
     * Returns the singleton instance of the Polygon3D class.
     *
     * @return The singleton instance of the Polygon3D class.
     * @Precondition: -
     * @Postcondition: The singleton instance of the Polygon3D class is returned.
     */
    public static Polygon3D getInstance()
    {
        return instance;
    }
}