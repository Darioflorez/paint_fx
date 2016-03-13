package command;

import models.Attribute;
import models.Paint;

/**
 * Created by dario on 2016-03-13.
 */
public class Draw implements Command {

    private Attribute attribute;
    private Paint paint;

    public Draw(Paint paint, Attribute attribute){
        this.paint = paint;
        this.attribute = attribute;
    }

    @Override
    public void execute() {
        paint.draw(attribute);
    }
}
