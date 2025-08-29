package jack;

public class Task {
    private final String description;
    private boolean isDone;

    /**
     * Creates a new task with the given description.
     * New tasks are initially marked as not done.
     *
     * @param description The description of the task
     */
    public Task(String description) {
        isDone = false;
        this.description = description;
    }

    /**
     * Marks this task as completed.
     */
    public void completed() {
        this.isDone = true;
    }

    /**
     * Marks this task as not completed.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns the status of the task as a string.
     *
     * @return "X" if the task is done, " " if not done
     */
    public String getStatusString() {
        return isDone ? "X" : " ";
    }


    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[" + getStatusString() + "] " + description;
    }
}
