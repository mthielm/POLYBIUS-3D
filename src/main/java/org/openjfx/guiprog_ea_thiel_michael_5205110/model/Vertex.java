package org.openjfx.guiprog_ea_thiel_michael_5205110.model;

import org.openjfx.guiprog_ea_thiel_michael_5205110.util.Literals;

/**
 * Represents a vertex in 3D space.
 *
 * @author mthiel
 */
public class Vertex
{
    /**
     * The x-coordinate of the vertex.
     */
    private float x;
    /**
     * The y-coordinate of the vertex.
     */
    private float y;
    /**
     * The z-coordinate of the vertex.
     */
    private float z;

    /**
     * Default constructor.
     *
     * @param x The x-coordinate of the vertex.
     * @param y The y-coordinate of the vertex.
     * @param z The z-coordinate of the vertex.
     */
    public Vertex(float x, float y, float z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Getter for x-coordinate.
     *
     * @return The x-coordinate of the vertex.
     */
    public float getX()
    {
        return x;
    }

    /**
     * Getter for y-coordinate.
     *
     * @return The y-coordinate of the vertex.
     */
    public float getY()
    {
        return y;
    }

    /**
     * Getter for z-coordinate.
     *
     * @return The z-coordinate of the vertex.
     */
    public float getZ()
    {
        return z;
    }

    /**
     * Setter for x-coordinate.
     *
     * @param x The x-coordinate of the vertex.
     */
    public void setX(float x)
    {
        this.x = x;
    }

    /**
     * Setter for y-coordinate.
     *
     * @param y The y-coordinate of the vertex.
     */
    public void setY(float y)
    {
        this.y = y;
    }

    /**
     * Setter for z-coordinate.
     *
     * @param z The z-coordinate of the vertex.
     */
    public void setZ(float z)
    {
        this.z = z;
    }

    /**
     * Returns a string representation of the vertex.
     *
     * @return A string representation of the vertex.
     */
    @Override
    public String toString()
    {
        return Literals.OPEN_PARENTHESIS
                + x + Literals.COMMA_X
                + y + Literals.COMMA_Y
                + z + Literals.COMMA_Z +
                Literals.CLOSE_PARENTHESIS;
    }
}


