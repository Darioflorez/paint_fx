package controllers;

import command.*;
import enums.ShapeType;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import models.*;
import models.Paint;
import uiComponents.ResizableCanvas;
import utilities.FileHandler;
import utilities.Log;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by dario on 2016-03-04.
 */
public class MainController implements Initializable {

    private final Integer[] lineSize = new Integer[]{1,5,10,15,20};

    // Declare all the components of the .fxml file you want to control
    private ResizableCanvas canvas; // Value injected by FXMLLoader

    @FXML
    private AnchorPane canvasHolder;
    @FXML
    private Pane paneCanvas;

    // Tools buttons
    @FXML
    private Button buttonDelete;
    @FXML
    private Button buttonFill;
    @FXML
    private ColorPicker colorPickerStroke;
    @FXML
    private ColorPicker colorPickerFill;
    @FXML
    private ChoiceBox<Integer> choiceBoxLine;
    @FXML
    private ChoiceBox<ShapeType> choiceBoxShapes;
    @FXML
    private MenuItem buttonSave;
    @FXML
    private MenuItem buttonLoad;

    // State buttons
    @FXML
    private Button buttonUndo;
    @FXML
    private Button buttonRedo;

    private Paint paint;
    public Attribute attr;

    public void initialize(URL location, ResourceBundle resources) {
        // initialize your logic here: all @FXML variables will have been injected
        initResizableCanvas();

        // Add css to the uiComponents so it looks like a piece of paper
        styleCanvas();

        initHandlers();

        // Init the default values used for the drawing shapes; t.ex colorStroke
        initDefaultValues();
    }

    private void initResizableCanvas(){
        // Bind canvas size to stack pane size.
        canvas = new ResizableCanvas();
        canvas.setId("canvas");
        paneCanvas.getChildren().add(canvas);
        canvas.widthProperty().bind(
                paneCanvas.widthProperty());
        canvas.heightProperty().bind(
                paneCanvas.heightProperty());
    }

    private void styleCanvas(){
        // Add css to the panel that hold the uiComponents
        canvasHolder.getStyleClass().add("main_panel");
        // Add css to uiComponents
        paneCanvas.getStyleClass().add("canvas");
    }

    private void initDefaultValues(){
        paint = new Paint(canvas);
        colorPickerFill.setValue(Color.WHITE);
        colorPickerStroke.setValue(Color.BLACK);

        this.attr = new Attribute(30,30,lineSize[1],
                colorPickerStroke.getValue().toString(),
                colorPickerFill.getValue().toString()); // X, Y, lineWidth, colorStroke, colorFill

        choiceBoxLine.setItems(FXCollections.observableArrayList(lineSize));
        choiceBoxShapes.setItems(FXCollections.observableArrayList(ShapeType.values()));
        choiceBoxLine.setValue(lineSize[1]);
    }

    //================================ Actions ================================
    private void initHandlers(){

        // Register the action handlers here
        // ===================UNDO AND REDO===========================
        buttonUndo.setOnAction(event -> {
            Log.i("UNDO COMMAND!");
            Undo undo = new Undo(paint);
            new CommitButton(undo).press();
        });

        buttonRedo.setOnAction(event -> {
            Log.i("REDO COMMAND!");
            Redo redo = new Redo(paint);
            new CommitButton(redo).press();
        });

        // ===================SAVE AND LOAD===========================
        buttonSave.setOnAction(event -> {
            Log.i("Button SAVE Clicked!");
            Save save = new Save(paint);
            new CommitButton(save).press();
        });
        buttonLoad.setOnAction(event -> {
            Log.i("Button LOAD Clicked!");
            LoadCanvas loadCanvas = new LoadCanvas(paint);
            new CommitButton(loadCanvas).press();
        });
        // ========================Color picker========================
        colorPickerStroke.setOnAction(event -> {
            attr.setColorStroke(colorPickerStroke.getValue().toString());
            Log.i("Color Stroke: " + attr.getColorStroke());
        });

        colorPickerFill.setOnAction(event ->  {
            attr.setColorFill(colorPickerFill.getValue().toString());
            Log.i("Color Fill: " + attr.getColorFill());
        });

        // ==========================Tools===========================
        buttonFill.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Log.i("Button DELETE Clicked!");
        });

        buttonDelete.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Log.i("Button DELETE Clicked!");
        });

        // ======================== ChoiceBox =============================
        //choiceBoxLine.setItems(FXCollections.observableArrayList(lineSize));
        choiceBoxLine.valueProperty().addListener((observable, oldValue, newValue) -> {
            Log.i("LINE WIDTH: " + newValue);
            attr.setLineWidth(newValue);
        });

        //choiceBoxShapes.setItems(FXCollections.observableArrayList(ShapeType.values()));
        choiceBoxShapes.valueProperty().addListener((observable, oldValue, newValue) -> {
            Log.i("SHAPE SELECTED: " + newValue.toString());
            attr.setType(newValue);
        });

        // ======================== Canvas =================================
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Log.i("DRAW ON CANVAS!");
//                double x = event.getX();
//                double y = event.getY();
            attr.setX(event.getX());
            attr.setY(event.getY());
            paint.draw(attr);
        });
//        uiComponents.addEventHandler(MouseEvent.MOUSE_DRAGGED,
//                new EventHandler<MouseEvent>() {
//                    @Override
//                    public void handle(MouseEvent e) {
//                        gc.strokeRect(e.getX() - 2, e.getY() - 2, 5, 5);
//                    }
//                });
    }
}
