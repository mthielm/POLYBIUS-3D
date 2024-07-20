package org.openjfx.guiprog_ea_thiel_michael_5205110.control;

import org.openjfx.guiprog_ea_thiel_michael_5205110.model.Polygon;
import org.openjfx.guiprog_ea_thiel_michael_5205110.model.Polyhedron;
import org.openjfx.guiprog_ea_thiel_michael_5205110.view.Console;

public class STLReaderThread extends Thread {
    private String file;
    private Polyhedron polyhedron;

    public STLReaderThread(String file) {
        this.file = file;
    }

    @Override
    public void run() {
        Console.log("Parsing file: " + file);
        STLReader reader = new STLReader();
        polyhedron = reader.parse(file);
        // Add a special "end" polygon to signal the end of parsing
        PolygonListManager.getInstance().addPolygon(new Polygon());
        //System.out.println("Reader thread stopped");
    }

    public Polyhedron getPolyhedron()
    {
        return polyhedron;
    }
}