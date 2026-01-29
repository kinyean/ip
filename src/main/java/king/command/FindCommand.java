package king.command;

import king.exception.KingException;
import king.storage.Storage;
import king.task.Task;
import king.task.TaskList;
import king.ui.Ui;

import java.io.IOException;
import java.util.List;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KingException, IOException {
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new KingException("Find format: find <keyword>");
        }

        List<Task> matches = tasks.find(keyword.trim());
        ui.showFindResults(matches);
        // no save needed: find doesn't mutate data
    }
}

