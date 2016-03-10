package command;

/**
 * Created by dario on 2016-03-10.
 */
public class CommitButton {

    private Command command;

    public CommitButton(Command command){
        this.command = command;
    }

    public void press(){
        command.execute();
    }
}
