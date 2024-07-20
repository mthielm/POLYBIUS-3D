package org.openjfx.guiprog_ea_thiel_michael_5205110.control;

import org.openjfx.guiprog_ea_thiel_michael_5205110.model.*;
import org.openjfx.guiprog_ea_thiel_michael_5205110.util.Constants;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Provides Methods to parse STL-Files (Both Binary and ASCII).
 *
 * @author mthiel
 * @see BufferedReader
 */
public class STLReader
{
    public static Polyhedron parse(String file)
    {
        try {
            DataInputStream inputStream = new DataInputStream(new FileInputStream(file));

            if(isASCII(inputStream))
            {
                return STLReader.readASCII(file);
            }
            else
            {
                return readBinary(file);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    public static Polyhedron readASCII(String fileName) throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        String data;
        Polygon tempPoly = new Polygon();
        Vector tempFace = null;

        Polyhedron polyhedron = new Polyhedron();

        while ((data = br.readLine()) != null)
        {
            data = data.trim();

            if (data.startsWith("facet"))
            {

                String[] parts = data.split("\\s+");

                float nx = Float.parseFloat(parts[2]);
                float ny = Float.parseFloat(parts[3]);
                float nz = Float.parseFloat(parts[4]);

                tempFace = new Vector(nx, ny, nz);

            }
            else if (data.startsWith("vertex"))
            {
                String[] parts = data.split("\\s+");

                float x = Float.parseFloat(parts[1]);
                float y = Float.parseFloat(parts[2]);
                float z = Float.parseFloat(parts[3]);

                tempPoly.addVertex(new Vertex(x, y, z));
            }
            else if (data.equals("endfacet"))
            {
                tempPoly.setNormal(tempFace);

                tempPoly.addEdge(new Edge(tempPoly.getVertices().get(0), tempPoly.getVertices().get(1)));
                tempPoly.addEdge(new Edge(tempPoly.getVertices().get(1), tempPoly.getVertices().get(2)));
                tempPoly.addEdge(new Edge(tempPoly.getVertices().get(2), tempPoly.getVertices().get(0)));

                polyhedron.addPolygon(tempPoly);

                //Update info box in GUI
                FileInfo.getInstance().setPolygonCount(polyhedron.getPolygons().toArray().length);
                FileInfo.getInstance().setFileFormat("ASCII");

                //Nebenläufigkeit für Volumenberechnung
                PolygonListManager.getInstance().addPolygon(tempPoly);

                tempPoly = new Polygon();
                tempFace = null;
            }
        }
        br.close();
        return polyhedron;
    }


    private static Polyhedron readBinary(String file) throws IOException
    {
        DataInputStream inputStream = new DataInputStream(new FileInputStream(file));
        inputStream.skipBytes(Constants.BINARY_HEADER);

        // Read the next 4 bytes as an unsigned integer (number of triangles)
        byte[] bytes = new byte[4];
        inputStream.readFully(bytes);
        // Convert to an unsigned int using little-endian order
        ByteBuffer buffer = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN);
        int numberOfTriangles = buffer.getInt();

        // Print the number of triangles
        System.out.println("Number of triangles: " + numberOfTriangles);

        //Update info box in GUI
        FileInfo.getInstance().setPolygonCount(numberOfTriangles);
        FileInfo.getInstance().setFileFormat("BINARY");

        Polyhedron polyhedron = new Polyhedron();
        Polygon tempPoly = new Polygon();
        Vector tempFace = null;

        for (int i = 0; i < numberOfTriangles; i++)
        {
            float nx = readFloat(inputStream);
            float ny = readFloat(inputStream);
            float nz = readFloat(inputStream);
            tempFace = new Vector(nx, ny, nz);

            for (int j = 0; j < 3; j++)
            {
                float x = readFloat(inputStream);
                float y = readFloat(inputStream);
                float z = readFloat(inputStream);
                tempPoly.addVertex(new Vertex(x, y, z));
            }
            tempPoly.setNormal(tempFace);

            tempPoly.addEdge(new Edge(tempPoly.getVertices().get(0), tempPoly.getVertices().get(1)));
            tempPoly.addEdge(new Edge(tempPoly.getVertices().get(1), tempPoly.getVertices().get(2)));
            tempPoly.addEdge(new Edge(tempPoly.getVertices().get(2), tempPoly.getVertices().get(0)));

            inputStream.skipBytes(2); // Skip attribute byte count

            polyhedron.addPolygon(tempPoly);
            polyhedron.setNumPolygons(numberOfTriangles);

            //Nebenläufigkeit für Volumenberechnung
            PolygonListManager.getInstance().addPolygon(tempPoly);

            tempPoly = new Polygon();
        }
        return polyhedron;
    }

    private static float readFloat(DataInputStream inputStream) throws IOException
    {
        byte[] bytes = new byte[4];
        inputStream.readFully(bytes);
        return ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).getFloat();
    }

    private static boolean isASCII(DataInputStream data) throws IOException
    {
        byte[] header = new byte[Constants.BINARY_HEADER];
        data.readFully(header);
        String headerStr = new String(header);
        data.close();

        if(headerStr.startsWith("solid"))
        {
            System.out.println("Datei ist im ASCII Format.");
            return true;
        }
        else
        {
            System.out.println("Datei ist im Binary Format.");
            return false;
        }
    }
}