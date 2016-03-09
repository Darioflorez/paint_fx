package models;

import javafx.scene.canvas.GraphicsContext;

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
        /*gc.setLineWidth(lineWidth); // The width of the line
        gc.setStroke(colorStroke);
        gc.strokeOval(30, 30, 80, 80); // (x, y, width, high)

        // Draw a fill circle
        gc.setFill(colorFill);
        gc.fillOval(30, 30, 80, 80); // (x, y, width, high)*/

        // Draw the edges of a line
        gc.setLineWidth(this.attribute.getLineWWidth());
        gc.setStroke(this.attribute.getColorStroke());
        gc.strokeOval(this.attribute.getX(), this.attribute.getY(), this.width, this.height);

        // Draw a fill circle
        gc.setFill(this.attribute.getColorFill());
        gc.strokeOval(this.attribute.getX(), this.attribute.getY(), this.width, this.height);
    }
}
