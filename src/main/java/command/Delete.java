package command;

import models.Paint;

/**
 * Created by dario on 2016-03-13.
 */
public class Delete implements Command {

    private Paint paint;
    private double x, y;

    public Delete (Paint paint, double x, double y){
        this.paint = paint;
        this.x = x;
        this.y = y;
    }

    @Override
    public void execute() {
        paint.delete(x,y);
    }
}
