public class Event extends Task{
    private String from;
    private String to;

    public Event(String description, String from, String to) throws KingException{
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s)", this.getStatus(), super.desc, this.from, this.to);
    }
}
