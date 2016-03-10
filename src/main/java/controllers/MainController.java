package controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import models.*;
import uiComponents.ResizableCanvas;
import utilities.Log;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by dario on 2016-03-04.
 */
public class MainController implements Initializable, EventHandler<MouseEvent>{

    private final String[] lineSize = new String[]{"Line size", "1", "5", "10", "15", "20"};
    private final String[] shapesList = new String[]{"Shapes", "circle", "square", "line"};


    // Declare all the components of the .fxml file you want to control
//    @FXML
//    private Canvas canvas; // Value injected by FXMLLoader
    private ResizableCanvas canvas;

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
    private ChoiceBox choiceBox;
    @FXML
    private ChoiceBox choiceBoxShapes;

    // Button Shapes
    @FXML
    private Shape buttonCircle;
    @FXML
    private Shape buttonLine;
    @FXML
    private Shape buttonSquare;

    private Paint paint;
    public Attribute attr;

    public void initialize(URL location, ResourceBundle resources) {
        // asset the ui elements
        //assert canvas != null : "fx:id=\"uiComponents\" was not injected: check your FXML file 'main.fxml'.";
        //assert buttonSelect != null : "fx:id=\"buttonSelect\" was not injected: check your FXML file 'main.fxml'.";
        assert canvasHolder != null : "fx:id=\"canvasHolder\" was not injected: check your FXML file 'main.fxml'.";
        assert choiceBox != null : "fx:id=\"choiceBox\" was not injected: check your FXML file 'main.fxml'.";
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

        // ========================Color picker========================
        colorPickerStroke.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                attr.setColorStroke(colorPickerStroke.getValue());
                Log.i("Color Stroke: " + attr.getColorStroke());
            }
        });

        colorPickerFill.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                attr.setColorFill(colorPickerFill.getValue());
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

        // ======================== ChoiceBox =============================
        choiceBox.setItems(FXCollections.observableArrayList(lineSize));
        choiceBox.setValue(lineSize[0]);
        choiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                Log.i(lineSize[newValue.intValue()]);
                attr.setLineWidth(Integer.parseInt(lineSize[newValue.intValue()]));
            }
        });

        choiceBoxShapes.setItems(FXCollections.observableArrayList(shapesList));
        choiceBoxShapes.setValue(shapesList[0]);
        choiceBoxShapes.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                Log.i(shapesList[newValue.intValue()]);
                attr.setId(shapesList[newValue.intValue()]);
            }
        });

        // ======================= Update new buttons ======================
//        Button button = new Button();
//        button.setText("Polygon");


    }


    private void styleCanvas(){
        // Add css to the panel that hold the uiComponents
        canvasHolder.getStyleClass().add("main_panel");
        // Add css to uiComponents
        paneCanvas.getStyleClass().add("canvas");
    }

    private void initDefaultValues(){
        paint = new Paint(canvas.getGraphicsContext2D());
        colorPickerFill.setValue(Color.WHITE);
        colorPickerStroke.setValue(Color.BLACK);
        this.attr = new Attribute(30,30,5,
                colorPickerStroke.getValue(),
                colorPickerFill.getValue()); // X, Y, lineWidth, colorStroke, colorFill
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
        if(event.getSource() instanceof Shape){
            evt = ((Shape)event.getSource()).getId();
            //Log.i( ((Shape)event.getSource()).getId() );
        }
        else if(event.getSource() instanceof Control){
            evt = ((Control)event.getSource()).getId();
            //Log.i( ((Control)event.getSource()).getId() );
        }
        else if (event.getSource() instanceof Canvas){
            evt = ((Canvas)event.getSource()).getId();
            //Log.i(((Canvas)event.getSource()).getId());
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
