package Neeraj;

import java.util.Scanner;

/**
 * Handles interactions with the user.
 */
public class Ui {
    private static final String LINE = "===========================";
    private Scanner scanner;

    /**
     * Creates a new Ui instance.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Shows the welcome message.
     */
    public void showWelcome() {
        String logo = LINE + "\n"
                + "        NEERAJ BOT        \n"
                + LINE + "\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Neeraj");
        System.out.println("What can I do for you?");
        System.out.println(LINE + "\n");
    }

    /**
     * Shows the goodbye message.
     */
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    /**
     * Shows the line separator.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Reads a command from the user.
     *
     * @return The user's input command
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Shows an error message.
     *
     * @param message The error message to display
     */
    public void showError(String message) {
        System.out.println(message);
        System.out.println(LINE + "\n");
    }

    /**
     * Shows a message indicating a task was added.
     *
     * @param task The task that was added
     * @param size The new size of the task list
     */
    public void showTaskAdded(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println(LINE + "\n");
    }

    /**
     * Shows a message indicating a task was deleted.
     *
     * @param task The task that was deleted
     * @param size The new size of the task list
     */
    public void showTaskDeleted(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println(LINE + "\n");
    }

    /**
     * Shows a message indicating a task was marked as done.
     *
     * @param task The task that was marked
     */
    public void showTaskMarked(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
        System.out.println(LINE + "\n");
    }

    /**
     * Shows a message indicating a task was unmarked.
     *
     * @param task The task that was unmarked
     */
    public void showTaskUnmarked(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
        System.out.println(LINE + "\n");
    }

    /**
     * Shows the list of tasks.
     *
     * @param tasks The task list to display
     */
    public void showTaskList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        System.out.println(LINE + "\n");
    }

    /**
     * Shows tasks that match the search.
     *
     * @param tasks The matching tasks
     */
    public void showFoundTasks(TaskList tasks) {
        if (tasks.size() == 0) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        }
        System.out.println(LINE + "\n");
    }

    /**
     * Closes the scanner.
     */
    public void close() {
        scanner.close();
    }
}