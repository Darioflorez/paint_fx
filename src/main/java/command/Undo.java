package command;

import interfaces.Shape;

import java.util.List;
import java.util.Stack;

/**
 * Created by dario on 2016-03-10.
 */
public class Undo implements Command {

    private Stack<List<Shape>> stack;

    public Undo(Stack<List<Shape>> stack){
        this.stack = stack;
    }

    @Override
    public void execute() {
        stack.pop();
    }

}
