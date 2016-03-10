package command;

import models.Paint;

/**
 * Created by dario on 2016-03-10.
 */
public class LoadCanvas implements Command {

    private Paint paint;

    public LoadCanvas(Paint paint){
        this.paint = paint;
    }

    @Override
    public void execute() {
        paint.loadCanvas();
    }
}
