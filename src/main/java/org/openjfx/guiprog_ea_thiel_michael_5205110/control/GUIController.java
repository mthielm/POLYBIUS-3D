package org.openjfx.guiprog_ea_thiel_michael_5205110.control;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Point3D;
import javafx.geometry.Pos;
import javafx.scene.Group;
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
import org.openjfx.guiprog_ea_thiel_michael_5205110.util.Constants;
import org.openjfx.guiprog_ea_thiel_michael_5205110.util.Literals;
import org.openjfx.guiprog_ea_thiel_michael_5205110.view.Console;
import org.openjfx.guiprog_ea_thiel_michael_5205110.view.Polygon3D;

import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.transform.Rotate.*;

/**
 * Controller class for the GUI. Handles the GUI events and updates the GUI.
 * The GUIController class is a singleton class.
 * The GUIController class is the main controller class for the GUI.
 * The GUIController class is responsible for handling the GUI events and updating the GUI.
 *
 * @see javafx.fxml.Initializable
 */
public class GUIController implements Initializable
{
    /**
     * Singleton instance of the GUIController
     */
    private static GUIController instance = null;

    /**
     * Constructor of the GUIController
     */
    public GUIController()
    {
        if(instance == null)
            instance = this;
    }

    /**
     * Method to get the instance of the GUIController
     *
     * @return The instance of the GUIController
     */
    public static GUIController getInstance() {
        return instance;
    }

    /**
     * Anchor points for the rotation
     */
    double anchorX;
    /**
     * Anchor points for the rotation
     */
    double anchorY;
    /**
     * Rotation speed of the mesh
     */
    private double rotationSpeed = Constants.ONE;
    /**
     * Anchor angles for the rotation
     */
    private double anchorAngleX = Constants.ZERO;
    /**
     * Anchor angles for the rotation
     */
    private double anchorAngleY = Constants.ZERO;

    /**
     * Pivot points for the rotation
     */
    double pivotX;
    /**
     * Pivot points for the rotation
     */
    double pivotY;
    /**
     * Pivot points for the rotation
     */
    double pivotZ;
    /**
     * Timeline for the rotation
     */
    private Timeline rotationTimeline;

    /**
     * MeshView object for the 3D mesh
     */
    @FXML
    private MeshView meshView;

    /**
     * Group object for the 3D mesh
     */
    @FXML
    private Group meshGroup;

    /**
     * Menu item for the about-dialog
     */
    @FXML
    private MenuItem aboutMenuItem;

    /**
     * Menu item for the close button
     */
    @FXML
    public MenuItem closeButton;

    /**
     * Slider for the velocity
     */
    @FXML
    private Slider velocitySlider;

    /**
     * Text for the filename
     */
    @FXML
    private Text info_filename;

    /**
     * Text for the format
     */
    @FXML
    private Text info_format;

    /**
     * Text for the polygon count
     */
    @FXML
    private Text info_polygoncount;

    /**
     * Method to initialize the GUI
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        // This method is called whenever the velocitySlider value changes
        velocitySlider.valueProperty().addListener(
                (observableValue, number, t1) ->
                        rotationSpeed = (int) velocitySlider.getValue());

        aboutMenuItem.setOnAction(this::handleAboutMenuItemAction);
    }

    /**
     * Method to update the texts
     *
     * @param info1 The filename text
     * @param info2 The format text
     * @param info3 The polygon count text
     */
    public void updateTexts(String info1, String info2, String info3) {
        info_filename.setText(info1);
        info_format.setText(info2 + Literals.FILE);
        info_polygoncount.setText(info3 + Literals.POLYGONS);
    }

