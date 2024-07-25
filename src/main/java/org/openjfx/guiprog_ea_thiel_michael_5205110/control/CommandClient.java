package org.openjfx.guiprog_ea_thiel_michael_5205110.control;

import org.openjfx.guiprog_ea_thiel_michael_5205110.model.Polyhedron;
import org.openjfx.guiprog_ea_thiel_michael_5205110.util.Constants;
import org.openjfx.guiprog_ea_thiel_michael_5205110.util.Literals;
import org.openjfx.guiprog_ea_thiel_michael_5205110.view.Console;

import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Class to send commands to the server.
 * The client sends commands to the server and receives the response.
 * The client is also able to send Polyhedrons to the server.
 *
 * @see CommandServer
 */
public class CommandClient implements Runnable
{
    /**
     * Hostname of the server
     */
    private String hostName = null;
    /**
     * Port number of the server
     */
    private int portNumber = -1;

    /**
     * Constructor of the CommandClient
     *
     * @param hostName Hostname of the server
     * @param portNumber Port number of the server
     */
    public CommandClient(String hostName, int portNumber)
    {
        this.hostName = hostName;
        this.portNumber = portNumber;
    }

    /**
     * Method to run the client.
     * Opens a socket to the server and sends commands to the server.
     */
    @Override
    public void run()
    {
        readAndSend(openSocket());
    }

    /**
     * Method to open a socket to the server.
     *
     * @return The socket to the server
     */
    private java.net.Socket openSocket()
    {
        try
        {
            return new java.net.Socket(this.hostName, this.portNumber);
        }
        catch (java.io.IOException e)
        {
            //Console.log("P2P: Waiting to establish connection...");
            //Console.log("Error: " + e.getMessage());
            return null;
        }
    }

    /**
     * Method to read and send commands to the server.
     *
     * @param socket The socket to the server
     */
    private void readAndSend(java.net.Socket socket)
    {
        while (socket == null) {
            //Console.log("Error: Could not connect to server. Retrying in 1
            // second...");
            try {
                Thread.sleep(Constants.TENTHOUSAND); // wait for 1 second before
                                                     // retrying
                socket = openSocket();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // restore interrupted
                                                    // status
                return;
            }
        }

        try
        {

            java.io.BufferedReader in = new java.io.BufferedReader
                    (new java.io.InputStreamReader(socket.getInputStream()));
            java.io.PrintWriter out = new java.io.PrintWriter
                    (socket.getOutputStream(), true);
            java.io.BufferedReader keyboard = new java.io.BufferedReader
                    (new java.io.InputStreamReader(System.in));
            Console.log(Literals.ENTER_COMMAND);
            String command;

            while(true)
            {
                command = keyboard.readLine();
                if(command.length() > Constants.ZERO)
                {
                    // Send the command to the client
                    out.println(command);

                    GUIController.getInstance().parseCommand(command);
                }
                else
                    break;
            }
            out.close();
            in.close();
            keyboard.close();
            socket.close();
        }
        catch (java.io.IOException e)
        {
            Console.log(Literals.ERROR + e.getMessage());
        }
    }

    //P2P SEND
    /**
     * Method to send a Polyhedron to the server.
     *
     * @param polyhedron The Polyhedron to send
     * @param socket The socket to the server
     */
    public void sendPolyhedron(Polyhedron polyhedron, java.net.Socket socket) {
        try {
            ObjectOutputStream out = new ObjectOutputStream
                    (socket.getOutputStream());
            out.writeObject(polyhedron);
        } catch (IOException e) {
            Console.log(Literals.ERROR + e.getMessage());
        }
    }
}