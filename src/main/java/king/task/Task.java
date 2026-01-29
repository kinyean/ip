package king.task;

import king.exception.KingException;

/**
 * Class that holds information about the task, a string description
 * as well as a boolean to track if it is completed
 */
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

    /**
     * Returns a string to be stored in storage
     * @return a String
     */
    public String toFileString() {
        return String.format("T | %d | %s", isDone? 1 : 0, this.desc);
    }

    @Override
    public String toString() {
        return String.format("[T][%s] " + this.desc, this.getStatus());
    }

}
