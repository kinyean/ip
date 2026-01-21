public class Task {
    private Boolean isDone = false;
    private String desc;

    public Task(String desc) {
        this.desc = desc;
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

    @Override
    public String toString() {
        return this.desc;
    }

}
