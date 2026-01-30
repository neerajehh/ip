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
        Task[] tasks = new Task[100];  // Changed from String[] to Task[]
        int taskCount = 0;

        while (true) {
            String input = scanner.nextLine();
            System.out.println("===========================");

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("===========================");
                break;
            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + "." + tasks[i]);
                }
                System.out.println("===========================\n");
            } else if (input.startsWith("mark ")) {
                int taskNumber = Integer.parseInt(input.substring(5)) - 1;
                tasks[taskNumber].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + tasks[taskNumber]);
                System.out.println("===========================\n");
            } else if (input.startsWith("unmark ")) {
                int taskNumber = Integer.parseInt(input.substring(7)) - 1;
                tasks[taskNumber].markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + tasks[taskNumber]);
                System.out.println("===========================\n");
            } else {
                Task newTask = new Task(input);  // Create Task object
                tasks[taskCount] = newTask;
                taskCount++;
                System.out.println("added: " + input);
                System.out.println("===========================\n");
            }
        }
    }
}