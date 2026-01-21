import java.util.Scanner;

public class King {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] sarr = new String[100];
        int counter = 0;
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
            } else if (s.equals("list")) {
                for (int i = 0; i < counter; i++) {
                    System.out.println(i + 1 + "." + sarr[i]);
                }
            } else {
                sarr[counter] = s;
                counter++;
                System.out.println("added:" + s);
            }
        }
    }
}
