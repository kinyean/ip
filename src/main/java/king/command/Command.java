package king.command;

import king.exception.KingException;
import king.storage.Storage;
import king.task.TaskList;
import king.ui.Ui;

import java.io.IOException;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, KingException;
    public boolean isExit() {
        return false;
    }
}
