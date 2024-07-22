package org.openjfx.guiprog_ea_thiel_michael_5205110.control;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Point3D;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.MeshView;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.openjfx.guiprog_ea_thiel_michael_5205110.model.Polyhedron;
import org.openjfx.guiprog_ea_thiel_michael_5205110.util.Literals;
import org.openjfx.guiprog_ea_thiel_michael_5205110.view.Console;
import org.openjfx.guiprog_ea_thiel_michael_5205110.view.Polygon3D;

import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.transform.Rotate.*;

public class GUIController implements Initializable
{
    private static GUIController instance = null;

    public GUIController()
    {
        if(instance == null)
            instance = this;
    }

    public static GUIController getInstance() {
        return instance;
    }

    private Stage stage;
    private PerspectiveCamera camera;
    double anchorX, anchorY, anchorZ;
    private double rotationSpeed = 1;
    private double anchorAngleX = 0;
    private double anchorAngleY = 0;
    private double anchorAngleZ = 0;

    double pivotX;
    double pivotY;
    double pivotZ;

    private Timeline rotationTimeline;

    @FXML
    private MeshView meshView;

    @FXML
    private Group meshGroup;

    @FXML
    private MenuItem aboutMenuItem;

    @FXML
    public MenuItem closeButton;

    @FXML
    private Button rotateButtonDown;

    @FXML
    private Button rotateButtonLeft;

    @FXML
    private Button rotateButtonRight;

    @FXML
    private Button rotateButtonUp;

    @FXML
    private Button zoomButton;

    @FXML
    private Button zoomOutButton;

    @FXML
    private Slider velocitySlider;

    @FXML
    private Text info_filename;

    @FXML
    private Text info_format;

    @FXML
    private Text info_polygoncount;

    @FXML
    private RadioButton continuousRotateButtonStart;

    @FXML
    private RadioButton continuousRotateButtonStop;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        velocitySlider.valueProperty().addListener(new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                rotationSpeed = (int) velocitySlider.getValue();
            }
        });

        //startCommandThreads();

        aboutMenuItem.setOnAction(this::handleAboutMenuItemAction);
    }

    public void setInfo_filename(String info1) {
        info_filename.setText(info1);
    }

    public void setInfo_format(String info2) {
        info_format.setText(info2);
    }

    public void setInfo_polygoncount(int info3) {
        info_polygoncount.setText(String.valueOf(info3));
    }

    public void updateTexts(String info1, String info2, String info3) {
        info_filename.setText(info1);
        info_format.setText(info2 + " File");
        info_polygoncount.setText(info3 + " Polygons");
    }

    public Group drawMesh(Scene scene)
    {
        meshView = new MeshView(Polygon3D.getInstance());
        //meshView = new MeshView(new Polygon3D());

        meshView.setMaterial(new PhongMaterial(Color.BLACK));
        meshView.setTranslateX((scene.getWidth()/2));
        meshView.setTranslateY((scene.getHeight()/2)-100);
        meshView.setCullFace(CullFace.NONE);
        //meshView.setDrawMode(DrawMode.LINE);

        meshView.setRotationAxis(X_AXIS);
        meshView.setRotate(90);

        //ALIEN CONFIGURATION
        meshView.setTranslateX(420);
        meshView.setTranslateY(+150);
        meshView.setTranslateZ(-450);



        Rotate rotateX = new Rotate(0, pivotX, pivotY, pivotZ, X_AXIS);
        Rotate rotateY = new Rotate(0, pivotX, pivotY, pivotZ, Y_AXIS);
        Rotate rotateZ = new Rotate(0, pivotX, pivotY, pivotZ, Z_AXIS);

        meshView.getTransforms().addAll(rotateX, rotateY, rotateZ);

        scene.setOnMousePressed(event -> {
            anchorX = event.getSceneX();
            anchorY = event.getSceneY();
            anchorAngleX = rotateX.getAngle();
            anchorAngleY = rotateY.getAngle();
        });

        scene.setOnMouseDragged(event -> {
            if (event.isPrimaryButtonDown()) {
                rotateX.setAngle(anchorAngleX + anchorY - event.getSceneY());
                rotateZ.setAngle(anchorAngleY + anchorX - event.getSceneX());
            } else if (event.isSecondaryButtonDown()) {
                meshView.setTranslateX(meshView.getTranslateX() + event.getSceneX() - anchorX);
                meshView.setTranslateY(meshView.getTranslateY() + event.getSceneY() - anchorY);
                anchorX = event.getSceneX();
                anchorY = event.getSceneY();
            }
        });

        meshGroup = new Group(meshView);
        return meshGroup;
    }

    public void translate(String axis, float distance)
    {
        switch (axis) {
            case "x" -> meshView.setTranslateX(meshView.getTranslateX() + distance);
            case "y" -> meshView.setTranslateY(meshView.getTranslateY() + distance);
            case "z" -> meshView.setTranslateZ(meshView.getTranslateZ() + distance);
        }
    }

    public void rotate(String axis, float angle)
    {
        switch (axis) {
            case "x" -> meshView.getTransforms().add(new Rotate(angle, pivotX, pivotY, pivotZ, X_AXIS));
            case "y" -> meshView.getTransforms().add(new Rotate(angle, pivotX, pivotY, pivotZ, Y_AXIS));
            case "z" -> meshView.getTransforms().add(new Rotate(angle, pivotX, pivotY, pivotZ, Z_AXIS));
        }
    }

