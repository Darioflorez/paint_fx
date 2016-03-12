package factory;

import enums.ShapeType;
import interfaces.Shape;
import models.*;

/**
 * Created by dario on 2016-03-10.
 */
public class ShapeFactory {

    public static Shape getShape(Attribute attr){

        ShapeType shapeType = attr.getType();
        Shape shape = null;
        if(shapeType != null){
            switch (shapeType){
                case CIRCLE:
                    shape = new Circonference(attr);
                    break;
                case SQUARE:
                    shape = new Square(attr);
                    break;
                case LINE:
                    shape = new Line(attr);
                    break;
                case TRIANGLE:
                    shape = new Polygon(attr);
                    break;
                default:
                    break;
            }
        }
        return shape;
    }
}
