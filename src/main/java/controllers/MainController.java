package controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import models.Paint;
import utilities.Log;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by dario on 2016-03-04.
 */
public class MainController implements Initializable, EventHandler<MouseEvent>{


    // Declare all the components of the .fxml file you want to control
    @FXML //  fx:id="canvas"
    private Canvas canvas; // Value injected by FXMLLoader
    @FXML
    private AnchorPane canvasHolder;
    @FXML
    private Pane paneCanvas;

    // Tools buttons
    @FXML
    private Button buttonSelect;
    @FXML
    private Button buttonLineWidth;
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

    // Button Shapes
    @FXML
    private Shape buttonCircle;
    @FXML
    private Shape buttonLine;
    @FXML
    private Shape buttonSquare;

    //GraphicContext is used to draw on the canvas
    private GraphicsContext gc;

    // Figure Properties
    private Color colorStroke;
    private Color colorFill;
    private int lineWidth;


    private Paint paint;



    public void initialize(URL location, ResourceBundle resources) {
        // asset the ui elements
        assert canvas != null : "fx:id=\"canvas\" was not injected: check your FXML file 'main.fxml'.";
        assert buttonSelect != null : "fx:id=\"buttonSelect\" was not injected: check your FXML file 'main.fxml'.";
        assert canvasHolder != null : "fx:id=\"canvasHolder\" was not injected: check your FXML file 'main.fxml'.";
        assert choiceBox != null : "fx:id=\"choiceBox\" was not injected: check your FXML file 'main.fxml'.";
        // initialize your logic here: all @FXML variables will have been injected
        paint = new Paint();

        // Add css to the canvas so it looks like a piece of paper
        styleCanvas();

        // Init the default values used for the drawing shapes; t.ex colorStroke
        initDefaultValues();

        // Register the action handlers here
        // ==========================Tools===========================
        buttonSelect.addEventHandler(MouseEvent.MOUSE_CLICKED, this);

        buttonLineWidth.addEventHandler(MouseEvent.MOUSE_CLICKED, this);

        buttonFill.addEventHandler(MouseEvent.MOUSE_CLICKED, this);

        buttonDelete.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
        // ==========================Shapes===========================
        buttonCircle.addEventHandler(MouseEvent.MOUSE_CLICKED, this);

        buttonSquare.addEventHandler(MouseEvent.MOUSE_CLICKED, this);

        buttonLine.addEventHandler(MouseEvent.MOUSE_CLICKED, this);

        // ========================Color picker========================
        colorPickerStroke.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                colorStroke = colorPickerStroke.getValue();
                Log.i("Color Stroke: " + colorStroke);
            }
        });

        colorPickerFill.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                colorFill = colorPickerFill.getValue();
                Log.i("Color Fill: " + colorFill);
            }
        });

        // ======================== Canvas =================================
//        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED,
//                new EventHandler<MouseEvent>() {
//                    @Override
//                    public void handle(MouseEvent e) {
//                        gc.strokeRect(e.getX() - 2, e.getY() - 2, 5, 5);
//                    }
//                });
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
    }


    private void styleCanvas(){
        Log.i("CANVAS HOLDER X: " + canvasHolder.widthProperty() +  " Y: " + canvasHolder.heightProperty());
        // Add css to the panel that hold the canvas
        canvasHolder.getStyleClass().add("main_panel");
        // Add css to canvas
        paneCanvas.setPrefSize(canvasHolder.getWidth(), canvasHolder.getHeight());
        paneCanvas.getStyleClass().add("canvas");
    }

    // Call every time the user change the properties of the figure
    private void updateGraphicContext(){

    }

    private void initDefaultValues(){
        gc = canvas.getGraphicsContext2D();
        colorStroke = Color.BLACK;
        colorPickerStroke.setValue(colorStroke);
        colorFill = Color.WHITE;
        colorPickerFill.setValue(colorFill);
        lineWidth = 5;
        choiceBox.setItems(FXCollections.observableArrayList(
                "Line size", "1", "5 ", "10", "15", "20"));
        choiceBox.setValue("Line size");
    }

    private void drawCircle(GraphicsContext gc, double x, double y){
        Log.i("DRAW CIRCLE");

        // Draw the edges of a line
        gc.setLineWidth(lineWidth); // The width of the line
        gc.setStroke(colorStroke);
        gc.strokeOval(x, y, 80, 80); // (x, y, width, high)

        // Draw a fill circle
        gc.setFill(colorFill);
        gc.fillOval(x, y, 80, 80); // (x, y, width, high)

    }

    private void drawLine(GraphicsContext gc){
        Log.i("DRAW LINE");

        gc.setFill(colorFill);
        gc.setLineWidth(lineWidth);
        gc.strokeLine(220,100,240,100); // (x1, y1, x2, y2)

    }

    private void drawSquare(GraphicsContext gc){
        Log.i("DRAW SQUARE");

        // Draw the edges of a line
        gc.setLineWidth(lineWidth); // The width of the line
        gc.setStroke(colorStroke);
        gc.fillRect(30, 30, 80, 80);

        // Draw a fill square
        gc.setFill(colorFill);
        gc.strokeRect(30, 30, 80, 80);
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
                double x = event.getX();
                double y = event.getY();
                Log.i("Canvas X: " + x + " Y: " + y);
                drawCircle(gc, x, y);
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
            case "buttonCircle":
                Log.i("DRAW CIRCLE!");
                drawCircle(gc, 0,0);
                break;
            case "buttonSquare":
                Log.i("DRAW SQUARE!");
                drawSquare(gc);
                break;
            case "buttonLine":
                Log.i("DRAW LINE!");
                drawLine(gc);
                break;
            default:
                break;
        }

    }
}
