import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private LocalDateTime from;
    private LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) throws KingException{
        super(description);
        this.from = from;
        this.to = to;
    }

    private static final DateTimeFormatter OUTPUT_FORMAT =
            DateTimeFormatter.ofPattern("MMM d yyyy, h:mma");

    @Override
    public String toFileString() {
        return String.format("E | %d | %s | %s | %s ", isDone? 1 : 0,
                this.desc, this.from.format(OUTPUT_FORMAT), this.to.format(OUTPUT_FORMAT));
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s)", this.getStatus(),
                super.desc, this.from.format(OUTPUT_FORMAT), this.to.format(OUTPUT_FORMAT));
    }
}
