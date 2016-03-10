package models;

import enums.ShapeType;
import interfaces.Shape;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by dario on 2016-03-08.
 */
public class Paint {

    private List<Shape> list;
    private GraphicsContext gc;
    private Stack<List<Shape>> stack;

    public Paint(GraphicsContext gc){
        this.gc = gc;
        this.list = new ArrayList<>();
    }

    public void draw(Attribute attr){
        /*TODO:
        *   Make a for loop that look that match actions with
        * */
//        ShapeType attrShapeType = attr.getType();
//        for (ShapeType type: ShapeType.values()){
//
//        }
        ShapeType shapeType = attr.getType();
        if( shapeType != null){
            switch (shapeType){
                case CIRCLE:
                    //Log.i("CIRCLE: " + " STROKE: "+ attr.getColorStroke() + " FILL: "+ attr.getColorFill());
                    Circonference circle = new Circonference(attr);
                    circle.draw(this.gc);
                    list.add(circle);
                    stack.add(list);
                    break;
                case SQUARE:
                    Square sqr = new Square(attr);
                    sqr.draw(this.gc);
                    list.add(sqr);
                    stack.add(list);
                    break;
                case LINE:
                    Line line = new Line(attr);
                    line.draw(this.gc);
                    list.add(line);
                    stack.add(list);
                    break;
                default:
                    break;
            }
        }
    }

}
