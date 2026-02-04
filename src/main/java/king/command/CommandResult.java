package king.command;

public class CommandResult {
    public final String feedback;
    public final boolean isExit;

    public CommandResult(String feedback, boolean isExit) {
        this.feedback = feedback;
        this.isExit = isExit;
    }
}
