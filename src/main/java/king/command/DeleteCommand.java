package king.command;

import king.exception.KingException;
import king.storage.Storage;
import king.task.Task;
import king.task.TaskList;
import king.ui.Ui;

import java.io.IOException;

public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, KingException {
        Task removed = tasks.remove(index); //-1 cause oneBased
        ui.showDeleted(removed, tasks.size());
        storage.save(tasks.asList());
    }
}