    /**
     * Method to draw the mesh
     *
     * @param scene The scene to draw the mesh in
     * @return The group object of the mesh
     */
    public Group drawMesh(Scene scene)
    {
        meshView = new MeshView(Polygon3D.getInstance());

        meshView.setMaterial(new PhongMaterial(Color.BLACK));
        meshView.setTranslateX((scene.getWidth()/Constants.TWO));
        meshView.setTranslateY((scene.getHeight()/Constants.TWO)
                -Constants.ONEHUNDRED);
        meshView.setCullFace(CullFace.NONE);
        meshView.setRotationAxis(X_AXIS);
        meshView.setRotate(Constants.NINETY);

        //ALIEN CONFIGURATION
        meshView.setTranslateX(Constants.FOURHUNDRETTWENTY);
        meshView.setTranslateY(+Constants.ONEHUNDRETFIFTY);
        meshView.setTranslateZ(-Constants.FOURHUNDRETFIFTY);

        Rotate rotateX = new Rotate(Constants.ZERO, pivotX, pivotY, pivotZ,
                X_AXIS);
        Rotate rotateY = new Rotate(Constants.ZERO, pivotX, pivotY, pivotZ,
                Y_AXIS);
        Rotate rotateZ = new Rotate(Constants.ZERO, pivotX, pivotY, pivotZ,
                Z_AXIS);

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
                meshView.setTranslateX(meshView.getTranslateX() +
                        event.getSceneX() - anchorX);
                meshView.setTranslateY(meshView.getTranslateY() +
                        event.getSceneY() - anchorY);
                anchorX = event.getSceneX();
                anchorY = event.getSceneY();
            }
        });

        meshGroup = new Group(meshView);
        return meshGroup;
    }

    /**
     * Method to translate the mesh
     *
     * @param axis The axis to translate
     * @param distance The distance to translate
     */
    public void translate(String axis, float distance)
    {
        switch (axis) {
            case Literals.X -> meshView.setTranslateX(meshView.getTranslateX() +
                    distance);
            case Literals.Y -> meshView.setTranslateY(meshView.getTranslateY() +
                    distance);
            case Literals.Z -> meshView.setTranslateZ(meshView.getTranslateZ() +
                    distance);
        }
    }

    /**
     * Method to rotate the mesh
     *
     * @param axis The axis to rotate
     * @param angle The angle to rotate
     */
    public void rotate(String axis, float angle)
    {
        switch (axis) {
            case Literals.X -> meshView.getTransforms().add(new Rotate(angle,
                    pivotX, pivotY, pivotZ, X_AXIS));
            case Literals.Y -> meshView.getTransforms().add(new Rotate(angle,
                    pivotX, pivotY, pivotZ, Y_AXIS));
            case Literals.Z -> meshView.getTransforms().add(new Rotate(angle,
                    pivotX, pivotY, pivotZ, Z_AXIS));
        }
    }

    /**
     * Method to set the stage
     *
     */
    public void setStage()
    {
        // Set the stage
    }

    /**
     * Method to set the pivot points
     *
     * @param pivotX The pivot point X
     */
    public void setPivotX(double pivotX) {
        this.pivotX = pivotX;
    }

    /**
     * Method to set the pivot points
     *
     * @param pivotY The pivot point Y
     */
    public void setPivotY(double pivotY) {
        this.pivotY = pivotY;
    }

    /**
     * Method to set the pivot points
     *
     * @param pivotZ The pivot point Z
     */
    public void setPivotZ(double pivotZ) {
        this.pivotZ = pivotZ;
    }

    /**
     * Method to handle the change material menu item action
     */
    @FXML
    public void handleChangeMaterialMenuItemAction()
    {
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);

        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll(Literals.RED, Literals.GREEN,
                                         Literals.BLUE, Literals.ORANGE,
                                         Literals.YELLOW, Literals.BLACK);
        choiceBox.setValue(Literals.RED);

        choiceBox.getSelectionModel().selectedItemProperty().addListener
                ((observable, oldValue, newValue) -> {
            PhongMaterial material = new PhongMaterial();
                    switch (newValue) {
                        case Literals.RED ->
                                material.setDiffuseColor(Color.RED);
                        case Literals.GREEN ->
                                material.setDiffuseColor(Color.GREEN);
                        case Literals.BLUE ->
                                material.setDiffuseColor(Color.BLUE);
                        case Literals.ORANGE ->
                                material.setDiffuseColor(Color.ORANGE);
                        case Literals.YELLOW ->
                                material.setDiffuseColor(Color.YELLOW);
                        case Literals.BLACK ->
                                material.setDiffuseColor(Color.BLACK);
                    }
            meshView.setMaterial(material);
        });

        Button okButton = new Button(Literals.OK);
        okButton.setOnAction(e -> dialogStage.close());

        VBox vbox = new VBox(choiceBox, okButton);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(Constants.FIFTEEN));

        dialogStage.setScene(new Scene(vbox));
        dialogStage.show();
    }

    /**
     * Method to set the camera
     *
     */
    public void setCamera()
    {
        // Set the camera
    }

    /**
     * Method to handle the close button action
     */
    @FXML
    public void handleCloseButtonAction()
    {
        System.exit(Constants.ZERO);
    }

    /**
     * Method to handle the about menu item action
     * @param event The event to handle
     */
    @FXML
    void handleAboutMenuItemAction(ActionEvent event)
    {
        Stage aboutStage = new Stage();
        aboutStage.setTitle(Literals.GUI_TITLE);

        Label aboutLabel = new Label(Literals.GUI_ABOUT_MENU);
        aboutLabel.setWrapText(true);
        aboutLabel.setPadding(new Insets(Constants.TEN));

        VBox aboutVBox = new VBox(aboutLabel);
        aboutVBox.setAlignment(Pos.CENTER);

        Scene aboutScene = new Scene(aboutVBox, Constants.TWOHUNDREDFIFTY,
                Constants.ONEHUNDREDTWENTY);
        aboutStage.setScene(aboutScene);
        aboutStage.initModality(Modality.APPLICATION_MODAL);
        aboutStage.showAndWait();
    }

    /**
     * Method to handle the continuous rotate button pressed
     */
    @FXML
    void handleContinuousRotateButtonPressed()
    {
        if (rotationTimeline == null)
        {
            rotationTimeline =
                    new Timeline(new KeyFrame(Duration.millis(Constants.SIXTEEN),
                    e ->
                            meshView.getTransforms().add(new Rotate(
                                    rotationSpeed/Constants.FIFTY,
                                    pivotX, pivotY, pivotZ, Z_AXIS))));
            rotationTimeline.setCycleCount(Timeline.INDEFINITE);
        }
        rotationTimeline.play();
    }

    /**
     * Method to handle the continuous rotate button released
     */
    @FXML
    void handleContinuousRotateButtonReleased()
    {
        if (rotationTimeline != null)
        {
            rotationTimeline.stop();
        }
    }

    /**
     * Method to handle the rotation button up
     */
    @FXML
    void handleRotationButtonUp()
    {
        meshView.getTransforms().add(new Rotate(Constants.TEN, pivotX, pivotY,
                pivotZ, X_AXIS));
    }

    /**
     * Method to handle the rotation button down
     */
    @FXML
    void handleRotationButtonDown()
    {
        meshView.getTransforms().add(new Rotate(-Constants.TEN, pivotX, pivotY,
                pivotZ, X_AXIS));
    }

    /**
     * Method to handle the rotation button left
     */
    @FXML
    void handleRotationButtonLeft()
    {
        meshView.getTransforms().add(new Rotate(-Constants.TEN, pivotX, pivotY,
                pivotZ, Z_AXIS));
    }

    /**
     * Method to handle the rotation button right
     */
    @FXML
    void handleRotationButtonRight()
    {
        meshView.getTransforms().add(new Rotate(Constants.TEN, pivotX, pivotY,
                pivotZ, Z_AXIS));
    }

    /**
     * Method to handle the zoom in button
     */
    @FXML
    void handleZoomInButton()
    {
        meshGroup.translateZProperty().set(meshGroup.getTranslateZ() -
                                           Constants.TWENTY);
    }

    /**
     * Method to handle the zoom out button
     */
    @FXML
    void handleZoomOutButton()
    {
        meshGroup.translateZProperty().set(meshGroup.getTranslateZ() +
                                           Constants.TEN);
    }

    /**
     * Method to update the polyhedron
     *
     * @param polyhedron The polyhedron to update
     */
    public void updatePolyhedron(Polyhedron polyhedron) {
        MeshController meshController = new MeshController();
        float[] points = meshController.mapPoints(polyhedron);
        int[] faces = meshController.createFaces(polyhedron);
        float[] textures = meshController.createTextures(polyhedron);
        int[] combinedFaces = meshController.mapFaces(faces, textures);
        Polygon3D.getInstance().setupMesh(points, textures, combinedFaces);
    }

    /**
     * Method to handle the get coordinates button action
     */
    @FXML
    void handleGetCoordsButtonAction() {
        // Accessing translation properties
        double translateX = meshView.getTranslateX();
        double translateY = meshView.getTranslateY();
        double translateZ = meshView.getTranslateZ();

        // Assuming rotation is applied via Rotate transforms
        double rotationX = Constants.ZERO, rotationY = Constants.ZERO,
               rotationZ = Constants.ZERO;
        for (Transform transform : meshView.getTransforms()) {
            if (transform instanceof Rotate rotate) {
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
        Console.log(Literals.COORDINATES_MSG_1 + translateX +
                         Literals.COORDINATES_MSG_2 + translateY +
                         Literals.COORDINATES_MSG_3 + translateZ);
        Console.log(Literals.ROTATION_MSG_1 + rotationX +
                         Literals.ROTATION_MSG_2 + rotationY +
                         Literals.ROTATION_MSG_3 + rotationZ +
                         Literals.ROTATION_MSG_4);
    }

    /**
     * Method to handle the reset button action
     */
    public void parseCommand(String input)
    {
        String[] parts = input.split(Literals.BACKSPACE);
        Console.log(Literals.COMMAND + parts[Constants.ZERO] +
                         Literals.SLASH + parts[Constants.ONE] +
                         Literals.SLASH + parts[Constants.TWO]);
        String command = parts[Constants.ZERO];
        if (command.equals(Literals.TRANSLATE))
        {

            // Perform translation
            String axis = parts[Constants.ONE];
            float distance = Float.parseFloat(parts[Constants.TWO]);

            // Call the method in MeshController class to translate
            GUIController.getInstance().translate(axis, distance);
            Console.log(Literals.CONTROLLER_MSG +
                    GUIController.getInstance());

            Console.log(Literals.TRANSLATION + axis + Literals.COMMA +
                    distance + Literals.BRACKET);
        } else if (command.equals(Literals.ROTATE))
        {
            // Perform rotation
            String axis = parts[Constants.ONE];
            float angle = Float.parseFloat(parts[Constants.TWO]);
            // Call the method in MeshController class to rotate
            GUIController.getInstance().rotate(axis, angle);
            Console.log(Literals.ROTATION + axis + Literals.COMMA +
                    angle + Literals.BRACKET);
        }
    }
}