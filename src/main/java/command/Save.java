package command;

import models.Paint;

/**
 * Created by dario on 2016-03-10.
 */
public class Save implements Command {

    private Paint paint;

    public Save(Paint paint){
        this.paint = paint;
    }

    @Override
    public void execute() {
        paint.save();
    }
}
