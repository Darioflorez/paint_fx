package models;


import enums.ShapeType;
import javafx.scene.paint.Color;

/**
 * Created by johan on 09/03/16.
 */
public class Attribute {

    private double x;
    private double y;
    private int lineWidth;
    private boolean fill;
    private Color colorStroke;
    private Color colorFill;
    private ShapeType type;

    public Attribute(double x, double Y, int lineWidth, Color colorStroke, Color colorFill){
        this.x = x;
        this.y = Y;
        this.fill = true;
        this.lineWidth = lineWidth;
        this.colorStroke = colorStroke;
        this.colorFill = colorFill;
        this.type = null;
    }

    public Attribute(double x, double y, int lineWidth, Color colorStroke) {
        this.x = x;
        this.y = y;
        this.lineWidth = lineWidth;
        this.colorStroke = colorStroke;
        this.fill = false;
        this.type = null;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(int lineWidth) {
        this.lineWidth = lineWidth;
    }

    public boolean isFill() {
        return fill;
    }

    public void setFill(boolean fill) {
        this.fill = fill;
    }

    public Color getColorStroke() {
        return colorStroke;
    }

    public void setColorStroke(Color colorStroke) {
        this.colorStroke = colorStroke;
    }

    public Color getColorFill() {
        return colorFill;
    }

    public void setColorFill(Color colorFill) {
        this.colorFill = colorFill;
    }

    public ShapeType getType() {
        return type;
    }

    public void setType(ShapeType type) {
        this.type = type;
    }
}
