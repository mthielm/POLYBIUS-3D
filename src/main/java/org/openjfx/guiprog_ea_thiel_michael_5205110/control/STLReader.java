package org.openjfx.guiprog_ea_thiel_michael_5205110.control;

import org.openjfx.guiprog_ea_thiel_michael_5205110.model.*;
import org.openjfx.guiprog_ea_thiel_michael_5205110.util.Constants;
import org.openjfx.guiprog_ea_thiel_michael_5205110.util.Literals;
import org.openjfx.guiprog_ea_thiel_michael_5205110.util.File;
import org.openjfx.guiprog_ea_thiel_michael_5205110.view.Console;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Implements a BufferedReader to read STL files in ASCII and binary format.
 * The class is used to parse the file and create a Polyhedron object.

 * @see BufferedReader
 *
 * @author mthiel
 */
public class STLReader
{
    /**
     * Parses an input file depending on the file format.
     *
     * @param file The input file selected through the user prompt.
     * @return The Polyhedron object.
     */
    public static Polyhedron parse(String file)
    {
        try {
            DataInputStream inputStream = new DataInputStream
                    (new FileInputStream(file));

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

    /**
     * Reads an ASCII file and creates a Polyhedron object.
     *
     * @param fileName The input file selected through the user prompt.
     * @return The Polyhedron object.
     * @throws IOException If an I/O error occurs.
     */
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

            // Parses facet data
            if (data.startsWith(Literals.FACET))
            {

                String[] parts = data.split(Literals.REGEX);

                float nx = Float.parseFloat(parts[Constants.TWO]);
                float ny = Float.parseFloat(parts[Constants.THREE]);
                float nz = Float.parseFloat(parts[Constants.FOUR]);

                tempFace = new Vector(nx, ny, nz);

            }
            // Parses vertex data
            else if (data.startsWith(Literals.VERTEX))
            {
                String[] parts = data.split(Literals.REGEX);

                float x = Float.parseFloat(parts[Constants.ONE]);
                float y = Float.parseFloat(parts[Constants.TWO]);
                float z = Float.parseFloat(parts[Constants.THREE]);

                tempPoly.addVertex(new Vertex(x, y, z));
            }
            // Resulting polygon is added to the polyhedron
            else if (data.equals(Literals.ENDFACET))
            {
                tempPoly.setNormal(tempFace);

                tempPoly.addEdge(new Edge(
                        tempPoly.getVertices().get(Constants.ZERO),
                        tempPoly.getVertices().get(Constants.ONE)));
                tempPoly.addEdge(new Edge(
                        tempPoly.getVertices().get(Constants.ONE),
                        tempPoly.getVertices().get(Constants.TWO)));
                tempPoly.addEdge(new Edge(
                        tempPoly.getVertices().get(Constants.TWO),
                        tempPoly.getVertices().get(Constants.ZERO)));

                polyhedron.addPolygon(tempPoly);

                // Update info box in GUI
                FileInfo.getInstance().setPolygonCount(
                        polyhedron.getPolygons().toArray().length);
                FileInfo.getInstance().setFileFormat(File.ASCII_FORMAT);

                // Concurrent calculation of volume
                PolygonListManager.getInstance().addPolygon(tempPoly);

                tempPoly = new Polygon();
                tempFace = null;
            }
        }
        br.close();
        return polyhedron;
    }

    /**
     * Reads a binary file and creates a Polyhedron object.
     *
     * @param file The input file selected through the user prompt.
     * @return The Polyhedron object.
     * @throws IOException If an I/O error occurs.
     */
    private static Polyhedron readBinary(String file) throws IOException
    {
        DataInputStream inputStream = new DataInputStream(
                new FileInputStream(file));
        inputStream.skipBytes(Constants.BINARY_HEADER);

        // Read the next 4 bytes as an unsigned integer (number of triangles)
        byte[] bytes = new byte[Constants.FOUR];
        inputStream.readFully(bytes);

        // Convert to an unsigned int using little-endian order
        ByteBuffer buffer = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN);
        int numberOfTriangles = buffer.getInt();

        // Print the number of triangles
        Console.log(Literals.NUMBER_OF_TRIANGLES + numberOfTriangles);

        //Update info box in GUI
        FileInfo.getInstance().setPolygonCount(numberOfTriangles);
        FileInfo.getInstance().setFileFormat(File.BINARY_FORMAT);

        Polyhedron polyhedron = new Polyhedron();
        Polygon tempPoly = new Polygon();
        Vector tempFace;

        for (int i = Constants.ZERO; i < numberOfTriangles; i++)
        {
            float nx = readFloat(inputStream);
            float ny = readFloat(inputStream);
            float nz = readFloat(inputStream);
            tempFace = new Vector(nx, ny, nz);

            for (int j = Constants.ZERO; j < Constants.THREE; j++)
            {
                float x = readFloat(inputStream);
                float y = readFloat(inputStream);
                float z = readFloat(inputStream);
                tempPoly.addVertex(new Vertex(x, y, z));
            }
            // Adds resulting polygon to the polyhedron
            tempPoly.setNormal(tempFace);

            tempPoly.addEdge(new Edge(tempPoly.getVertices().get(Constants.ZERO),
                    tempPoly.getVertices().get(Constants.ONE)));
            tempPoly.addEdge(new Edge(tempPoly.getVertices().get(Constants.ONE),
                    tempPoly.getVertices().get(Constants.TWO)));
            tempPoly.addEdge(new Edge(tempPoly.getVertices().get(Constants.TWO),
                    tempPoly.getVertices().get(Constants.ZERO)));

            // Skip 2 Byte unsigned int after each polygon
            inputStream.skipBytes(Constants.BINARY_UNSIGNED_INT_SIZE_BYTES);

            polyhedron.addPolygon(tempPoly);
            polyhedron.setNumPolygons(numberOfTriangles);

            // Concurrent calculation of volume
            PolygonListManager.getInstance().addPolygon(tempPoly);

            tempPoly = new Polygon();
        }
        return polyhedron;
    }

    /**
     * Converts 4 bytes from an input stream to a float using little-endian
     * order.
     *
     * @param inputStream The input stream.
     * @return The resulting float value.
     * @throws IOException If an I/O error occurs.
     */
    private static float readFloat(DataInputStream inputStream) throws IOException
    {
        byte[] bytes = new byte[Constants.BINARY_FLOAT_SIZE_BYTES];
        inputStream.readFully(bytes);
        return ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).getFloat();
    }

    /**
     * Determines whether the input file is in ASCII or binary format.
     *
     * @param data The input stream to read from.
     * @return True if the file is in ASCII format,
     *         False if it is in binary format.
     * @throws IOException If an I/O error occurs.
     */
    private static boolean isASCII(DataInputStream data) throws IOException
    {
        byte[] header = new byte[Constants.BINARY_HEADER];
        data.readFully(header);
        String headerStr = new String(header);
        data.close();

        if(headerStr.startsWith(File.ASCII_HEADER_TAG))
        {
            Console.log(File.ASCII_FORMAT_MESSAGE);
            return true;
        }
        else
        {
            Console.log(File.BINARY_FORMAT_MESSAGE);
            return false;
        }
    }
}