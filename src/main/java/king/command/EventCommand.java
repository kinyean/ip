package king.command;

import king.exception.KingException;
import king.storage.Storage;
import king.task.Event;
import king.task.Task;
import king.task.TaskList;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * A command that creates an Event task with two LocalDateTime
 */
public class EventCommand extends Command {
    private final String desc;
    private final LocalDateTime from;
    private final LocalDateTime to;

    public EventCommand(String desc, LocalDateTime from, LocalDateTime to) {
        assert desc != null : "Event description should not be null";
        assert from != null : "Event start time should not be null";
        assert to != null : "Event end time should not be null";
        assert !to.isBefore(from) : "Event end time must not be before start time";

        this.desc = desc;
        this.from = from;
        this.to = to;
    }

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) throws IOException, KingException {
        Task t = new Event(desc, from, to);
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

