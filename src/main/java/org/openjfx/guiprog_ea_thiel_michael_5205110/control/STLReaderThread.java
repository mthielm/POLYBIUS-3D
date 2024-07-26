package org.openjfx.guiprog_ea_thiel_michael_5205110.control;

import org.openjfx.guiprog_ea_thiel_michael_5205110.model.Polygon;
import org.openjfx.guiprog_ea_thiel_michael_5205110.model.Polyhedron;
import org.openjfx.guiprog_ea_thiel_michael_5205110.util.Literals;
import org.openjfx.guiprog_ea_thiel_michael_5205110.view.Console;

/**
 * This class is responsible for reading an STL file in a separate thread.
 *
 * @author mthiel
 */
public class STLReaderThread extends Thread {
    /** The file to be read. */
    private final String file;

    /** The polyhedron that is created from the file. */
    private Polyhedron polyhedron;

    /**
     * Constructor for the STLReaderThread.
     *
     * @param file The file to be read.
     * @Precondition: file is not null.
     * @Postcondition: A new STLReaderThread is created with the given file.
     */
    public STLReaderThread(String file) {
        this.file = file;
    }

    /**
     * Reads the file and creates a polyhedron from it.
     *
     * @Precondition: The file is a valid STL file.
     * @Postcondition: The polyhedron is created from the file.
     */
    @Override
    public void run() {
        Console.log(Literals.PARSING_FILE + file);
        polyhedron = STLReader.parse(file);
        // Add a special "end" polygon to signal the end of parsing
        PolygonStack.getInstance().addPolygon(new Polygon());
    }

    /**
     * Returns the polyhedron that was created from the file.
     *
     * @return The polyhedron that was created from the file.
     * @Precondition: The polyhedron is not null.
     * @Postcondition: The polyhedron is returned.
     */
    public Polyhedron getPolyhedron()
    {
        return polyhedron;
    }
}