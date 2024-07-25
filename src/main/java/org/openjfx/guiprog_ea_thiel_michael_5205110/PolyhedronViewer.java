package org.openjfx.guiprog_ea_thiel_michael_5205110;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.openjfx.guiprog_ea_thiel_michael_5205110.control.GUIController;
import org.openjfx.guiprog_ea_thiel_michael_5205110.control.MeshController;
import org.openjfx.guiprog_ea_thiel_michael_5205110.model.FileInfo;
import org.openjfx.guiprog_ea_thiel_michael_5205110.util.Constants;
import org.openjfx.guiprog_ea_thiel_michael_5205110.util.Literals;
import org.openjfx.guiprog_ea_thiel_michael_5205110.util.File;
import org.openjfx.guiprog_ea_thiel_michael_5205110.view.Polygon3D;

import java.io.IOException;

/**
 * Main class for the GUI. Initializes the JavaFX application.
 *
 * @author mthiel
 */
public class PolyhedronViewer extends Application
{
    /**
     * Main method of the program. Calls the launch method of the Application
     * class.
     *
     * @param args Command line arguments.
     * @Praecondition: -
     * @Postcondition: Program is running.
     */
    public static void main(String[] args)
    {
        launch();
    }

    /**
     * Initializes the JavaFX application. Called by the launch method.
     * Once the FXML file is loaded, the GUIController is called and the stage
     * is displayed.
     * Overrides the start method of the Application class.
     *
     * @param stage The stage to display the GUI.
     * @throws IOException If the FXML file cannot be loaded.
     * @Praecondition: -
     * @Postcondition: GUI is displayed.
     */
    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(PolyhedronViewer.class.
                getResource(File.FXML_FILE));
        AnchorPane root = loader.load();
        GUIController controller = loader.getController();

        controller.setStage();

        Scene scene = new Scene(root, Constants.SCREEN_WIDTH,
                                Constants.SCREEN_HEIGHT);

        //Alternative Rotation Code
        MeshController meshController = new MeshController();
        double[] origin = meshController.findOrigin(Polygon3D.getInstance().
                getPoints().toArray(new float[Constants.ZERO]));
        GUIController.getInstance().setPivotX(origin[Constants.ZERO]);
        GUIController.getInstance().setPivotY(origin[Constants.ONE]);
        GUIController.getInstance().setPivotZ(origin[Constants.TWO]);

        root.getChildren().add(controller.drawMesh(scene));
        //Console.log(root.getChildren());

        controller.updateTexts(FileInfo.getInstance().getFileName(),
                               FileInfo.getInstance().getFileFormat(),
                               FileInfo.getInstance().getPolygonCount());
        root.getChildren().get(Constants.FOURTEEN).toBack();

        stage.setTitle(Literals.GUI_TITLE);

        stage.setResizable(false);

        PerspectiveCamera perspectiveCamera = new PerspectiveCamera(false);
        scene.setCamera(perspectiveCamera);
        controller.setCamera();

        stage.setScene(scene);
        stage.show();
    }
}