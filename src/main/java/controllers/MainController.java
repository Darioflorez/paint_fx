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

    // Figure Properties
    private Color color;
    private int lineWidth;



    public void initialize(URL location, ResourceBundle resources) {
        assert canvas != null : "fx:id=\"canvas\" was not injected: check your FXML file 'main.fxml'.";
        assert buttonSelect != null : "fx:id=\"buttonSelect\" was not injected: check your FXML file 'main.fxml'.";
        assert canvasHolder != null : "fx:id=\"canvasHolder\" was not injected: check your FXML file 'main.fxml'.";

        // initialize your logic here: all @FXML variables will have been injected
        initCanvas();
        initPaneCanvas();
        // Register the action handlers here
        buttonSelect.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                Log.i("Button Select Clicked!");
                drawOnCanvas();
            }
        });

        buttonCircle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                drawOnCanvas();
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

    private void initPaneCanvas(){
        paneCanvas.getStyleClass().add("canvas");
    }
    private void initCanvas(){
        canvasHolder.getStyleClass().add("main_panel");
    }

    private void drawOnCanvas(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawShape(gc);
    }

    private void drawShape(GraphicsContext gc){
        if(color == null){
            Log.i("COLOR WAS NULL!");
            color = Color.BLACK;
        }
        if (lineWidth == 0 ){
            lineWidth = 5;
        }

        gc.setFill(Color.GOLD);
        gc.setStroke(color);
        gc.fillOval(10, 60, 30, 30);

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

    private void drawCircle(){

    }

    private void drawLine(){

    }

    private void drawSquare(){

    }

}
