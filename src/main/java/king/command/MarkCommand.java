package king.command;

import king.exception.KingException;
import king.storage.Storage;
import king.task.Task;
import king.task.TaskList;
import king.ui.Ui;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, KingException {
        Task markTask = tasks.get(index); //-1 because the list is oneBased
        if (isDone) {
            markTask.mark();
            ui.showMarked(markTask);
        } else {
            markTask.unmark();
            ui.showUnmarked(markTask);
        }
        storage.save(tasks.asList());
    }
}
