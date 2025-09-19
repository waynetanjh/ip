package jack;

/**
 * Handles UI messages for the GUI application
 */
public class Ui {

    /**
     * Returns confirmation message when task has been marked as completed
     *
     * @return confirmation message as a string
     */
    public static String getMarkCompletedMessage() {
        return "Nice! I've marked this task as done.";
    }

    /**
     * Returns confirmation message when task has been marked as uncompleted
     *
     * @return confirmation message
     */
    public static String getUnmarkCompletedMessage() {
        return "OK, I've marked this task as not done yet.";
    }

    /**
     * Returns confirmation message when task has been deleted
     *
     * @return confirmation message
     */
    public static String getDeletedMessage() {
        return "Noted. I've removed this task.";
    }

    /**
     * Returns confirmation message after event task has been added
     *
     * @return confirmation message
     */
    public static String getEventTaskMessage() {
        return "Got it. I've added this task.";
    }

    /**
     * Returns confirmation message after deadline task has been added
     *
     * @return confirmation message
     */
    public static String getDeadlineTaskMessage() {
        return "Got it. I've added this task.";
    }

    /**
     * Returns confirmation message after todo task has been added
     *
     * @return confirmation message
     */
    public static String getToDoMessage() {
        return "Got it. I've added this task.";
    }

    /**
     * Displays help message with list of available commands
     *
     * @return Help message string
     */
    public static String showHelp() {
        return String.join("\n",
                "Commands available:",
                "  list - show all tasks",
                "  todo <description> - add a todo",
                "  deadline <description> /by <date> - add a deadline",
                "  event <description> /from <start-date> /to <end-date> - add an event",
                "  mark <task number> - mark task as done",
                "  unmark <task number> - mark task as not done",
                "  delete <task number> - delete a task",
                "  find <keyword> - find tasks containing keyword",
                "  help - show this help message",
                "  bye - exit the program"
        );
    }
}
