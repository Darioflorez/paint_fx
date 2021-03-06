package models;

import interfaces.Shape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by johan on 09/03/16.
 */
public class Square extends Shape {

    public int width, height;

    public Square(Attribute attr){
        super(attr);
        this.width = this.height = 80;
    }

    @Override
    public void draw(GraphicsContext gc) {

        gc.setLineWidth(this.attribute.getLineWidth()); // The width of the line
        gc.setStroke(Color.web(this.attribute.getColorStroke()));
        gc.strokeRect(this.attribute.getX(), this.attribute.getY(), this.width, this.height);

        // Draw a fill square
        gc.setFill(Color.web(this.attribute.getColorFill()));
        gc.fillRect(this.attribute.getX(), this.attribute.getY(), this.width, this.height);

    }
}
