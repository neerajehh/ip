import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main class for Neeraj chatbot.
 * Manages tasks including todos, deadlines, and events.
 */
public class Neeraj {
    private static final String LINE = "===========================";
    private ArrayList<Task> tasks;
    private Scanner scanner;

    /**
     * Creates a new Neeraj chatbot.
     */
    public Neeraj() {
        this.tasks = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Runs the chatbot program.
     */
    public void run() {
        showWelcome();

        boolean isExit = false;
        while (!isExit) {
            String input = scanner.nextLine();
            System.out.println(LINE);
            isExit = executeCommand(input);
        }

        scanner.close();
    }

    /**
     * Displays the welcome message.
     */
    private void showWelcome() {
        String logo = LINE + "\n"
                + "        NEERAJ BOT        \n"
                + LINE + "\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Neeraj");
        System.out.println("What can I do for you?");
        System.out.println(LINE + "\n");
    }

    /**
     * Executes a command based on user input.
     *
     * @param input The user's input.
     * @return true if the command is to exit, false otherwise.
     */
    private boolean executeCommand(String input) {
        if (input.equals("bye")) {
            handleBye();
            return true;
        } else if (input.equals("list")) {
            handleList();
        } else if (input.startsWith("mark ")) {
            handleMark(input);
        } else if (input.startsWith("unmark ")) {
            handleUnmark(input);
        } else if (input.startsWith("todo ")) {
            handleTodo(input);
        } else if (input.startsWith("deadline ")) {
            handleDeadline(input);
        } else if (input.startsWith("event ")) {
            handleEvent(input);
        } else {
            handleUnknownCommand();
        }
        return false;
    }

    /**
     * Handles the bye command.
     */
    private void handleBye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    /**
     * Handles the list command.
     */
    private void handleList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        System.out.println(LINE + "\n");
    }

    /**
     * Handles the mark command.
     *
     * @param input The user input.
     */
    private void handleMark(String input) {
        try {
            int taskNumber = Integer.parseInt(input.substring(5).trim()) - 1;
            if (taskNumber < 0 || taskNumber >= tasks.size()) {
                throw new NeerajException("OOPS!!! Invalid task number.");
            }
            Task task = tasks.get(taskNumber);
            task.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + task);
            System.out.println(LINE + "\n");
        } catch (NumberFormatException e) {
            System.out.println("OOPS!!! Please provide a valid task number.");
            System.out.println(LINE + "\n");
        } catch (NeerajException e) {
            System.out.println(e.getMessage());
            System.out.println(LINE + "\n");
        }
    }

    /**
     * Handles the unmark command.
     *
     * @param input The user input.
     */
    private void handleUnmark(String input) {
        try {
            int taskNumber = Integer.parseInt(input.substring(7).trim()) - 1;
            if (taskNumber < 0 || taskNumber >= tasks.size()) {
                throw new NeerajException("OOPS!!! Invalid task number.");
            }
            Task task = tasks.get(taskNumber);
            task.markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + task);
            System.out.println(LINE + "\n");
        } catch (NumberFormatException e) {
            System.out.println("OOPS!!! Please provide a valid task number.");
            System.out.println(LINE + "\n");
        } catch (NeerajException e) {
            System.out.println(e.getMessage());
            System.out.println(LINE + "\n");
        }
    }

    /**
     * Handles the todo command.
     *
     * @param input The user input.
     */
    private void handleTodo(String input) {
        try {
            String description = input.substring(5).trim();
            if (description.isEmpty()) {
                throw new NeerajException("OOPS!!! The description of a todo cannot be empty.");
            }
            Task newTask = new Todo(description);
            addTask(newTask);
        } catch (NeerajException e) {
            System.out.println(e.getMessage());
            System.out.println(LINE + "\n");
        }
    }

    /**
     * Handles the deadline command.
     *
     * @param input The user input.
     */
    private void handleDeadline(String input) {
        try {
            String remaining = input.substring(9).trim();
            if (remaining.isEmpty()) {
                throw new NeerajException("OOPS!!! The description of a deadline cannot be empty.");
            }
            String[] parts = remaining.split(" /by ", 2);
            if (parts.length < 2) {
                throw new NeerajException("OOPS!!! Please specify a deadline with /by");
            }
            String description = parts[0].trim();
            String by = parts[1].trim();
            if (description.isEmpty()) {
                throw new NeerajException("OOPS!!! The description of a deadline cannot be empty.");
            }
            Task newTask = new Deadline(description, by);
            addTask(newTask);
        } catch (NeerajException e) {
            System.out.println(e.getMessage());
            System.out.println(LINE + "\n");
        }
    }

    /**
     * Handles the event command.
     *
     * @param input The user input.
     */
    private void handleEvent(String input) {
        try {
            String remaining = input.substring(6).trim();
            if (remaining.isEmpty()) {
                throw new NeerajException("OOPS!!! The description of an event cannot be empty.");
            }
            String[] parts1 = remaining.split(" /from ", 2);
            if (parts1.length < 2) {
                throw new NeerajException("OOPS!!! Please specify event time with /from and /to");
            }
            String description = parts1[0].trim();
            String[] parts2 = parts1[1].split(" /to ", 2);
            if (parts2.length < 2) {
                throw new NeerajException("OOPS!!! Please specify event end time with /to");
            }
            String from = parts2[0].trim();
            String to = parts2[1].trim();
            if (description.isEmpty()) {
                throw new NeerajException("OOPS!!! The description of an event cannot be empty.");
            }
            Task newTask = new Event(description, from, to);
            addTask(newTask);
        } catch (NeerajException e) {
            System.out.println(e.getMessage());
            System.out.println(LINE + "\n");
        }
    }

    /**
     * Handles unknown commands.
     */
    private void handleUnknownCommand() {
        System.out.println("I don't understand that command!");
        System.out.println(LINE + "\n");
    }

    /**
     * Adds a task and displays confirmation message.
     *
     * @param task The task to add.
     */
    private void addTask(Task task) {
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(LINE + "\n");
    }

    /**
     * Main entry point for the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new Neeraj().run();
    }
}