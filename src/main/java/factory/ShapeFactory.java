package factory;

import enums.ShapeType;
import interfaces.Shape;

/**
 * Created by dario on 2016-03-10.
 */
public class ShapeFactory {

    public Shape getShape(ShapeType shapeType){

        Shape shape = null;
        switch (shapeType){
            case CIRCLE:
                break;
            case SQUARE:
                break;
            case LINE:
                break;
            default:
                break;
        }
        return shape;
    }
}
