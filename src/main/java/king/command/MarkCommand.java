package king.command;

import king.exception.KingException;
import king.storage.Storage;
import king.task.Task;
import king.task.TaskList;

import java.io.IOException;

/**
 * Command that Mark or Unmark a selected task
 */
public class MarkCommand extends Command {

    private int index;
    private boolean isDone;

    public MarkCommand(int index, boolean isDone) {
        this.index = index;
        this.isDone = isDone;
    }

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) throws IOException, KingException {
        Task markTask = tasks.get(index);
        if (isDone) {
            markTask.mark();
            storage.save(tasks.asList());
            return new CommandResult("Nice! I've marked this task as done:\n  " + markTask, false);
        } else {
            markTask.unmark();
            storage.save(tasks.asList());
            return new CommandResult("OK, I've marked this task as not done yet:\n  " + markTask, false);
        }
    }
}
