package king.command;

import king.storage.Storage;
import king.task.TaskList;
import king.ui.Ui;

/**
 * Command that exits the chatbot
 */
public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) { }

    @Override
    public boolean isExit() { return true; }
}
