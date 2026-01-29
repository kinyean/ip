public class Deadline extends Task{

    private String by;

    public Deadline(String description, String by) throws KingException{
        super(description);
        this.by = by.trim();
    }

    @Override
    public String toFileString() {
        return String.format("D | %d | %s | %s", isDone? 1 : 0, this.desc, this.by);
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", this.getStatus(), super.desc, this.by);
    }
}
