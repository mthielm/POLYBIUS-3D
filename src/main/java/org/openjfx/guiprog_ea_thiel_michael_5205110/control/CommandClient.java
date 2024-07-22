package org.openjfx.guiprog_ea_thiel_michael_5205110.control;

import org.openjfx.guiprog_ea_thiel_michael_5205110.model.Polyhedron;
import org.openjfx.guiprog_ea_thiel_michael_5205110.view.Console;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class CommandClient implements Runnable
{
    private String hostName = null;
    private int portNumber = -1;

    public CommandClient(String hostName, int portNumber)
    {
        this.hostName = hostName;
        this.portNumber = portNumber;
    }

    @Override
    public void run()
    {
        readAndSend(openSocket());
    }

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

    private void readAndSend(java.net.Socket socket)
    {
        while (socket == null) {
            //Console.log("Error: Could not connect to server. Retrying in 1 second...");
            try {
                Thread.sleep(10000); // wait for 1 second before retrying
                socket = openSocket();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // restore interrupted status
                return;
            }
        }

        try
        {

            java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(socket.getInputStream()));
            java.io.PrintWriter out = new java.io.PrintWriter(socket.getOutputStream(), true);
            java.io.BufferedReader keyboard = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
            Console.log("Please enter a command: ");
            String command;

            while(true)
            {
                command = keyboard.readLine();
                if(command.length() > 0)
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
            Console.log("Error: " + e.getMessage());
        }
    }

    //P2P SEND
    public void sendPolyhedron(Polyhedron polyhedron, java.net.Socket socket) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(polyhedron);
        } catch (IOException e) {
            Console.log("Error: " + e.getMessage());
        }
    }
}