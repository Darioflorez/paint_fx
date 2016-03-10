package models;


import enums.ShapeType;
import javafx.scene.paint.Color;

import java.io.Serializable;

/**
 * Created by johan on 09/03/16.
 */
public class Attribute implements Serializable {

    private double x;
    private double y;
    private int lineWidth;
    private boolean fill;
    private String colorStroke;
    private String colorFill;
    private ShapeType type;

    public Attribute(double x, double Y, int lineWidth, String colorStroke, String colorFill){
        this.x = x;
        this.y = Y;
        this.fill = true;
        this.lineWidth = lineWidth;
        this.colorStroke = colorStroke;
        this.colorFill = colorFill;
        this.type = null;
    }

    public Attribute(double x, double y, int lineWidth, String colorStroke) {
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

    public String getColorStroke() {
        return colorStroke;
    }

    public void setColorStroke(String colorStroke) {
        this.colorStroke = colorStroke;
    }

    public String getColorFill() {
        return colorFill;
    }

    public void setColorFill(String colorFill) {
        this.colorFill = colorFill;
    }

    public ShapeType getType() {
        return type;
    }

    public void setType(ShapeType type) {
        this.type = type;
    }
}
