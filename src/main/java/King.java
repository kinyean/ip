import java.util.Scanner;

public class King {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Task[] tarr = new Task[100];
        int counter = 0;
        String greet = "____________________________________________________________\n" +
                " Hello! I'm King\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        String exit = " Bye. Hope to see you again soon!\n";
        System.out.println(greet);
        while (true) {
            String[] s = scan.nextLine().split(" ");
            if (s[0].equals("mark")) {
                tarr[Integer.parseInt(s[1])-1].mark();
                System.out.println("Nice! I've marked this task as done:");
                System.out.printf("[%s]" + tarr[Integer.parseInt(s[1])-1].toString() + "\n", tarr[Integer.parseInt(s[1])-1].getStatus());
            } else if (s[0].equals("unmark")) {
                tarr[Integer.parseInt(s[1])-1].unmark();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.printf("[%s]" + tarr[Integer.parseInt(s[1])-1].toString() + "\n", tarr[Integer.parseInt(s[1])-1].getStatus());
            } else if (s[0].equals("bye")) {
                System.out.println(exit);
                break;
            } else if (s[0].equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < counter; i++) {
                    System.out.printf(i + 1 + ".[%s] " + tarr[i].toString() + "\n", tarr[i].getStatus());
                }
            } else {
                tarr[counter] = new Task(s[0]);
                counter++;
                System.out.println("added:" + s[0]);
            }
        }
    }
}
