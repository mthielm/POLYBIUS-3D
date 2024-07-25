package org.openjfx.guiprog_ea_thiel_michael_5205110.model;

import org.openjfx.guiprog_ea_thiel_michael_5205110.util.Literals;

/**
 * Represents a vertex in 3D space.
 *
 * @param x The x-coordinate of the vertex.
 * @param y The y-coordinate of the vertex.
 * @param z The z-coordinate of the vertex.
 * @author mthiel
 */
public record Vertex(float x, float y, float z) {
    /**
     * Default constructor.
     *
     * @param x The x-coordinate of the vertex.
     * @param y The y-coordinate of the vertex.
     * @param z The z-coordinate of the vertex.
     * @Precondition: x, y, z are float values.
     * @Postcondition: A new Vertex object is created with the given x, y, z
     * values.
     */
    public Vertex {
    }

    /**
     * Getter for x-coordinate.
     * Overrides the default x method.
     *
     * @return The x-coordinate of the vertex.
     * @Precondition: x is a float value.
     * @Postcondition: The x-coordinate of the vertex is returned.
     */
    @Override
    public float x() {
        return x;
    }

    /**
     * Getter for y-coordinate.
     * Overrides the default y method.
     *
     * @return The y-coordinate of the vertex.
     * @Precondition: y is a float value.
     * @Postcondition: The y-coordinate of the vertex is returned.
     */
    @Override
    public float y() {
        return y;
    }

    /**
     * Getter for z-coordinate.
     * Overrides the default z method.
     *
     * @return The z-coordinate of the vertex.
     * @Precondition: z is a float value.
     * @Postcondition: The z-coordinate of the vertex is returned.
     */
    @Override
    public float z() {
        return z;
    }

    /**
     * Returns a string representation of the vertex.
     * Overrides the default toString method.
     *
     * @return A string representation of the vertex.
     * @Precondition: x, y, z are float values.
     * @Postcondition: A string representation of the vertex is returned.
     */
    @Override
    public String toString() {
        return Literals.OPEN_PARENTHESIS
                + x + Literals.COMMA_X
                + y + Literals.COMMA_Y
                + z + Literals.COMMA_Z +
                Literals.CLOSE_PARENTHESIS;
    }
}