package org.openjfx.guiprog_ea_thiel_michael_5205110;

import org.openjfx.guiprog_ea_thiel_michael_5205110.control.MeshController;
import org.openjfx.guiprog_ea_thiel_michael_5205110.control.PolyhedronController;
import org.openjfx.guiprog_ea_thiel_michael_5205110.control.STLReaderThread;
import org.openjfx.guiprog_ea_thiel_michael_5205110.control.SurfaceCalculatorThread;
import org.openjfx.guiprog_ea_thiel_michael_5205110.model.Polyhedron;
import org.openjfx.guiprog_ea_thiel_michael_5205110.util.Literals;
import org.openjfx.guiprog_ea_thiel_michael_5205110.util.Scanner;
import org.openjfx.guiprog_ea_thiel_michael_5205110.util.Timer;
import org.openjfx.guiprog_ea_thiel_michael_5205110.view.Console;
import org.openjfx.guiprog_ea_thiel_michael_5205110.view.Polygon3D;

/**
 * @author Michael Thiel (5205110)
 */
public class Main
{
    public static void main(String[] args)
    {
        Console.log(Literals.WELCOME_TITLE);
        Console.log(Literals.WELCOME_MESSAGE);

        // Prompts the user to enter a file name
        Scanner scanner = new Scanner();
        String filePath = scanner.prompt();


        // Starts a timer to measure parse time
        Timer timer = new Timer();
        timer.start();

        // Starts two threads to parse the STL file and calculate the surface area
        STLReaderThread readerThread = new STLReaderThread(filePath);
        SurfaceCalculatorThread calculatorThread = new SurfaceCalculatorThread();

        readerThread.start();
        calculatorThread.start();

        try
        {
            readerThread.join();
            calculatorThread.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        // Stops the timer and prints the runtime
        //timer.stop();
        //Console.log(timer.getRuntime() + Literals.SECONDS);

        // Retrieves the polyhedron from the reader thread
        Polyhedron polyhedron = readerThread.getPolyhedron();

        //Sort polygons by surface area
        //PolyhedronController.getInstance().sortByArea(polyhedron);

        //Calculate volume of polyhedron
        PolyhedronController.getInstance().calculateVolume(polyhedron);

        // Maps vertices to a format readable by TriangleMesh object
        MeshController meshController = new MeshController();
        float[] points = meshController.mapPoints(polyhedron);
        int[] faces = meshController.createFaces(polyhedron);
        float[] textures = meshController.createTextures(polyhedron);
        int[] combinedFaces = meshController.mapFaces(faces, textures);

        // Sets up the TriangleMesh object
        Polygon3D polygon3D = Polygon3D.getInstance();
        polygon3D.setupMesh(points, textures, combinedFaces);

        //PolygonApplication.main(args);

        // Starts GUI and opens connection for Peer2Peer
        Peer2PeerTest peer2PeerTest = new Peer2PeerTest();
        peer2PeerTest.main(args);
    }
}