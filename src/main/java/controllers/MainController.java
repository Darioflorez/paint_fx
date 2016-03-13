package controllers;

import command.*;
import enums.ShapeType;
import facade.InputHandler;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import main.Main;
import models.*;
import models.Paint;
import uiComponents.ResizableCanvas;
import utilities.Log;
import utilities.Themes;

import java.net.URL;
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

    // Menu items
    @FXML
    private MenuItem buttonSave;
    @FXML
    private MenuItem buttonLoad;
    @FXML
    private MenuItem themeLight;
    @FXML
    private MenuItem themeBlack;
    @FXML
    private MenuItem themePink;

    // State buttons
    @FXML
    private Button buttonUndo;
    @FXML
    private Button buttonRedo;

    private Paint paint;
    private Attribute attr;

    private boolean fill;
    private boolean delete;

    private InputHandler inputHandler;

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

        this.fill =false;
        this.delete =false;
        inputHandler = new InputHandler();
    }

    //================================ Actions ================================
    private void initHandlers(){

        // Register the action handlers here
        // ==========================Tools===========================
        buttonFill.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            toogleFill();
            Log.i("Button FILL Clicked: " + fill);
        });

        buttonDelete.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            toogleDelete();
            fill = false;
            Log.i("Button Delete Clicked: " + delete);
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

        // ======================== ChoiceBox =============================
        choiceBoxLine.valueProperty().addListener((observable, oldValue, newValue) -> {
            Log.i("LINE WIDTH: " + newValue);
            attr.setLineWidth(newValue);
        });

        choiceBoxShapes.valueProperty().addListener((observable, oldValue, newValue) -> {
            Log.i("SHAPE SELECTED: " + newValue.toString());
            fill = false;
            attr.setType(newValue);
        });

        // ======================== Actions =================================

        // ===================UNDO AND REDO===========================
        buttonUndo.setOnAction(event -> {
            Log.i("UNDO COMMAND!");
            Undo undoCommand = new Undo(paint);
            inputHandler.undo(undoCommand);
        });

        buttonRedo.setOnAction(event -> {
            Log.i("REDO COMMAND!");
            Redo redoCommand = new Redo(paint);
            inputHandler.redo(redoCommand);
        });

        // ===================SAVE AND LOAD===========================
        buttonSave.setOnAction(event -> {
            Log.i("Button SAVE Clicked!");
            Save saveCommand = new Save(paint);
            inputHandler.save(saveCommand);
        });
        buttonLoad.setOnAction(event -> {
            Log.i("Button LOAD Clicked!");
            LoadCanvas loadCanvasCommand = new LoadCanvas(paint);
            inputHandler.load(loadCanvasCommand);
        });

        // ======================== Canvas =================================
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

            if(delete){
                Log.i("DELETE SHAPE!");
                Command deleteCommand = new Delete(paint,event.getX(), event.getY());
                inputHandler.delete(deleteCommand);
                delete = false;
            }
            else if(fill){
                Log.i("FILL SHAPE!");
                Command updateShapeCommand =
                        new UpdateShape(paint,event.getX(), event.getY(),
                        attr.getColorFill(), attr.getLineWidth() );
                inputHandler.updateShape(updateShapeCommand);
                fill = false;
            }
            else{
                Log.i("DRAW ON CANVAS!");
                attr.setX(event.getX());
                attr.setY(event.getY());

                Draw drawCommand = new Draw(paint, attr);
                inputHandler.draw(drawCommand);
            }
        });
//        uiComponents.addEventHandler(MouseEvent.MOUSE_DRAGGED,
//                new EventHandler<MouseEvent>() {
//                    @Override
//                    public void handle(MouseEvent e) {
//                        gc.strokeRect(e.getX() - 2, e.getY() - 2, 5, 5);
//                    }
//                });
    }

    private void toogleFill(){
        if(fill){
            fill = false;
        }
        else {
            fill = true;
        }
    }
    private void toogleDelete(){
        if(delete){
            delete = false;
        }
        else {
            delete = true;
        }
    }
}
