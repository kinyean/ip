package king.command;

import king.storage.Storage;
import king.task.TaskList;
import king.ui.Ui;

/**
 * Command that exits the chatbot
 */
public class ExitCommand extends Command {

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        return new CommandResult("Bye. Hope to see you again soon!", true);
    }
}
