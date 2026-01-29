
public final class Parser {

    private Parser() {
        // prevent instantiation
    }

    /**
     * Returns a Task object from parsing the String param
     *
     * @param line line from file
     * @return Task Object
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
            case "D" -> new Deadline(desc, parts[3]);
            case "E" -> new Event(desc, parts[3], parts[4]);
            default -> throw new KingException("Corrupted save line: " + line);
        };

        if (done) task.mark();
        return task;
    }

}