package org.openjfx.guiprog_ea_thiel_michael_5205110;

// ToDo: all packages and classes -> check comments and warnings!
// ToDo: Java-Docs -> @post, @pre, @date

import org.openjfx.guiprog_ea_thiel_michael_5205110.control.PolybiusApplication;

/**
 * This is "<b>Polybius 3-D</b>", an application developed as part of the
 * coursework for the GUIPROG class at Hochschule Bremen. It is an individual work.<br>
 *
 * Main class of the program. Initializes the program and starts the GUI.
 *
 * @author mthiel
 * @version 1.0
 * @Date: 2024-07-31
 */
public class Main
{
    /**
     * Main method of the program. Starts the program, prompts the user for a
     * file name.
     * Parses the file and initializes the GUI. Opens a connection for Peer2Peer.
     *
     * @param args Command line arguments
     * @Precondition: -
     * @Postcondition: Program is running.
     */
    public static void main(String[] args)
    {
        PolybiusApplication application = new PolybiusApplication();
        application.run(args);
    }
}