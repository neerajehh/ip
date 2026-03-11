package Neeraj;

/**
 * Main chatbot class that handles user interactions and task management.
 */
public class Neeraj {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Sets up the chatbot and loads any saved tasks from file.
     */
    public Neeraj() {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList(Storage.load());
    }

    /**
     * Starts the main program loop.
     */
    public void run() {
        ui.showWelcome();

        boolean isExit = false;
        while (!isExit) {
            String input = ui.readCommand();
            ui.showLine();
            isExit = executeCommand(input);
        }

        ui.close();
    }

    /**
     * Processes user commands and calls the appropriate handler.
     *
     * @param input What the user typed
     * @return true when user wants to exit
     */
    private boolean executeCommand(String input) {
        String command = Parser.getCommand(input);
        String arguments = Parser.getArguments(input);

        try {
            switch (command) {
            case "bye":
                handleBye();
                return true;
            case "list":
                handleList();
                break;
            case "mark":
                handleMark(arguments);
                break;
            case "unmark":
                handleUnmark(arguments);
                break;
            case "todo":
                handleTodo(arguments);
                break;
            case "deadline":
                handleDeadline(arguments);
                break;
            case "event":
                handleEvent(arguments);
                break;
            case "delete":
                handleDelete(arguments);
                break;
            case "find":
                handleFind(arguments);
                break;
            default:
                handleUnknownCommand();
            }
        } catch (NeerajException e) {
            ui.showError(e.getMessage());
        }
        return false;
    }

    private void handleBye() {
        ui.showGoodbye();
    }

    private void handleList() {
        ui.showTaskList(tasks);
    }

    /**
     * Marks a task as complete.
     *
     * @param arguments Task number from user
     */
    private void handleMark(String arguments) throws NeerajException {
        int taskNumber = Parser.parseTaskNumber(arguments);
        if (taskNumber >= tasks.size()) {
            throw new NeerajException("OOPS!!! Invalid task number.");
        }
        Task task = tasks.get(taskNumber);
        task.markAsDone();
        Storage.save(tasks.getTasks());
        ui.showTaskMarked(task);
    }

    /**
     * Unmarks a task (sets it back to incomplete).
     *
     * @param arguments Task number from user
     */
    private void handleUnmark(String arguments) throws NeerajException {
        int taskNumber = Parser.parseTaskNumber(arguments);
        if (taskNumber >= tasks.size()) {
            throw new NeerajException("OOPS!!! Invalid task number.");
        }
        Task task = tasks.get(taskNumber);
        task.markAsNotDone();
        Storage.save(tasks.getTasks());
        ui.showTaskUnmarked(task);
    }

    /**
     * Removes a task from the list permanently.
     *
     * @param arguments Task number to delete
     */
    private void handleDelete(String arguments) throws NeerajException {
        int taskNumber = Parser.parseTaskNumber(arguments);
        if (taskNumber >= tasks.size()) {
            throw new NeerajException("OOPS!!! Invalid task number.");
        }
        Task removed = tasks.remove(taskNumber);
        Storage.save(tasks.getTasks());
        ui.showTaskDeleted(removed, tasks.size());
    }

    /**
     * Adds a new todo task.
     *
     * @param arguments The todo description
     */
    private void handleTodo(String arguments) throws NeerajException {
        if (arguments.isEmpty()) {
            throw new NeerajException("OOPS!!! The description of a todo cannot be empty.");
        }
        Task newTask = new Todo(arguments);
        tasks.add(newTask);
        Storage.save(tasks.getTasks());
        ui.showTaskAdded(newTask, tasks.size());
    }

    /**
     * Adds a deadline task with a due date.
     *
     * @param arguments Description and deadline (separated by /by)
     */
    private void handleDeadline(String arguments) throws NeerajException {
        if (arguments.isEmpty()) {
            throw new NeerajException("OOPS!!! The description of a deadline cannot be empty.");
        }
        String[] parts = arguments.split(" /by ", 2);
        if (parts.length < 2) {
            throw new NeerajException("OOPS!!! Please specify a deadline with /by");
        }
        String description = parts[0].trim();
        String by = parts[1].trim();
        if (description.isEmpty()) {
            throw new NeerajException("OOPS!!! The description of a deadline cannot be empty.");
        }
        Task newTask = new Deadline(description, by);
        tasks.add(newTask);
        Storage.save(tasks.getTasks());
        ui.showTaskAdded(newTask, tasks.size());
    }

    /**
     * Adds an event with start and end times.
     *
     * @param arguments Description and time period (using /from and /to)
     */
    private void handleEvent(String arguments) throws NeerajException {
        if (arguments.isEmpty()) {
            throw new NeerajException("OOPS!!! The description of an event cannot be empty.");
        }
        String[] parts1 = arguments.split(" /from ", 2);
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
        tasks.add(newTask);
        Storage.save(tasks.getTasks());
        ui.showTaskAdded(newTask, tasks.size());
    }

    /**
     * Searches for tasks containing the keyword.
     *
     * @param keyword The search keyword
     */
    private void handleFind(String keyword) throws NeerajException {
        if (keyword.isEmpty()) {
            throw new NeerajException("OOPS!!! The search keyword cannot be empty.");
        }
        TaskList foundTasks = tasks.find(keyword);
        ui.showFoundTasks(foundTasks);
    }

    private void handleUnknownCommand() {
        ui.showError("I don't understand that command!");
    }

    public static void main(String[] args) {
        new Neeraj().run();
    }
}