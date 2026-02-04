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
    public CommandResult execute(TaskList tasks, Storage storage) throws KingException, IOException {
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new KingException("Find format: find <keyword>");
        }

        List<Task> matches = tasks.find(keyword.trim());
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");

        if (matches.isEmpty()) {
            sb.append("(no matches)");
        } else {
            for (int i = 0; i < matches.size(); i++) {
                sb.append(i + 1).append(". ").append(matches.get(i)).append("\n");
            }
        }

        return new CommandResult(sb.toString().trim(), false);
        // no save needed: find doesn't mutate data
    }
}