/*    public void rotate(String axis, float angle)
    {
        // Implement the code to rotate the meshView object
        if (axis.equals("x"))
            meshView.getTransforms().add(new Rotate(angle,pivotX, pivotY, pivotZ, Rotate.X_AXIS));
        else if (axis.equals("y"))
            meshView.getTransforms().add(new Rotate(angle,pivotX, pivotY, pivotZ, Rotate.Y_AXIS));
        else if (axis.equals("z"))
            meshView.getTransforms().add(new Rotate(angle,pivotX, pivotY, pivotZ, Rotate.Z_AXIS));
    }*/

    public void setStage(Stage stage)
    {
        this.stage = stage;
    }

    public void setPivotX(double pivotX) {
        this.pivotX = pivotX;
    }

    public void setPivotY(double pivotY) {
        this.pivotY = pivotY;
    }

    public void setPivotZ(double pivotZ) {
        this.pivotZ = pivotZ;
    }

    @FXML
    public void handleChangeMaterialMenuItemAction()
    {
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);

        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll("Red", "Green", "Blue", "Orange", "Yellow", "Black");
        choiceBox.setValue("Red");

        choiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            PhongMaterial material = new PhongMaterial();
            switch (newValue) {
                case "Red":
                    material.setDiffuseColor(Color.RED);
                    break;
                case "Green":
                    material.setDiffuseColor(Color.GREEN);
                    break;
                case "Blue":
                    material.setDiffuseColor(Color.BLUE);
                    break;
                case "Orange":
                    material.setDiffuseColor(Color.ORANGE);
                    break;
                case "Yellow":
                    material.setDiffuseColor(Color.YELLOW);
                    break;
                case "Black":
                    material.setDiffuseColor(Color.BLACK);
                    break;
            }
            meshView.setMaterial(material);
        });

        Button okButton = new Button("OK");
        okButton.setOnAction(e -> dialogStage.close());

        VBox vbox = new VBox(choiceBox, okButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(15));

        dialogStage.setScene(new Scene(vbox));
        dialogStage.show();
    }

    //Maybe move somewhere else
    public void startCommandThreads()
    {
        (new Thread(new CommandServer(1233))).start();
        while (!CommandServer.isAccepting()) {
            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
        }
        (new Thread(new CommandClient("127.0.0.1", 1233))).start();
    }

    public void setCamera(PerspectiveCamera camera)
    {
        this.camera = camera;
    }

    @FXML
    public void handleCloseButtonAction()
    {
        System.exit(0);
    }

    @FXML
    void handleAboutMenuItemAction(ActionEvent event)
    {
        Stage aboutStage = new Stage();
        aboutStage.setTitle(Literals.GUI_TITLE);

        Label aboutLabel = new Label(Literals.GUI_ABOUT_MENU);
        aboutLabel.setWrapText(true);
        aboutLabel.setPadding(new Insets(10));

        VBox aboutVBox = new VBox(aboutLabel);
        aboutVBox.setAlignment(Pos.CENTER);

        Scene aboutScene = new Scene(aboutVBox, 250, 120);
        aboutStage.setScene(aboutScene);
        aboutStage.initModality(Modality.APPLICATION_MODAL);
        aboutStage.showAndWait();
    }

    @FXML
    void handleContinuousRotateButtonPressed()
    {
        if (rotationTimeline == null)
        {
            rotationTimeline = new Timeline(new KeyFrame(Duration.millis(16), e ->
            {
                meshView.getTransforms().add(new Rotate(rotationSpeed/50, pivotX, pivotY, pivotZ, Z_AXIS));
            }));
            rotationTimeline.setCycleCount(Timeline.INDEFINITE);
        }
        rotationTimeline.play();
    }

    @FXML
    void handleContinuousRotateButtonReleased()
    {
        if (rotationTimeline != null)
        {
            rotationTimeline.stop();
        }
    }

    @FXML
    void handleRotationButtonUp()
    {
        meshView.getTransforms().add(new Rotate(10, pivotX, pivotY, pivotZ, X_AXIS));
    }

    @FXML
    void handleRotationButtonDown()
    {
        meshView.getTransforms().add(new Rotate(-10, pivotX, pivotY, pivotZ, X_AXIS));
    }

    @FXML
    void handleRotationButtonLeft()
    {
        meshView.getTransforms().add(new Rotate(-10, pivotX, pivotY, pivotZ, Z_AXIS));
    }

    @FXML
    void handleRotationButtonRight()
    {
        meshView.getTransforms().add(new Rotate(10, pivotX, pivotY, pivotZ, Z_AXIS));
    }

    @FXML
    void handleZoomInButton()
    {
        meshGroup.translateZProperty().set(meshGroup.getTranslateZ() - 20);
    }

    @FXML
    void handleZoomOutButton()
    {
        meshGroup.translateZProperty().set(meshGroup.getTranslateZ() + 10);
    }

    public void updatePolyhedron(Polyhedron polyhedron) {
        MeshController meshController = new MeshController();
        float[] points = meshController.mapPoints(polyhedron);
        int[] faces = meshController.createFaces(polyhedron);
        float[] textures = meshController.createTextures(polyhedron);
        int[] combinedFaces = meshController.mapFaces(faces, textures);
        Polygon3D.getInstance().setupMesh(points, textures, combinedFaces);
    }

    @FXML
    void handleGetCoordsButtonAction() {
        // Accessing translation properties
        double translateX = meshView.getTranslateX();
        double translateY = meshView.getTranslateY();
        double translateZ = meshView.getTranslateZ();

        // Assuming rotation is applied via Rotate transforms
        double rotationX = 0, rotationY = 0, rotationZ = 0;
        for (Transform transform : meshView.getTransforms()) {
            if (transform instanceof Rotate) {
                Rotate rotate = (Rotate) transform;
                Point3D axis = rotate.getAxis();
                if (axis.equals(X_AXIS)) {
                    rotationX += rotate.getAngle();
                } else if (axis.equals(Y_AXIS)) {
                    rotationY += rotate.getAngle();
                } else if (axis.equals(Z_AXIS)) {
                    rotationZ += rotate.getAngle();
                }
            }
        }

        // Printing the coordinates and rotations
        Console.log("Coordinates: X=" + translateX + ", Y=" + translateY + ", Z=" + translateZ);
        Console.log("Rotations: X=" + rotationX + "°, Y=" + rotationY + "°, Z=" + rotationZ + "°");
    }

    public void parseCommand(String input)
    {
        String[] parts = input.split(" ");
        Console.log("Command: " + parts[0] + "/" + parts[1] + "/" + parts[2]);
        String command = parts[0];
        if (command.equals("translate"))
        {

            // Perform translation
            String axis = parts[1];
            float distance = Float.parseFloat(parts[2]);

            // Call the method in MeshController class to translate
            GUIController.getInstance().translate(axis, distance);
            Console.log("Tatsächlich angesteuerter Controller: " + GUIController.getInstance());

            Console.log("Translation: (" + axis + "," + distance + ")");
        } else if (command.equals("rotate"))
        {
            // Perform rotation
            String axis = parts[1];
            float angle = Float.parseFloat(parts[2]);
            // Call the method in MeshController class to rotate
            GUIController.getInstance().rotate(axis, angle);
            Console.log("Rotation: (" + axis + "," + angle + ")");
        }
    }
}
