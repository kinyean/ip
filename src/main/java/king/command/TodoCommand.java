package king.command;

import king.exception.KingException;
import king.storage.Storage;
import king.task.Task;
import king.task.TaskList;
import king.ui.Ui;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, KingException {
        Task t = new Task(desc);
        tasks.add(t);
        ui.showAdded(t, tasks.size());
        storage.save(tasks.asList());
    }
}

