import java.io.IOException;
import java.time.LocalDateTime;

public class EventCommand extends Command {
    private final String desc;
    private final LocalDateTime from;
    private final LocalDateTime to;

    public EventCommand(String desc, LocalDateTime from, LocalDateTime to) {
        this.desc = desc;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, KingException {
        Task t = new Event(desc, from, to);
        tasks.add(t);
        ui.showAdded(t, tasks.size());
        storage.save(tasks.asList());
    }
}

