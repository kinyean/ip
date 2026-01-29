import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;


public class King {
    public static void main(String[] args) throws KingException{
        Scanner scan = new Scanner(System.in);
        List<Task> tarr;
        Storage storage = new Storage("data/king.txt");
        DateTimeFormatter INPUT_FORMAT =
                DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

        String greet = "____________________________________________________________\n" +
                " Hello! I'm King\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";

        String exit = " Bye. Hope to see you again soon!\n";
        System.out.println(greet);

        try {
            tarr = storage.load();
        } catch (IOException | KingException e) {
            tarr = new ArrayList<>();
            System.out.println("Warning: Could not load save file. Starting with empty list.");
            System.out.println("Reason: " + e.getMessage());
        }

        while (true) {
            try {
                String[] s = scan.nextLine().trim().split(" ", 2);
                if (s[0].equals("mark")) {
                    tarr.get(Integer.parseInt(s[1]) - 1).mark();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.printf(tarr.get(Integer.parseInt(s[1]) - 1).toString() + "\n");
                    storage.save(tarr);
                    continue;
                } else if (s[0].equals("unmark")) {
                    tarr.get(Integer.parseInt(s[1]) - 1).unmark();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.printf(tarr.get(Integer.parseInt(s[1]) - 1).toString() + "\n");
                    storage.save(tarr);
                    continue;
                } else if (s[0].equals("bye")) {
                    System.out.println(exit);
                    break;
                } else if (s[0].equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tarr.size(); i++) {
                        System.out.printf(i + 1 + ". " + tarr.get(i).toString() + "\n");
                    }
                    continue;
                } else if (s[0].equals("delete")) {
                    Task temp = tarr.remove(Integer.parseInt(s[1]) - 1);
                    System.out.println("Got it. I've removed this task:");
                    System.out.println(temp);
                    System.out.println("Now you have " + tarr.size() + " tasks in the list.");
                    storage.save(tarr);
                    continue;
                } else if (s[0].equals("deadline")) {
                    String[] temp = s[1].split("/by");
                    tarr.add(new Deadline(temp[0].trim(), LocalDateTime.parse(temp[1].trim(),INPUT_FORMAT)));
                    storage.save(tarr);
                } else if (s[0].equals("event")) {
                    String[] temp = s[1].split("/from", 2);
                    String[] temp2 = temp[1].split("/to");
                    tarr.add(new Event(temp[0].trim(), LocalDateTime.parse(temp2[0].trim(),INPUT_FORMAT),
                            LocalDateTime.parse(temp2[1].trim(),INPUT_FORMAT)));
                    storage.save(tarr);
                } else if (s[0].equals("todo")) {
                    String desc = s.length == 2 ? s[1] : "";
                    tarr.add(new Task(desc));
                    storage.save(tarr);
                } else {
                    throw new KingException("No such Command");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }
            System.out.println("Got it. I've added this task:");
            System.out.println(tarr.get(tarr.size()-1).toString());
            System.out.println("Now you have " + tarr.size() + " tasks in the list.");
        }
    }
}
