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
        String[] tasks = new String[100];  // Store up to 100 tasks
        int taskCount = 0;                 // Track number of tasks

        while (true) {
            String input = scanner.nextLine();
            System.out.println("===========================");

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("===========================");
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                System.out.println("===========================\n");
            } else {
                tasks[taskCount] = input;
                taskCount++;
                System.out.println("added: " + input);
                System.out.println("===========================\n");
            }
        }
    }
}