package command;

import models.Attribute;
import models.Paint;
import utilities.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Created by dario on 2016-03-10.
 */
public class Undo implements Command {

    private Paint paint;

    public Undo(Paint paint){
        this.paint = paint;
    }

    @Override
    public void execute() {
        paint.undo();
    }

}
