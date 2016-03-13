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
        switch (shapeType){
            case CIRCLE:
                final Shape shapeCircle = new Circonference(attr);
                return shapeCircle;
            case SQUARE:
                final Shape shapeSquare = new Square(attr);
                return shapeSquare;
            case LINE:
                final Shape shapeLine = new Line(attr);
                return shapeLine;
            case TRIANGLE:
                final Shape shapePolygon = new Polygon(attr);
                   return shapePolygon;
            default:
                return null;
        }
    }
}
