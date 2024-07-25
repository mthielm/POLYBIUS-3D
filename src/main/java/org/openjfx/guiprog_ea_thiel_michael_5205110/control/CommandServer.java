package org.openjfx.guiprog_ea_thiel_michael_5205110.control;

import org.openjfx.guiprog_ea_thiel_michael_5205110.model.Polyhedron;
import org.openjfx.guiprog_ea_thiel_michael_5205110.util.Constants;
import org.openjfx.guiprog_ea_thiel_michael_5205110.util.Literals;
import org.openjfx.guiprog_ea_thiel_michael_5205110.view.Console;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Class to receive commands from the server.
 * The server sends commands to the client and receives the response.
 * The server is also able to receive Polyhedrons from the client.
 */
public class CommandServer implements Runnable
{
    /**
     * Port number of the server
     */
    private static boolean isAccepting = false;
    /**
     * Port number of the server
     */
    private int portNumber = -1;

    /**
     * Constructor of the CommandServer
     *
     * @param portNumber Port number of the server
     */
    public CommandServer(int portNumber)
    {
        this.portNumber = portNumber;
    }

    /**
     * Method to check if the server is accepting connections.
     *
     * @return True if the server is accepting connections, false otherwise
     */
    public static boolean isAccepting()
    {
        return isAccepting;
    }

    /**
     * Method to run the server.
     * Opens a socket to the client and receives commands from the client.
     */
    @Override
    public void run()
    {
        java.net.ServerSocket server = null;
        try
        {
            server = new java.net.ServerSocket(this.portNumber);
        }
        catch (java.io.IOException e)
        {
            Console.log(Literals.ERROR + e.getMessage());
            return;
        }
        Console.log(Literals.WAITING_FOR_CLIENT);
        java.net.Socket client = null;
        try
        {
            isAccepting = true;
            client = server.accept();
        }
        catch (java.io.IOException e)
        {
            Console.log(Literals.ERROR_ACCEPTING + e.getMessage());
            System.exit(Constants.ZERO);
        }
        Console.log(Literals.CLIENT_CONNECTED);
        serveClient(client);
    }

    /**
     * Method to serve the client.
     * Reads the commands from the client and sends them to the GUI.
     *
     * @param client The client to serve
     */
    private void serveClient(java.net.Socket client)
    {
        try
        {
            java.io.BufferedReader in = new java.io.BufferedReader
                    (new java.io.InputStreamReader(client.getInputStream()));
            java.io.PrintWriter out = new java.io.PrintWriter
                    (client.getOutputStream(), true);
            String inputLine;
            while((inputLine = in.readLine()) != null)
                if (inputLine.length() > Constants.ZERO)
                {
                    // Code to send the command to the GUI to either
                    // translate or rotate object i.e. translate(axis,
                    // distance) or rotate(axis, angle) parseCommand
                    // (inputLine);
                    GUIController.getInstance().parseCommand(inputLine);

                }
            in.close();
            out.close();
            ////////////////////////
            Polyhedron polyhedron = receivePolyhedron(client);
            if (polyhedron != null) {
                GUIController.getInstance().updatePolyhedron(polyhedron);
            }
            /////////////////////////
            client.close();
            }
            catch (java.io.IOException e)
            {
                Console.log(Literals.ERROR_IN_OUT + e.getMessage());
            }
        }

    /*private static void parseCommand(String input)
    {
        String[] parts = input.split(" ");
        Console.log("Command: " + parts[0] + "/" + parts[1] + "/" + parts[2]);
        String command = parts[0];
        if (command.equals("translate"))
        {

            // Perform translation
            String axis = parts[1];
            float distance = Float.parseFloat(parts[2]);

            // Call the method in MeshController class to translate
            GUIController.getInstance().translate(axis, distance);
            Console.log("Tatsächlich angesteuerter Controller: " + GUIController.getInstance());

            Console.log("Translation: (" + axis + "," + distance + ")");
        } else if (command.equals("rotate"))
        {

            // Perform rotation
            String axis = parts[1];
            float angle = Float.parseFloat(parts[2]);
            // Call the method in MeshController class to rotate
            GUIController.getInstance().rotate(axis, angle);
            Console.log("Rotation: (" + axis + "," + angle + ")");
        }
    }*/

    /**
     * Method to receive a Polyhedron from the client.
     *
     * @param client The client to receive the Polyhedron from
     * @return The Polyhedron received from the client
     */
    public Polyhedron receivePolyhedron(java.net.Socket client) {
        try {
            ObjectInputStream in = new ObjectInputStream
                    (client.getInputStream());
            return (Polyhedron) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            Console.log(Literals.ERROR + e.getMessage());
            return null;
        }
    }
}