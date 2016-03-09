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
    private GraphicsContext gc;

    public Paint(GraphicsContext gc){
        this.gc = gc;
    }

    public void draw(Attribute attr){
        switch (attr.getId()){
            case "circle":
                //Log.i("CIRCLE: " + " STROKE: "+ attr.getColorStroke() + " FILL: "+ attr.getColorFill());
                Circonference circle = new Circonference(attr);
                circle.draw(this.gc);
                break;
            case "square":
                Square sqr = new Square(attr);
                sqr.draw(this.gc);
                break;
            case "line":
                Line line = new Line(attr);
                line.draw(this.gc);
                break;
            default:
                break;
        }
    }

}
