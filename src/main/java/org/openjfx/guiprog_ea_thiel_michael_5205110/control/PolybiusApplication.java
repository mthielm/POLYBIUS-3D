package org.openjfx.guiprog_ea_thiel_michael_5205110.control;

import org.openjfx.guiprog_ea_thiel_michael_5205110.view.PolyhedronViewer;
import org.openjfx.guiprog_ea_thiel_michael_5205110.model.Polyhedron;
import org.openjfx.guiprog_ea_thiel_michael_5205110.net.PeerToPeerService;

public class PolybiusApplication
{
    private final UserInputHandler userInputHandler;
    private final STLFileProcessor stlFileProcessor;
    private final MeshController meshController;
    private final PeerToPeerService peerToPeerService;

    public PolybiusApplication()
    {
        this.userInputHandler = new UserInputHandler();
        this.stlFileProcessor = new STLFileProcessor();
        this.meshController = new MeshController();
        this.peerToPeerService = new PeerToPeerService();
    }

    public void run(String[] args)
    {
        userInputHandler.printWelcomeMessage();
        String filePath = userInputHandler.getFilePathFromUser();

        Polyhedron polyhedron = stlFileProcessor.processFile(filePath);

        meshController.drawMesh(polyhedron);
        PolyhedronViewer.main(args);

        peerToPeerService.start();
    }
}
