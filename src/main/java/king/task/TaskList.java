package king.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that holds and manipulate list of tasks
 */
public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public void add(Task t) {
        tasks.add(t);
    }

    public Task remove(int index) {
        return tasks.remove(index);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public List<Task> asList() {
        return tasks; // for storage.save(...)
    }

    /**
     * Return a list of tasks that contain the keyword in their description
     * @param keyword
     * @return List<Task>
     */
    public List<Task> find(String keyword) {
        String k = keyword.toLowerCase();
        List<Task> results = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getDescription().toLowerCase().contains(k)) {
                results.add(t);
            }
        }
        return results;
    }
    public boolean containsDuplicate(Task candidate) {
        assert candidate != null : "Candidate task should not be null";

        return tasks.stream().anyMatch(existing -> isDuplicateOf(existing, candidate));
    }

    private boolean isDuplicateOf(Task a, Task b) {
        if (!a.getClass().equals(b.getClass())) {
            return false;
        }

        String aDesc = a.getDescription().trim().toLowerCase();
        String bDesc = b.getDescription().trim().toLowerCase();
        if (!aDesc.equals(bDesc)) {
            return false;
        }

        // Type-specific checks
        if (a instanceof Deadline da && b instanceof Deadline db) {
            return da.getBy().equals(db.getBy());
        }

        if (a instanceof Event ea && b instanceof Event eb) {
            return ea.getFrom().equals(eb.getFrom()) && ea.getTo().equals(eb.getTo());
        }

        // Todo: same class + same normalized description is enough
        return true;
    }

}
