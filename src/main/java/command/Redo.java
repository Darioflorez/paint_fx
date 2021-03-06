package command;

import interfaces.Shape;
import models.Attribute;
import models.Paint;

import java.util.List;
import java.util.Stack;

/**
 * Created by dario on 2016-03-10.
 */
public class Redo implements Command {

    private Paint paint;

    public Redo(Paint paint){
        this.paint = paint;
    }

    @Override
    public void execute() {
        paint.redo();
    }
}
