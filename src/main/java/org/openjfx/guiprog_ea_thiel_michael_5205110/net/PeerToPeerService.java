package org.openjfx.guiprog_ea_thiel_michael_5205110.net;

import org.openjfx.guiprog_ea_thiel_michael_5205110.control.CommandClient;
import org.openjfx.guiprog_ea_thiel_michael_5205110.control.CommandServer;
import org.openjfx.guiprog_ea_thiel_michael_5205110.util.Constants;
import org.openjfx.guiprog_ea_thiel_michael_5205110.util.Literals;

public class PeerToPeerService {
    public static void start() {
        // Start the server instance in a new thread
        new Thread(() -> {
            CommandServer server = new CommandServer(Constants.PORT_SERVER);
            new Thread(server).start();
            while (CommandServer.isAccepting())
            {
                try
                {
                    Thread.sleep(Constants.MILLISECONDS);
                }
                catch (InterruptedException e)
                {
                    throw new RuntimeException(e);
                }
            }
            (new Thread(new CommandClient
                    (Literals.HOST_NAME, Constants.PORT_CLIENT))).start();
        }).start();
    }
}