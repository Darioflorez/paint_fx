package command;

import models.Attribute;
import models.Paint;

/**
 * Created by dario on 2016-03-13.
 */
public class UpdateShape implements Command {

    private double x, y;
    private String color;
    private int lineSize;
    private Paint paint;

    public UpdateShape(Paint paint, double x, double y, String color, int lineSize){
        this.paint = paint;
        this.x = x;
        this.y = y;
        this.lineSize = lineSize;
        this.color = color;
    }

    @Override
    public void execute() {
        paint.fill(x,y,color,lineSize);
    }
}
