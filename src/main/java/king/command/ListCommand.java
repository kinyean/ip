package king.command;

import king.storage.Storage;
import king.task.TaskList;
import king.ui.Ui;

/**
 * Command that shows the list of tasks
 */
public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks);
    }
}
