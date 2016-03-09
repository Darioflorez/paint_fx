package models;


import javafx.scene.canvas.GraphicsContext;

/**
 * Created by johan on 09/03/16.
 */
public abstract class Shape {

    public Attribute attribute;

    public Shape(Attribute attr){
        this.attribute = attr;
    }

    public abstract void draw(GraphicsContext gc);
}
