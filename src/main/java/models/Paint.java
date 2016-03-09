package models;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Control;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import utilities.Log;

/**
 * Created by dario on 2016-03-08.
 */
public class Paint {


    // Tools; What options the has before performing an action
    // Figure Properties
    private Color colorSelected;
    private int lineWidth;

    public Paint(){

    }


    // Actions; What the user can do with the appliation
    public void drawCircle(GraphicsContext gc){
        Log.i("DRAW CIRCLE");

        // Draw the edges of a line
        gc.setLineWidth(lineWidth); // The width of the line
        gc.setStroke(Color.BLACK);
        gc.strokeOval(30, 30, 80, 80); // (x, y, width, high)

        // Draw a fill circle
        gc.setFill(colorSelected);
        gc.fillOval(30, 30, 80, 80); // (x, y, width, high)

    }

    public void drawLine(GraphicsContext gc){
        Log.i("DRAW LINE");

        gc.setFill(Color.GOLD);
        gc.setLineWidth(lineWidth);
        gc.strokeLine(220,100,240,100); // (x1, y1, x2, y2)

    }

    public void drawSquare(GraphicsContext gc){
        Log.i("DRAW SQUARE");

        // Draw the edges of a line
        gc.setLineWidth(lineWidth); // The width of the line
        gc.setStroke(Color.BLACK);
        gc.fillRect(120, 30, 80, 80);

        // Draw a fill square
        gc.setFill(colorSelected);
        gc.strokeRect(120, 30, 80, 80);
    }

}
