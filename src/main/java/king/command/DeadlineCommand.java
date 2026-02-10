package king.command;

import java.io.IOException;
import java.time.LocalDateTime;

import king.exception.KingException;
import king.storage.Storage;
import king.task.Deadline;
import king.task.Task;
import king.task.TaskList;

/**
 * Command that creates a task that have a deadline
 */
public class DeadlineCommand extends Command {
    private final String desc;
    private final LocalDateTime by;

    public DeadlineCommand(String desc, LocalDateTime by) {
        this.desc = desc;
        this.by = by;
    }

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) throws IOException, KingException {
        Task t = new Deadline(desc, by);
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

