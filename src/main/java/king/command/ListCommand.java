package king.command;

import king.storage.Storage;
import king.task.TaskList;
import king.ui.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks);
    }
}
