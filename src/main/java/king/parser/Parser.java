package king.parser;

import king.command.*;
import king.exception.KingException;
import king.task.Deadline;
import king.task.Event;
import king.task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public final class Parser {

    private Parser() {
        // prevent instantiation
    }

    private static final DateTimeFormatter INPUT_FORMAT =
            DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");

    public static Command parse(String input) throws KingException {
        String trimmed = input.trim();
        if (trimmed.isEmpty()) {
            throw new KingException("Please enter a command.");
        }

        String[] parts = trimmed.split(" ", 2);
        String cmd = parts[0];
        String args = (parts.length == 2) ? parts[1].trim() : "";

        return switch (cmd) {
            case "bye"      -> new ExitCommand();
            case "list"     -> new ListCommand();
            case "mark"     -> new MarkCommand(parseIndex(args), true);
            case "unmark"   -> new MarkCommand(parseIndex(args), false);
            case "delete"   -> new DeleteCommand(parseIndex(args));
            case "todo"     -> new TodoCommand(args);
            case "deadline" -> parseDeadline(args);
            case "event"    -> parseEvent(args);
            default         -> throw new KingException("No such Command");
        };
    }

    private static Command parseDeadline(String args) throws KingException {
        // expected: <desc> /by <d/MM/yyyy HHmm>
        if (args.isBlank()) {
            throw new KingException("king.task.Deadline format: deadline <desc> /by <d/MM/yyyy HHmm>");
        }

        String[] parts = args.split("/by", 2);
        if (parts.length < 2) {
            throw new KingException("king.task.Deadline format: deadline <desc> /by <d/MM/yyyy HHmm>");
        }

        String desc = parts[0].trim();
        String when = parts[1].trim();

        if (desc.isEmpty()) {
            throw new KingException("king.task.Deadline description cannot be empty.");
        }
        if (when.isEmpty()) {
            throw new KingException("king.task.Deadline must have a /by date time.");
        }

        LocalDateTime by = parseDateTime(when);
        return new DeadlineCommand(desc, by);
    }

    private static Command parseEvent(String args) throws KingException {
        // expected: <desc> /from <d/MM/yyyy HHmm> /to <d/MM/yyyy HHmm>
        if (args.isBlank()) {
            throw new KingException("king.task.Event format: event <desc> /from <d/MM/yyyy HHmm> /to <d/MM/yyyy HHmm>");
        }

        String[] first = args.split("/from", 2);
        if (first.length < 2) {
            throw new KingException("king.task.Event format: event <desc> /from <d/MM/yyyy HHmm> /to <d/MM/yyyy HHmm>");
        }

        String desc = first[0].trim();
        if (desc.isEmpty()) {
            throw new KingException("king.task.Event description cannot be empty.");
        }

        String[] second = first[1].split("/to", 2);
        if (second.length < 2) {
            throw new KingException("king.task.Event format: event <desc> /from <d/MM/yyyy HHmm> /to <d/MM/yyyy HHmm>");
        }

        String fromStr = second[0].trim();
        String toStr = second[1].trim();

        if (fromStr.isEmpty() || toStr.isEmpty()) {
            throw new KingException("king.task.Event must have both /from and /to date times.");
        }

        LocalDateTime from = parseDateTime(fromStr);
        LocalDateTime to = parseDateTime(toStr);

        // Optional but nice validation
        if (to.isBefore(from)) {
            throw new KingException("king.task.Event end time cannot be before start time.");
        }

        return new EventCommand(desc, from, to);
    }

    /**
     * Returns a king.task.Task object from parsing the String param
     *
     * @param line line from file
     * @return king.task.Task Object
     * @throws KingException if line is empty
     */
    public static Task parseTaskFromLine(String line) throws KingException {
        // "T | 0 | desc "
        // "D | 0 | desc | by"
        // "E | 0 | desc | from | to"
        String[] parts = line.split(" \\| ");

        String type = parts[0];
        boolean done = parts[1].equals("1");
        String desc = parts[2];

        Task task = switch (type) {
            case "T" -> new Task(desc);
            case "D" -> new Deadline(desc, LocalDateTime.parse(parts[3]));
            case "E" -> new Event(desc, LocalDateTime.parse(parts[3]), LocalDateTime.parse(parts[4]));
            default -> throw new KingException("Corrupted save line: " + line);
        };

        if (done) task.mark();
        return task;
    }

    private static LocalDateTime parseDateTime(String input) throws KingException {
        try {
            return LocalDateTime.parse(input.trim(), INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new KingException("Invalid date format. Use: d/MM/yyyy HHmm (e.g. 2/12/2019 1800)");
        }
    }

    private static int parseIndex(String arg) throws KingException {
        if (arg.isBlank()) throw new KingException("Missing task number.");
        try {
            int oneBased = Integer.parseInt(arg);
            if (oneBased < 1) throw new KingException("king.task.Task number must be >= 1.");
            return oneBased - 1;
        } catch (NumberFormatException e) {
            throw new KingException("Please provide a valid task number.");
        }
    }
}