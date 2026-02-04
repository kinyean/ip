package king.command;

import king.storage.Storage;
import king.task.TaskList;

/**
 * Command that shows the list of tasks
 */
public class ListCommand extends Command {

    @Override
    public CommandResult execute(TaskList tasks, Storage storage) {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return new CommandResult(sb.toString().trim(), false);

    }
}
