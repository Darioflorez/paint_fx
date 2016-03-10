package interfaces;


import javafx.scene.canvas.GraphicsContext;
import models.Attribute;

import java.io.Serializable;

/**
 * Created by johan on 09/03/16.
 */
public abstract class Shape implements Serializable{

    public Attribute attribute;

    public Shape(Attribute attr){
        attribute = attr;
    }

    public abstract void draw(GraphicsContext gc);
}
