package org.openjfx.guiprog_ea_thiel_michael_5205110.net;

import org.openjfx.guiprog_ea_thiel_michael_5205110.PolyhedronViewer;
import org.openjfx.guiprog_ea_thiel_michael_5205110.control.CommandClient;
import org.openjfx.guiprog_ea_thiel_michael_5205110.control.CommandServer;
import org.openjfx.guiprog_ea_thiel_michael_5205110.util.Constants;
import org.openjfx.guiprog_ea_thiel_michael_5205110.util.Literals;

/**
 * This class is used to test the Peer2Peer connection.
 * It starts a server instance in a new thread and a client instance in a new
 * thread.
 * The client instance connects to the server instance.
 * After the server instance stops accepting connections, the PolyhedronViewer
 * is started.
 *
 * @author mthiel
 *
 * @see Thread
 */
public class Peer2PeerTest {
    /**
     * Main method of the program. Starts the Peer2Peer test.
     *
     * @param args Command line arguments.
     * @Precondition: -
     * @Postcondition: Program is running.
     */
    public static void main(String[] args) {
        // Start the server instance in a new thread
        new Thread(() -> {
            CommandServer server = new CommandServer(Constants.PORT_SERVER);
            new Thread(server).start();
            while (CommandServer.isAccepting()) {
                try {
                    Thread.sleep(Constants.MILLISECONDS);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            (new Thread(new CommandClient
                    (Literals.HOST_NAME, Constants.PORT_CLIENT))).start();
            PolyhedronViewer.main(args);
        }).start();
    }
}