package org.openjfx.guiprog_ea_thiel_michael_5205110.net;

import org.openjfx.guiprog_ea_thiel_michael_5205110.PolyhedronViewer;
import org.openjfx.guiprog_ea_thiel_michael_5205110.control.CommandClient;
import org.openjfx.guiprog_ea_thiel_michael_5205110.control.CommandServer;

public class Peer2PeerTest {

    public static void main(String[] args) {
        // Start the server instance in a new thread
        new Thread(() -> {
            CommandServer server = new CommandServer(1234);
            new Thread(server).start();
            while (!CommandServer.isAccepting()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            (new Thread(new CommandClient("127.0.0.1", 1235))).start();
            PolyhedronViewer.main(args);
        }).start();
    }
}
