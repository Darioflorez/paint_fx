package uiComponents;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by dario on 2016-03-09.
 */
public class ResizableCanvas extends Canvas {

    public ResizableCanvas() {
        // Redraw uiComponents when size changes.
        widthProperty().addListener(evt -> draw());
        heightProperty().addListener(evt -> draw());
    }

    private void draw() {
        double width = getWidth();
        double height = getHeight();
//
//        GraphicsContext gc = getGraphicsContext2D();
//        gc.clearRect(0, 0, width, height);
//
//        gc.setStroke(Color.RED);
//        gc.strokeLine(0, 0, width, height);
//        gc.strokeLine(0, height, width, 0);
    }

    public void cleaCanvas(){
        double width = getWidth();
        double height = getHeight();

        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, width, height);
    }

    @Override
    public boolean isResizable() {
        return true;
    }

    @Override
    public double prefWidth(double height) {
        return getWidth();
    }

    @Override
    public double prefHeight(double width) {
        return getHeight();
    }
}
