package controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import models.*;
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
    /*private GraphicsContext gc;

    // Figure Properties
    private Color colorStroke;
    private Color colorFill;
    private int lineWidth;
    */

    private Paint paint;
    public Attribute attr;


    //=============================================
    private final String[] lineSize = new String[]{"Line size", "1", "5", "10", "15", "20"};



    public void initialize(URL location, ResourceBundle resources) {
        // asset the ui elements
        assert canvas != null : "fx:id=\"canvas\" was not injected: check your FXML file 'main.fxml'.";
        assert buttonSelect != null : "fx:id=\"buttonSelect\" was not injected: check your FXML file 'main.fxml'.";
        assert canvasHolder != null : "fx:id=\"canvasHolder\" was not injected: check your FXML file 'main.fxml'.";
        assert choiceBox != null : "fx:id=\"choiceBox\" was not injected: check your FXML file 'main.fxml'.";
        // initialize your logic here: all @FXML variables will have been injected
        paint = new Paint(canvas.getGraphicsContext2D());

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
//        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED,
//                new EventHandler<MouseEvent>() {
//                    @Override
//                    public void handle(MouseEvent e) {
//                        gc.strokeRect(e.getX() - 2, e.getY() - 2, 5, 5);
//                    }
//                });
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, this);

        // ======================== ChoiceBox =============================
        choiceBox.setItems(FXCollections.observableArrayList(lineSize));
        choiceBox.setValue("Line size");
        choiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                Log.i(lineSize[newValue.intValue()]);
                attr.setLineWidth(Integer.parseInt(lineSize[newValue.intValue()]));
            }
        });

        // ======================= Update new buttons ======================
//        Button button = new Button();
//        button.setText("Polygon");


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
        // Update Attribute object here!!
    }

    private void initDefaultValues(){
        colorPickerFill.setValue(Color.WHITE);
        colorPickerStroke.setValue(Color.BLACK);
        this.attr = new Attribute(30,30,5,
                colorPickerStroke.getValue(),
                colorPickerFill.getValue()); // X, Y, lineWidth, colorStroke, colorFill
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
                attr.setX(x);
                attr.setY(y);
                paint.draw(attr);
                Log.i("Canvas X: " + x + " Y: " + y);
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
                //shape = "circle";
                //this.paint.drawCircle(this.attr);
                attr.setId("circle");
                break;
            case "buttonSquare":
                Log.i("DRAW SQUARE!");
                //this.paint.drawSquare(this.attr);
                attr.setId("square");
                break;
            case "buttonLine":
                Log.i("DRAW LINE!");
                //this.paint.drawLine(this.attr);
                attr.setId("line");
                break;
            default:
                break;
        }

    }
}
