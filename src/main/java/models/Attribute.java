package models;


import javafx.scene.paint.Color;

/**
 * Created by johan on 09/03/16.
 */
public class Attribute {

    private int x, y, lineWWidth;
    private boolean fill;
    private Color colorStroke;
    private Color colorFill;

    public Attribute(int x, int Y, int lineWWidth, Color colorStroke, Color colorFill){
        this.x = x;
        this.y = Y;
        this.fill = true;
        this.lineWWidth = lineWWidth;
        this.colorStroke = colorStroke;
        this.colorFill = colorFill;
    }

    public Attribute(int x, int y, int lineWWidth, Color colorStroke) {
        this.x = x;
        this.y = y;
        this.lineWWidth = lineWWidth;
        this.colorStroke = colorStroke;
        this.fill = false;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getLineWWidth() {
        return lineWWidth;
    }

    public void setLineWWidth(int lineWWidth) {
        this.lineWWidth = lineWWidth;
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
}
