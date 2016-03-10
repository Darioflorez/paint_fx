package command;

import models.Attribute;

/**
 * Created by dario on 2016-03-10.
 */
public interface CommandHandler {

    public void undo();
    public void redo();
    public void save();
    public void loadCanvas();
    public void draw(Attribute attr);
}
