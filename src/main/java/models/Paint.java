package models;

import enums.ShapeType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

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
        /*TODO:
        *   Make a for loop that look that match actions with
        * */
//        ShapeType attrShapeType = attr.getType();
//        for (ShapeType type: ShapeType.values()){
//
//        }
        switch (attr.getType()){
            case CIRCLE:
                //Log.i("CIRCLE: " + " STROKE: "+ attr.getColorStroke() + " FILL: "+ attr.getColorFill());
                Circonference circle = new Circonference(attr);
                circle.draw(this.gc);
                break;
            case SQUARE:
                Square sqr = new Square(attr);
                sqr.draw(this.gc);
                break;
            case LINE:
                Line line = new Line(attr);
                line.draw(this.gc);
                break;
            default:
                break;
        }
    }

}
