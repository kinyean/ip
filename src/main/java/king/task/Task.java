package king.task;

import king.exception.KingException;

public class Task {
    protected Boolean isDone = false;
    protected String desc;

    public Task(String desc) throws KingException {
        if (desc.trim().isEmpty()) {
            throw new KingException("king.task.Task description cannot be empty");
        }
        this.desc = desc.trim();
    }

    public String getStatus() {
        return isDone? "X" : " ";
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String toFileString() {
        return String.format("T | %d | %s", isDone? 1 : 0, this.desc);
    }

    @Override
    public String toString() {
        return String.format("[T][%s] " + this.desc, this.getStatus());
    }

}
