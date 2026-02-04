package king.ui;

import king.task.Task;
import king.task.TaskList;

import java.util.List;
import java.util.Scanner;

public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    public void showWelcome() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm king.King");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public void showBye() {
        System.out.println(" Bye. Hope to see you again soon!");
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void showLoadingError(String msg) {
        System.out.println("Warning: Failed to load save file.");
        if (msg != null && !msg.isBlank()) {
            System.out.println("Reason: " + msg);
        }
    }

    public void showError(String msg) {
        System.out.println(msg);
    }

    public void showAdded(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public void showDeleted(Task task, int size) {
        System.out.println("Got it. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public void showMarked(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    public void showUnmarked(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    public void showList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public void showFindResults(List<Task> matches) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < matches.size(); i++) {
            System.out.println((i + 1) + ". " + matches.get(i));
        }
        if (matches.isEmpty()) {
            System.out.println("(no matches)");
        }
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

}

