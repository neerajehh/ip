package Neeraj;

public abstract class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    /** Returns 1 if task is done, 0 otherwise, for use in save format */
    public String getSaveStatus() {
        return (isDone ? "1" : "0");
    }

    /** Returns the task in a format suitable for saving to file */
    public abstract String toSaveFormat();

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}