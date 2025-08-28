package jack;

import java.util.List;

public class Ui {
    private static final String LINE = "-----------------------------------------";

    /**
     * Prints a welcome message when program starts
     */
    public static void printWelcomeMessage() {
        String name = "Jack";
        System.out.println(LINE);
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    /**
     * Prints confirmation message when task has been marked as completed
     * @param text string representation of the task
     */
    static void markCompleted(String text) {
        System.out.println("\t" + LINE);
        System.out.println("\t" + "Nice! I've marked this task as done:");
        System.out.println(" " + text);
        System.out.println("\t" + LINE);
    }

    /**
     * Prints a confirmation message when task has been marked as uncompleted
     * @param text string representation of the task
     */
    public static void unmarkCompleted(String text) {
        System.out.println("\t" + LINE);
        System.out.println("\t" + "OK, I've marked this task as not done yet:");
        System.out.println(" " + text);
        System.out.println("\t" + LINE);
    }

    /**
     * Prints a message with the text wrapped in lines
     * @param text string representation of the message
     */
    public static void echo(String text) {
        System.out.println("\t" + LINE);
        System.out.println(text);
        System.out.println("\t" + LINE);
    }

    /**
     * Prints goodbye message
     */
    public static void showBye() {
        echo("\tBye. Hope to see you again soon!");
    }

    public static void showDeleted(Task removed, int remaining) {
        echo("\tNoted. I've removed this task:\n\t  "
                + removed + "\n\tNow you have " + remaining + " tasks in the list.");
    }

    public static void handleEventTask(List<Task> tasks, int numberOfTasks) {
        echo("\tGot it. I've added this task:\n\t  "
                + tasks.get(numberOfTasks - 1)
                + "\n\tNow you have " + numberOfTasks + " tasks in the list.");
    }

    /**
     * Adds deadline task to the list and prints confirmation message
     * @param tasks list of tasks
     * @param numberOfTasks total number of tasks in the list
     */
    public static void handleDeadlineTask(List<Task> tasks, int numberOfTasks) {
        echo("\tGot it. I've added this task:\n\t  "
                    + tasks.get(numberOfTasks - 1)
                    + "\n\tNow you have " + numberOfTasks + " tasks in the list.");
    }

    /**
     * Adds todo task to the list and prints confirmation message
     * @param tasks list of tasks
     */
    public static void handleToDo(List<Task> tasks) {
        echo("\tGot it. I've added this task:\n\t  "
                + tasks.get(tasks.size() - 1)
                + "\n\tNow you have " + tasks.size() + " tasks in the list.");
    }
}
