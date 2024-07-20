package org.openjfx.guiprog_ea_thiel_michael_5205110;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.openjfx.guiprog_ea_thiel_michael_5205110.control.MeshController;
import org.openjfx.guiprog_ea_thiel_michael_5205110.model.FileInfo;
import org.openjfx.guiprog_ea_thiel_michael_5205110.util.Literals;
import org.openjfx.guiprog_ea_thiel_michael_5205110.view.Polygon3D;

import java.io.IOException;

public class PolygonApplication extends Application
{
    public static void main(String[] args)
    {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(PolygonApplication.class.getResource("polygon-view.fxml"));
        AnchorPane root = loader.load();
        GUIController controller = loader.getController();

        controller.setStage(stage);

        Scene scene = new Scene(root, 960, 540);

        //Doesn't work
        //Image icon = new Image("/icon.png");
        //stage.getIcons().add(icon);

        //Alternative Rotation Code
        MeshController meshController = new MeshController();
        double[] origin = meshController.findOrigin(Polygon3D.getInstance().getPoints().toArray(new float[0]));
        GUIController.getInstance().setPivotX(origin[0]);
        GUIController.getInstance().setPivotY(origin[1]);
        GUIController.getInstance().setPivotZ(origin[2]);

        root.getChildren().add(controller.drawMesh(scene));
        //System.out.println(root.getChildren());

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