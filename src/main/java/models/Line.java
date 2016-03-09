package models;

import javafx.scene.canvas.GraphicsContext;

/**
 * Created by johan on 09/03/16.
 */
public class Line extends Shape{

    double x2, y2;

    public Line(Attribute attr){
        super(attr);
        this.x2 = this.attribute.getX() + 40;   // other positions x-value + 40px
        this.y2 = this.attribute.getY();        // same y-value
    }
    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(this.attribute.getColorFill());
        gc.setLineWidth(this.attribute.getLineWidth());
        gc.strokeLine(this.attribute.getX(), this.attribute.getY(), this.x2, this.y2); // (x1, y1, x2, y2)
    }
}
