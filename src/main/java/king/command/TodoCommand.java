package king.command;

import king.exception.KingException;
import king.storage.Storage;
import king.task.Task;
import king.task.TaskList;

import java.io.IOException;

/**
 * Command that creates a basic Task
 */
public class TodoCommand extends Command {
    private final String desc;

    public TodoCommand(String desc) {
        this.desc = desc;
    }

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) throws IOException, KingException {
        Task t = new Task(desc);
        if (tasks.containsDuplicate(t)) {
            throw new KingException("Duplicate task detected:\n  " + t);
        }
        tasks.add(t);
        storage.save(tasks.asList());
        String message = "Got it. I've added this task:\n"
                + "  " + t + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";

        return new CommandResult(message, false);

    }
}

