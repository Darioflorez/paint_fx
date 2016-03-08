package controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Shape;
import utilities.Log;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by dario on 2016-03-04.
 */
public class MainController implements Initializable{


    // Declare all the components of the .fxml file you want to control
    @FXML //  fx:id="canvas"
    private Canvas canvas; // Value injected by FXMLLoader
    @FXML
    private Button buttonSelect;
    @FXML
    private Button buttonLineWidth;

    @FXML
    private AnchorPane canvasHolder;
    @FXML
    private Pane paneCanvas;
    @FXML
    private ColorPicker colorPicker;

    // Button Shapes
    @FXML
    private Shape buttonCircle;
    @FXML
    private Shape buttonLine;
    @FXML
    private Shape buttonSquare;

    //GraphicContext is used to draw on the canvas
    GraphicsContext gc;

    // Figure Properties
    private Color color;
    private int lineWidth;



    public void initialize(URL location, ResourceBundle resources) {
        assert canvas != null : "fx:id=\"canvas\" was not injected: check your FXML file 'main.fxml'.";
        assert buttonSelect != null : "fx:id=\"buttonSelect\" was not injected: check your FXML file 'main.fxml'.";
        assert canvasHolder != null : "fx:id=\"canvasHolder\" was not injected: check your FXML file 'main.fxml'.";

        // initialize your logic here: all @FXML variables will have been injected

        // Add css to the canvas so it looks like a piece of paper
        styleCanvas();

        // Init the default values used for the drawing shapes; t.ex color
        initDefaultValues();

        gc = canvas.getGraphicsContext2D();

        // Register the action handlers here
        buttonSelect.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                Log.i("Button Select Clicked!");
                drawOnCanvas();
            }
        });



        // ==========================Shapes===========================
        buttonCircle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                drawCircle(gc);
            }
        });

        buttonSquare.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                drawSquare(gc);
            }
        });

        buttonLine.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                drawLine(gc);
            }
        });



        colorPicker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                color = colorPicker.getValue();
                Log.i("Color Selected: " + color);
            }
        });
    }


    private void styleCanvas(){
        // Add css to canvas
        paneCanvas.getStyleClass().add("canvas");
        // Add css to the panel that hold the canvas
        canvasHolder.getStyleClass().add("main_panel");
    }

    private void drawOnCanvas(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawShape(gc);
    }

    private void drawShape(GraphicsContext gc){

//        gc.setLineWidth(5);
//        gc.strokeLine(40, 10, 10, 40);
//        gc.fillOval(10, 60, 30, 30);
//        gc.strokeOval(60, 60, 30, 30);
//        gc.fillRoundRect(110, 60, 30, 30, 10, 10);
//        gc.strokeRoundRect(160, 60, 30, 30, 10, 10);
//        gc.fillArc(10, 110, 30, 30, 45, 240, ArcType.OPEN);
//        gc.fillArc(60, 110, 30, 30, 45, 240, ArcType.CHORD);
//        gc.fillArc(110, 110, 30, 30, 45, 240, ArcType.ROUND);
//        gc.strokeArc(10, 160, 30, 30, 45, 240, ArcType.OPEN);
//        gc.strokeArc(60, 160, 30, 30, 45, 240, ArcType.CHORD);
//        gc.strokeArc(110, 160, 30, 30, 45, 240, ArcType.ROUND);
//        gc.fillPolygon(new double[]{10, 40, 10, 40},
//                new double[]{210, 210, 240, 240}, 4);
//        gc.strokePolygon(new double[]{60, 90, 60, 90},
//                new double[]{210, 210, 240, 240}, 4);
//        gc.strokePolyline(new double[]{110, 140, 110, 140},
//                new double[]{210, 210, 240, 240}, 4);
    }

    // Call every time the user change the properties of the figure
    private void updateGraphicContext(){

    }

    private void initDefaultValues(){
        if(color == null){
            color = Color.BLACK;
        }
        if (lineWidth == 0 ){
            lineWidth = 5;
        }
    }

    private void drawCircle(GraphicsContext gc){
        Log.i("DRAW CIRCLE");

        gc.setFill(Color.GOLD);
        gc.setLineWidth(10);
        gc.setStroke(color);
        gc.fillOval(10, 60, 30, 30);

    }

    private void drawLine(GraphicsContext gc){
        Log.i("DRAW LINE");

        gc.setFill(Color.RED);
        gc.setLineWidth(10);
        gc.setStroke(color);
        gc.fillOval(10, 60, 30, 40);

    }

    private void drawSquare(GraphicsContext gc){
        Log.i("DRAW SQUARE");

        gc.setFill(Color.BLACK);
        gc.setLineWidth(10);
        gc.setStroke(color);
        gc.fillOval(10, 60, 30, 50);
        gc.fillOval(10, 60, 30, 50);
    }

}
