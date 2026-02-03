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
        Task[] tasks = new Task[100];
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
            } else if (input.startsWith("todo ")) {
                String description = input.substring(5);
                Task newTask = new Todo(description);
                tasks[taskCount] = newTask;
                taskCount++;
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + newTask);
                System.out.println("Now you have " + taskCount + " tasks in the list.");
                System.out.println("===========================\n");
            } else if (input.startsWith("deadline ")) {
                String[] parts = input.substring(9).split(" /by ", 2);
                String description = parts[0];
                String by = parts[1];
                Task newTask = new Deadline(description, by);
                tasks[taskCount] = newTask;
                taskCount++;
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + newTask);
                System.out.println("Now you have " + taskCount + " tasks in the list.");
                System.out.println("===========================\n");
            } else if (input.startsWith("event ")) {
                String remaining = input.substring(6);
                String[] parts1 = remaining.split(" /from ", 2);
                String description = parts1[0];
                String[] parts2 = parts1[1].split(" /to ", 2);
                String from = parts2[0];
                String to = parts2[1];
                Task newTask = new Event(description, from, to);
                tasks[taskCount] = newTask;
                taskCount++;
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + newTask);
                System.out.println("Now you have " + taskCount + " tasks in the list.");
                System.out.println("===========================\n");
            } else {
                System.out.println("I don't understand that command!");
                System.out.println("===========================\n");
            }
        }
    }
}