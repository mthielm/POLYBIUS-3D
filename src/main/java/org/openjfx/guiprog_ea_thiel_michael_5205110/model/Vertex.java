package org.openjfx.guiprog_ea_thiel_michael_5205110.model;

/**
 * Represents a vertex of a given polygon.
 *
 * @author mthiel
 */
public class Vertex
{
    private float x;
    private float y;
    private float z;

    public Vertex(float x, float y, float z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
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

    public void setX(float x)
    {
        this.x = x;
    }

    public void setY(float y)
    {
        this.y = y;
    }

    public void setZ(float z)
    {
        this.z = z;
    }

    @Override
    public String toString()
    {
        return  "(" + x +
                "x,"  + y +
                "y,"  + z +
                "z)";
    }
}


