package models;

import enums.ShapeType;
import interfaces.Shape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import uiComponents.ResizableCanvas;
import utilities.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by dario on 2016-03-08.
 */
public class Paint {

    private List<Attribute> list;
    private GraphicsContext gc;

    public Paint(GraphicsContext gc){
        this.gc = gc;
        this.list = new ArrayList<>();
    }

    public List<Attribute> getList(){
        return this.list;
    }

    public void loadCanvas(List<Attribute> shapes){
        Log.i("---> " + shapes.size());
        for(Attribute atr: shapes){
            Log.i("X: " + atr.getX());
            Log.i("Y: " + atr.getY());
            draw(atr);
        }
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
                    list.add(circle.attribute.clone());
                    break;
                case SQUARE:
                    Square sqr = new Square(attr);
                    sqr.draw(this.gc);
                    list.add(sqr.attribute.clone());
                    break;
                case LINE:
                    Line line = new Line(attr);
                    line.draw(this.gc);
                    list.add(line.attribute.clone());
                    break;
                default:
                    break;
            }
        }
    }

}
