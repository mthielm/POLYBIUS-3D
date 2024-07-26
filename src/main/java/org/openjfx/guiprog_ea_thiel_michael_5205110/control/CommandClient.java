package org.openjfx.guiprog_ea_thiel_michael_5205110.control;

import org.openjfx.guiprog_ea_thiel_michael_5205110.util.Constants;
import org.openjfx.guiprog_ea_thiel_michael_5205110.util.Literals;
import org.openjfx.guiprog_ea_thiel_michael_5205110.view.Console;

/**
 * Class to send commands to the server.
 * The client sends commands to the server and receives the response.
 * The client is also able to send Polyhedrons to the server.
 *
 * @author mthiel
 *
 * @see java.net.Socket
 * @see java.io.BufferedReader
 * @see java.io.InputStreamReader
 */
public class CommandClient implements Runnable
{
    /** Hostname of the server. */
    private final String hostName;

    /** Port number of the server. */
    private final int portNumber;

    /**
     * Constructor of the CommandClient.
     *
     * @param hostName Hostname of the server.
     * @param portNumber Port number of the server.
     * @Precondition: hostName is not null, portNumber is greater than 0.
     * @Postcondition: The CommandClient is initialized.
     */
    public CommandClient(String hostName, int portNumber)
    {
        this.hostName = hostName;
        this.portNumber = portNumber;
    }

    /**
     * Method to run the client.
     * Opens a socket to the server and sends commands to the server.
     *
     * @Precondition: The server is running.
     * @Postcondition: The client is running.
     */
    @Override
    public void run()
    {
        readAndSend(openSocket());
    }

    /**
     * Method to open a socket to the server.
     *
     * @return The socket to the server.
     * @Precondition: The server is running.
     * @Postcondition: A socket to the server is opened.
     */
    private java.net.Socket openSocket()
    {
        try
        {
            return new java.net.Socket(this.hostName, this.portNumber);
        }
        catch (java.io.IOException e)
        {
            return null;
        }
    }

    /**
     * Method to read and send commands to the server.
     *
     * @param socket The socket to the server.
     * @Precondition: The server is running.
     * @Postcondition: The client sends commands to the server.
     */
    private void readAndSend(java.net.Socket socket)
    {
        while (socket == null) {
            try {
                Thread.sleep(Constants.TENTHOUSAND);
                socket = openSocket();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
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
                try {
                    command = keyboard.readLine();
                    if(!command.isEmpty())
                    {
                        out.println(command);

                        GUIController.getInstance().parseCommand(command);
                    }
                    else
                        break;
                } catch (java.io.IOException e) {
                    Console.log(Literals.ERROR + e.getMessage());
                    break;
                }
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
}