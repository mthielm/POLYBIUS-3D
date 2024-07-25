package org.openjfx.guiprog_ea_thiel_michael_5205110;

import org.openjfx.guiprog_ea_thiel_michael_5205110.control.MeshController;
import org.openjfx.guiprog_ea_thiel_michael_5205110.control.PolyhedronController;
import org.openjfx.guiprog_ea_thiel_michael_5205110.control.STLReaderThread;
import org.openjfx.guiprog_ea_thiel_michael_5205110.control.SurfaceCalculatorThread;
import org.openjfx.guiprog_ea_thiel_michael_5205110.model.Polyhedron;
import org.openjfx.guiprog_ea_thiel_michael_5205110.net.Peer2PeerTest;
import org.openjfx.guiprog_ea_thiel_michael_5205110.util.Literals;
import org.openjfx.guiprog_ea_thiel_michael_5205110.util.Scanner;
import org.openjfx.guiprog_ea_thiel_michael_5205110.util.Timer;
import org.openjfx.guiprog_ea_thiel_michael_5205110.view.Console;
import org.openjfx.guiprog_ea_thiel_michael_5205110.view.Polygon3D;

// ToDo: control-package -> check comments and warnings!
/**
 * Main class of the program. Initializes the program and starts the GUI.
 *
 * @author mthiel
 */
public class Main
{
    /**
     * Main method of the program. Starts the program, prompts the user for a
     * file name.
     * Parses the file and initializes the GUI. Opens a connection for Peer2Peer.
     *
     * @param args Command line arguments
     */
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

        // Retrieves the polyhedron from the reader thread
        Polyhedron polyhedron = readerThread.getPolyhedron();

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

        // Starts GUI and opens connection for Peer2Peer
        Peer2PeerTest peer2PeerTest = new Peer2PeerTest();
        peer2PeerTest.main(args);
    }
}