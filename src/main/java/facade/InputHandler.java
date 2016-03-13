package facade;

import command.Command;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dario on 2016-03-12.
 */
public class InputHandler {

    private List<Command> commands;

    public InputHandler(){
        commands = new ArrayList<>();
    }

    public void save(Command command){
        command.execute();
    }

    public void load(Command command){
        command.execute();
    }

    public void undo(Command command){
        command.execute();
    }

    public void redo(Command command){
        command.execute();
    }

    public void draw(Command command){
        this.commands.add(command);
        command.execute();
    }

    public void updateShape(Command command){
        this.commands.add(command);
        command.execute();
    }

    public void delete(Command command){
        this.commands.add(command);
        command.execute();

    }
}
