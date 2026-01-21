import java.util.Scanner;

public class King {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String greet = "____________________________________________________________\n" +
                " Hello! I'm King\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        String exit = " Bye. Hope to see you again soon!\n";
        System.out.println(greet);
        while (true) {
            String s = scan.nextLine();
            if (s.equals("bye")) {
                System.out.println(exit);
                break;
            }
            System.out.println(s);
        }
    }
}
