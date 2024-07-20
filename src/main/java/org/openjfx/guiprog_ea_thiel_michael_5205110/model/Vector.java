package org.openjfx.guiprog_ea_thiel_michael_5205110.model;

/**
 * Represents a vector in 3D space and provides some operations.
 *
 * @author mthiel
 */
public class Vector
{
    private float x;
    private float y;
    private float z;

    public Vector()
    {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public Vector(Vertex start, Vertex end)
    {
        this.x = end.getX() - start.getX();
        this.y = end.getY() - start.getY();
        this.z = end.getZ() - start.getZ();
    }

    public Vector(float x, float y, float z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector(Vertex vertex)
    {
        this.x = vertex.getX();
        this.y = vertex.getY();
        this.z = vertex.getZ();
    }

    public float getX()
    {
        return x;
    }

    public float getY()
    {
        return y;
    }

    public float getZ()
    {
        return z;
    }

    public Vector crossProduct(Vector v)
    {
        return new Vector(this.y * v.getZ() - this.z * v.getY(), this.z * v.getX() - this.x * v.getZ(), this.x * v.getY() - this.y * v.getX());
    }

    public float dotProduct(Vector v)
    {
        return this.x * v.x + this.y * v.y + this.z * v.z;
    }

    public float magnitude()
    {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    public Vector subtract(Vector vector)
    {
        return new Vector(this.x - vector.getX(), this.y - vector.getY(), this.z - vector.getZ());
    }

    @Override
    public String toString()
    {
        return "(" + x +
                "x," + y +
                "y," + z +
                "z)";
    }
}