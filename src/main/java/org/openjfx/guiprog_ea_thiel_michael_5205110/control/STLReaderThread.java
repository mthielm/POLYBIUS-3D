package org.openjfx.guiprog_ea_thiel_michael_5205110.control;

import org.openjfx.guiprog_ea_thiel_michael_5205110.model.Polygon;
import org.openjfx.guiprog_ea_thiel_michael_5205110.model.Polyhedron;
import org.openjfx.guiprog_ea_thiel_michael_5205110.util.Literals;
import org.openjfx.guiprog_ea_thiel_michael_5205110.view.Console;

/**
 * This class is responsible for reading an STL file in a separate thread.
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
     * @Postcondition: A new STLReaderThread is created with the given file.
     * @Praecondition: file is not null.
     */
    public STLReaderThread(String file) {
        this.file = file;
    }

    /**
     * Reads the file and creates a polyhedron from it.
     *
     * @Postcondition: The polyhedron is created from the file.
     * @Praecondition: The file is a valid STL file.
     */
    @Override
    public void run() {
        Console.log(Literals.PARSING_FILE + file);
        polyhedron = STLReader.parse(file);
        // Add a special "end" polygon to signal the end of parsing
        PolygonListManager.getInstance().addPolygon(new Polygon());
    }

    /**
     * Returns the polyhedron that was created from the file.
     *
     * @return The polyhedron that was created from the file.
     * @Postcondition: The polyhedron is returned.
     * @Praecondition: The polyhedron is not null.
     */
    public Polyhedron getPolyhedron()
    {
        return polyhedron;
    }
}