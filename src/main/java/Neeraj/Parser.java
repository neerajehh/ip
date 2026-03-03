package Neeraj;

/**
 * Parses user input into commands.
 */
public class Parser {

    /**
     * Parses a user command and returns the command type.
     *
     * @param input The user's input string
     * @return The command type (e.g., "todo", "list", "bye")
     */
    public static String getCommand(String input) {
        String[] parts = input.split(" ", 2);
        return parts[0];
    }

    /**
     * Gets the arguments portion of a command.
     *
     * @param input The user's input string
     * @return The arguments after the command, or empty string if none
     */
    public static String getArguments(String input) {
        String[] parts = input.split(" ", 2);
        return parts.length > 1 ? parts[1].trim() : "";
    }

    /**
     * Parses a task number from input.
     *
     * @param arguments The arguments string containing the task number
     * @return The task number (0-indexed)
     * @throws NeerajException If the task number is invalid
     */
    public static int parseTaskNumber(String arguments) throws NeerajException {
        try {
            int taskNumber = Integer.parseInt(arguments.trim()) - 1;
            if (taskNumber < 0) {
                throw new NeerajException("OOPS!!! Invalid task number.");
            }
            return taskNumber;
        } catch (NumberFormatException e) {
            throw new NeerajException("OOPS!!! Please provide a valid task number.");
        }
    }
}