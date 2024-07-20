package org.openjfx.guiprog_ea_thiel_michael_5205110.control;

import org.openjfx.guiprog_ea_thiel_michael_5205110.GUIController;
import org.openjfx.guiprog_ea_thiel_michael_5205110.model.Polyhedron;

import java.io.IOException;
import java.io.ObjectInputStream;

public class CommandServer implements Runnable
{
    private static boolean isAccepting = false;

    private int portNumber = -1;

    public CommandServer(int portNumber)
    {
        this.portNumber = portNumber;
    }

    public static boolean isAccepting()
    {
        return isAccepting;
    }

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
            System.out.println("Error: " + e.getMessage());
            return;
        }
        System.out.println("Waiting for client to connect...");
        java.net.Socket client = null;
        try
        {
            isAccepting = true;
            client = server.accept();
        }
        catch (java.io.IOException e)
        {
            System.out.println("Error Accepting: " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Client connected!");
        serveClient(client);
    }

    private void serveClient(java.net.Socket client)
    {
        try
        {
            java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(client.getInputStream()));
            java.io.PrintWriter out = new java.io.PrintWriter(client.getOutputStream(), true);
            String inputLine;
            while((inputLine = in.readLine()) != null)
                if (inputLine.length() > 0)
                {
                    //Code to send the command to the GUI to either translate or rotate object i.e translate(axis, distance) or rotate(axis, angle)
                    //parseCommand(inputLine);
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
                System.out.println("Error Eingabe/Ausgabe: " + e.getMessage());
            }
        }

    /*private static void parseCommand(String input)
    {
        String[] parts = input.split(" ");
        System.out.println("Command: " + parts[0] + "/" + parts[1] + "/" + parts[2]);
        String command = parts[0];
        if (command.equals("translate"))
        {

            // Perform translation
            String axis = parts[1];
            float distance = Float.parseFloat(parts[2]);

            // Call the method in MeshController class to translate
            GUIController.getInstance().translate(axis, distance);
            System.out.println("Tatsächlich angesteuerter Controller: " + GUIController.getInstance());

            System.out.println("Translation: (" + axis + "," + distance + ")");
        } else if (command.equals("rotate"))
        {

            // Perform rotation
            String axis = parts[1];
            float angle = Float.parseFloat(parts[2]);
            // Call the method in MeshController class to rotate
            GUIController.getInstance().rotate(axis, angle);
            System.out.println("Rotation: (" + axis + "," + angle + ")");
        }
    }*/

    public Polyhedron receivePolyhedron(java.net.Socket client) {
        try {
            ObjectInputStream in = new ObjectInputStream(client.getInputStream());
            return (Polyhedron) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
}