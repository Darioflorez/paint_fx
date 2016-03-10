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
public class MainController implements Initializable, EventHandler<MouseEvent>{

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

        // Init the default values used for the drawing shapes; t.ex colorStroke
        initDefaultValues();

        // Register the action handlers here
        // ==========================Tools===========================
        buttonFill.addEventHandler(MouseEvent.MOUSE_CLICKED, this);

        buttonDelete.addEventHandler(MouseEvent.MOUSE_CLICKED, this);

        // ===================UNDO AND REDO===========================
        buttonUndo.setOnAction(event -> {
            Log.i("UNDO COMMAND!");
            Undo undo = new Undo(paint);
            CommitButton commit = new CommitButton(undo);
            commit.press();
        });

        buttonRedo.setOnAction(event -> {
            Log.i("REDO COMMAND!");
            Redo redo = new Redo(paint);
            CommitButton commit = new CommitButton(redo);
            commit.press();
        });

        // ===================SAVE AND LOAD===========================
        buttonSave.setOnAction(event -> {
            Log.i("Button SAVE Clicked!");
            Save save = new Save(paint);
            CommitButton commit = new CommitButton(save);
            commit.press();
        });
        buttonLoad.setOnAction(event -> {
            Log.i("Button LOAD Clicked!");
            LoadCanvas loadCanvas = new LoadCanvas(paint);
            CommitButton commit = new CommitButton(loadCanvas);
            commit.press();
        });

        // ========================Color picker========================
        colorPickerStroke.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                attr.setColorStroke(colorPickerStroke.getValue().toString());
                Log.i("Color Stroke: " + attr.getColorStroke().toString());
            }
        });

        colorPickerFill.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                attr.setColorFill(colorPickerFill.getValue().toString());
                Log.i("Color Fill: " + attr.getColorFill());
            }
        });
        // ======================== Canvas =================================
//        uiComponents.addEventHandler(MouseEvent.MOUSE_DRAGGED,
//                new EventHandler<MouseEvent>() {
//                    @Override
//                    public void handle(MouseEvent e) {
//                        gc.strokeRect(e.getX() - 2, e.getY() - 2, 5, 5);
//                    }
//                });
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, this);

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

        initChoiceBox();
        choiceBoxLine.setValue(lineSize[1]);
    }

    // ======================== ChoiceBox =============================
    private void initChoiceBox(){
        choiceBoxLine.setItems(FXCollections.observableArrayList(lineSize));
        choiceBoxLine.valueProperty().addListener((observable, oldValue, newValue) -> {
            Log.i("LINE WIDTH: " + newValue);
            attr.setLineWidth(newValue);
        });

        choiceBoxShapes.setItems(FXCollections.observableArrayList(ShapeType.values()));
        choiceBoxShapes.valueProperty().addListener((observable, oldValue, newValue) -> {
            Log.i("SHAPE SELECTED: " + newValue.toString());
            attr.setType(newValue);
        });
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

    //================================ Actions ================================
    @Override
    public void handle(MouseEvent event) {
        String evt = "";
        if(event.getSource() instanceof MenuItem){
            evt = ((MenuItem)event.getSource()).getId();
            Log.i( ((MenuItem)event.getSource()).getId());
        }
        else if(event.getSource() instanceof Control){
            evt = ((Control)event.getSource()).getId();
            //Log.i( ((Control)event.getSource()).getType() );
        }
        else if (event.getSource() instanceof Canvas){
            evt = ((Canvas)event.getSource()).getId();
            //Log.i(((Canvas)event.getSource()).getType());
        }
        switch (evt) {
            case "canvas":
                Log.i("DRAW ON CANVAS!");
//                double x = event.getX();
//                double y = event.getY();
                attr.setX(event.getX());
                attr.setY(event.getY());
                paint.draw(attr);
//              Log.i("Canvas X: " + x + " Y: " + y);
                break;
            case "buttonSelect":
                Log.i("Button Select Clicked!");
                break;
            case "buttonDelete":
                Log.i("Button DELETE Clicked!");
                break;
            case "buttonLineWidth":
                Log.i("Button LINE_WIDTH Clicked!");
                break;
            case "buttonFill":
                Log.i("Button FILL Clicked!");
                break;
            default:
                break;
        }

    }
}
