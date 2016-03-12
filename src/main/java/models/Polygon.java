package models;

import interfaces.Shape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by dario on 2016-03-12.
 */
public class Polygon extends Shape {


    public int width, height;

    public Polygon(Attribute attr){
        super(attr);
        this.width = this.height = 80;
    }

    @Override
    public void draw(GraphicsContext gc) {
        // Draw the edges of a line
        gc.setLineWidth(attribute.getLineWidth());
        gc.setStroke(Color.web(attribute.getColorStroke()));
        gc.strokePolygon(new double[]{attribute.getX(), attribute.getX() + width, attribute.getX()},
                new double[]{attribute.getY() + height, attribute.getY(), attribute.getY()}, 3);

        // Draw a fill circle
        gc.setFill(Color.web(this.attribute.getColorFill()));
        gc.fillPolygon(new double[]{attribute.getX(), attribute.getX() + width, attribute.getX()},
                new double[]{attribute.getY() + height, attribute.getY(), attribute.getY()}, 3);
    }
}
