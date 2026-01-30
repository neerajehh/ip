import java.util.Scanner;

public class Neeraj {
    public static void main(String[] args) {
        String logo = "===========================\n"
                + "        NEERAJ BOT        \n"
                + "===========================\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Neeraj");
        System.out.println("What can I do for you?");
        System.out.println("===========================\n");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            System.out.println("===========================");

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("===========================");
                break;
            } else {
                System.out.println(input);
                System.out.println("===========================\n");
            }
        }
    }
}