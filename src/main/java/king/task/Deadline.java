package king.task;

import king.exception.KingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDateTime by;

    public Deadline(String description, LocalDateTime by) throws KingException {
        super(description);
        this.by = by;
    }

    private static final DateTimeFormatter OUTPUT_FORMAT =
            DateTimeFormatter.ofPattern("MMM d yyyy, h:mma");

    @Override
    public String toFileString() {
        return String.format("D | %d | %s | %s", isDone? 1 : 0, this.desc, this.by);
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", this.getStatus(), super.desc, this.by.format(OUTPUT_FORMAT));
    }
}
