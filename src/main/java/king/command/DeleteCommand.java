package king.command;

import king.exception.KingException;
import king.storage.Storage;
import king.task.Task;
import king.task.TaskList;
import king.ui.Ui;

import java.io.IOException;

/**
 * Command that deletes a task
 */
public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }
    @Override
    public CommandResult execute(TaskList tasks, Storage storage) throws IOException, KingException {
        Task removed = tasks.remove(index);
        storage.save(tasks.asList());
        String message = "Noted. I've removed this task:\n"
                + "  " + removed + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";

        return new CommandResult(message, false);
    }
}
