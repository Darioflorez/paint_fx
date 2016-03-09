package models;

import javafx.scene.canvas.GraphicsContext;
import utilities.Log;

/**
 * Created by johan on 09/03/16.
 */
public class Circonference extends Shape{

    public int width, height;

    public Circonference(Attribute attr){
        super(attr);
        this.width = this.height = 80;
    }

    @Override
    public void draw(GraphicsContext gc) {

        // Draw the edges of a line
        gc.setLineWidth(attribute.getLineWidth());
        gc.setStroke(attribute.getColorStroke());
        gc.strokeOval(this.attribute.getX(), this.attribute.getY(), this.width, this.height);

        // Draw a fill circle
        gc.setFill(this.attribute.getColorFill());
        gc.fillOval(this.attribute.getX(), this.attribute.getY(), this.width, this.height);
    }
}
