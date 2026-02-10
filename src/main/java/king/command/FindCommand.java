package king.command;

import king.exception.KingException;
import king.storage.Storage;
import king.task.Task;
import king.task.TaskList;

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
            String result = java.util.stream.IntStream.range(0, matches.size())
                    .mapToObj(i -> (i + 1) + ". " + matches.get(i))
                    .collect(java.util.stream.Collectors.joining("\n"));
            sb.append(result);
        }

        return new CommandResult(sb.toString().trim(), false);
        // no save needed: find doesn't mutate data
    }
}

