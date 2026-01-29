public class Event extends Task{
    private String from;
    private String to;

    public Event(String description, String from, String to) throws KingException{
        super(description);
        this.from = from.trim();
        this.to = to.trim();
    }

    @Override
    public String toFileString() {
        return String.format("E | %d | %s | from: %s to: %s ", isDone? 1 : 0, this.desc, this.from, this.to);
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s)", this.getStatus(), super.desc, this.from, this.to);
    }
}
