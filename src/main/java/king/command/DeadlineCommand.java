package king.command;

import king.exception.KingException;
import king.storage.Storage;
import king.task.Deadline;
import king.task.Task;
import king.task.TaskList;
import king.ui.Ui;

import java.io.IOException;
import java.time.LocalDateTime;

public class DeadlineCommand extends Command {
    private final String desc;
    private final LocalDateTime by;

    public DeadlineCommand(String desc, LocalDateTime by) {
        this.desc = desc;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, KingException {
        Task t = new Deadline(desc, by);
        tasks.add(t);
        ui.showAdded(t, tasks.size());
        storage.save(tasks.asList());
    }
}

