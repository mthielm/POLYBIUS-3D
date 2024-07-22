package org.openjfx.guiprog_ea_thiel_michael_5205110.model;

import org.openjfx.guiprog_ea_thiel_michael_5205110.util.Constants;
import org.openjfx.guiprog_ea_thiel_michael_5205110.util.Literals;

/**
 * Represents a vector in 3D space and provides some operations.
 *
 * @author mthiel
 */
public class Vector
{
    /**
     * The x-coordinate of the vector.
     */
    private float x;
    /**
     * The y-coordinate of the vector.
     */
    private float y;
    /**
     * The z-coordinate of the vector.
     */
    private float z;

    /**
     * Default constructor.
     */
    public Vector()
    {
        this.x = Constants.ZERO;
        this.y = Constants.ZERO;
        this.z = Constants.ZERO;
    }

/*    public Vector(Vertex start, Vertex end)
    {
        this.x = end.getX() - start.getX();
        this.y = end.getY() - start.getY();
        this.z = end.getZ() - start.getZ();
    }*/

    /**
     * Constructor with parameters.
     *
     * @param x The x-coordinate of the vector.
     * @param y The y-coordinate of the vector.
     * @param z The z-coordinate of the vector.
     */
    public Vector(float x, float y, float z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Constructor with a vertex as parameter.
     *
     * @param vertex The vertex to create the vector from.
     */
    public Vector(Vertex vertex)
    {
        this.x = vertex.getX();
        this.y = vertex.getY();
        this.z = vertex.getZ();
    }

    /** Cross product of two vectors.
     *
     * @param v The vector to calculate the cross product with.
     * @return The cross product of the two vectors.
     */
    public Vector crossProduct(Vector v)
    {
        return new Vector(this.y * v.getZ() - this.z * v.getY(),
                          this.z * v.getX() - this.x * v.getZ(),
                          this.x * v.getY() - this.y * v.getX());
    }

    /** Dot product of two vectors.
     *
     * @param v The vector to calculate the dot product with.
     * @return The dot product of the two vectors.
     */
    public float dotProduct(Vector v)
    {
        return this.x * v.x + this.y * v.y + this.z * v.z;
    }

    /**
     * Calculates the magnitude of the vector.
     *
     * @return The magnitude of the vector.
     */
    public float magnitude()
    {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    /** Subtracts a vector from a given vector.
     *
     * @param vector The vector to subtract.
     * @return The result of the subtraction.
     */
    public Vector subtract(Vector vector)
    {
        return new Vector(this.x - vector.getX(),
                          this.y - vector.getY(),
                          this.z - vector.getZ());
    }

    /**
     * Getter for the x-coordinate.
     *
     * @return The x-coordinate of the vector.
     */
    public float getX()
    {
        return x;
    }

    /**
     * Getter for the y-coordinate.
     *
     * @return The y-coordinate of the vector.
     */
    public float getY()
    {
        return y;
    }

    /**
     * Getter for the z-coordinate.
     *
     * @return The z-coordinate of the vector.
     */
    public float getZ()
    {
        return z;
    }

    /** Returns the vector as a string.
     *
     * @return The vector as a string.
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