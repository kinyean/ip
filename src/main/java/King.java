import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class King {
    public static void main(String[] args) throws KingException{
        Scanner scan = new Scanner(System.in);
        List<Task> tarr = new ArrayList<>();
        int counter = 0;
        String greet = "____________________________________________________________\n" +
                " Hello! I'm King\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        String exit = " Bye. Hope to see you again soon!\n";
        System.out.println(greet);
        while (true) {
            String[] s = scan.nextLine().split(" ",2);
            if (s[0].equals("mark")) {
                tarr.get(Integer.parseInt(s[1])-1).mark();
                System.out.println("Nice! I've marked this task as done:");
                System.out.printf(tarr.get(Integer.parseInt(s[1])-1).toString() + "\n");
                continue;
            } else if (s[0].equals("unmark")) {
                tarr.get(Integer.parseInt(s[1])-1).unmark();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.printf(tarr.get(Integer.parseInt(s[1])-1).toString() + "\n");
                continue;
            } else if (s[0].equals("bye")) {
                System.out.println(exit);
                break;
            } else if (s[0].equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < counter; i++) {
                    System.out.printf(i + 1 + ". " + tarr.get(i).toString() + "\n");
                }
                continue;
            } else if (s[0].equals("delete")) {
                Task temp = tarr.remove(Integer.parseInt(s[1])-1);
                counter--;
                System.out.println("Got it. I've removed this task:");
                System.out.println(temp);
                System.out.println("Now you have " + counter + " tasks in the list.");
                continue;
            } else if (s[0].equals("deadline")) {
                String[] temp = s[1].split("/by");
                tarr.add(new Deadline(temp[0].trim(), temp[1].trim()));
            } else if (s[0].equals("event")) {
                String[] temp = s[1].split("/from", 2);
                String[] temp2 = temp[1].split("/to");
                tarr.add(new Event(temp[0].trim(),temp2[0].trim(), temp2[1].trim()));
            } else if (s[0].equals("todo")){
                try {
                    String desc = s.length == 2? s[1] : "";
                    tarr.add(new Task(desc));
                } catch (KingException e) {
                    System.out.println(e.getMessage());
                    continue;
                }
            } else {
                try {
                    throw new KingException("No such Command");
                } catch (KingException e) {
                    System.out.println((e.getMessage()));
                    continue;
                }
            }
            System.out.println("Got it. I've added this task:");
            System.out.println(tarr.get(counter).toString());
            counter++;
            System.out.println("Now you have " + counter + " tasks in the list.");
        }
    }
}
