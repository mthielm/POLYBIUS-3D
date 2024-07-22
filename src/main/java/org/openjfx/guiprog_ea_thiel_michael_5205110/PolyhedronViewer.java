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
     * Main method of the program. Calls the launch method of the Application class.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args)
    {
        launch();
    }

    /**
     * Initializes the JavaFX application. Called by the launch method.
     * Once the FXML file is loaded, the GUIController is called and the stage is displayed.
     *
     * @param stage The stage to display the GUI.
     * @throws IOException If the FXML file cannot be loaded.
     */
    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(PolyhedronViewer.class.getResource(File.FXML_FILE));
        AnchorPane root = loader.load();
        GUIController controller = loader.getController();

        controller.setStage(stage);

        Scene scene = new Scene(root, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        //Alternative Rotation Code
        MeshController meshController = new MeshController();
        double[] origin = meshController.findOrigin(Polygon3D.getInstance().getPoints().toArray(new float[0]));
        GUIController.getInstance().setPivotX(origin[0]);
        GUIController.getInstance().setPivotY(origin[1]);
        GUIController.getInstance().setPivotZ(origin[2]);

        root.getChildren().add(controller.drawMesh(scene));
        //Console.log(root.getChildren());

        controller.updateTexts(FileInfo.getInstance().getFileName(), FileInfo.getInstance().getFileFormat(), FileInfo.getInstance().getPolygonCount());
        root.getChildren().get(14).toBack();

        stage.setTitle(Literals.GUI_TITLE);

        stage.setResizable(false);

        PerspectiveCamera perspectiveCamera = new PerspectiveCamera(false);
        scene.setCamera(perspectiveCamera);
        controller.setCamera(perspectiveCamera);

        stage.setScene(scene);
        stage.show();
    }
}