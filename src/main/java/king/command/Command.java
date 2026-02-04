package king.command;

import king.exception.KingException;
import king.storage.Storage;
import king.task.TaskList;

import java.io.IOException;

/**
 * Abstract class of all the commands that the user can give
 */
public abstract class Command {

    /**
     * Executes a certain function on the tasks
     * @param tasks a list of tasks in class TaskList
     * @param storage Storage where data is stored
     * @throws IOException
     * @throws KingException if an error occurs when manipulating tasks
     */
    public abstract CommandResult execute(TaskList tasks, Storage storage) throws IOException, KingException;
}
