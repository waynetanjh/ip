import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Jack {
    private static final String LINE = "-----------------------------------------";
    private static final Storage STORAGE = new Storage("data/duke.txt");
    private final List<Task> tasks = new ArrayList<>();

    public Jack() {
        try {
           STORAGE.loadFile(tasks);
        } catch (IOException e) {
            System.out.println("Error reading file");
        }
    }
    /**
     * Prints a message with lines above and below for seperation
     * @param text the message that is displayed
     */
    private static void echo(String text) {
        System.out.println("\t" + LINE);
        System.out.println(text);
        System.out.println("\t" + LINE);
    }

    /**
     * Prints confirmation message that the task has been marked as completed
     * @param text the task represented as a string
     */
    private static void markCompleted(String text) {
        System.out.println("\t" + LINE);
        System.out.println("\t" + "Nice! I've marked this task as done:");
        System.out.println(" " + text);
        System.out.println("\t" + LINE);
    }

    /**
     * Prints a confirmation message that the task has been marked as uncompleted
     * @param text the string representation of the task
     */
    private static void unmarkCompleted(String text) {
        System.out.println("\t" + LINE);
        System.out.println("\t" + "OK, I've marked this task as not done yet:");
        System.out.println(" " + text);
        System.out.println("\t" + LINE);
    }

    /**
     * Prints a welcome message when program starts
     */
    private static void printWelcomeMessage() {
        String name = "Jack";
        System.out.println(LINE);
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    private static void handleDelete(List<Task> tasks, int index) {
        if (tasks.isEmpty()) {
            throw new IllegalArgumentException("No tasks to delete");
        }
        Task removed = tasks.remove(index);

        echo("\tNoted. I've removed this task:\n\t  "
                + removed
                + "\n\tNow you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Handles the addition of a {@code Todo} task to the task list.
     * <p>
     * If the description after todo is missing or blank, the task is not added and warning will be printed.
     *
     * @param tasks     the new Todo will be added to this the list of tasks
     * @param argument  the description part of the todo command is everything after "todo"
     */
    private static void handleToDo(List<Task> tasks, String argument) {
        if (argument == null || argument.isBlank()) {
            echo("\tOOPS!!! The description of a todo cannot be empty");
            return;
        }
        String desc = argument.trim();
        tasks.add(new Todo(desc));

        echo("\tGot it. I've added this task:\n\t  "
                + tasks.get(tasks.size() - 1)
                + "\n\tNow you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Handles the addition of a deadline task to the task list.
     * @param tasks new deadline tasks are added to this list of tasks
     * @param argument description of the deadline task
     */
    private static void handleDeadlineTask(List<Task> tasks, String argument) {
        if (argument == null || argument.isBlank()) {
            echo("\tOOPS!!! The description of a todo cannot be empty");
            return;
        }
        String[] parts = argument.split("/by", 2);
        String desc = parts[0].trim();
        String by = parts[1].trim();
        tasks.add(new Deadline(desc, by));
        int numberOfTasks = tasks.size();

        echo("\tGot it. I've added this task:\n\t  "
                + tasks.get(numberOfTasks - 1)
                + "\n\tNow you have " + numberOfTasks + " tasks in the list.");

    }

    /**
     * Handles the addition of the event task to the task list.
     * @param tasks event task added to this list of tasks
     * @param argument description of event consisting of from and to date
     */
    private static void handleEventTask(List<Task> tasks, String argument) {
        String[] parts = argument.split("/from|/to", 3);
        String desc = parts[0].trim();
        String from = parts[1].trim();
        String to = parts[2].trim();
        tasks.add(new Event(desc, from, to));
        int numberOfTasks = tasks.size();

        echo("\tGot it. I've added this task:\n\t  "
                + tasks.get(numberOfTasks - 1)
                + "\n\tNow you have " + numberOfTasks + " tasks in the list.");
    }

    private static void handleList(List<Task> tasks) {
        if (tasks.isEmpty()) {
            echo("\tHere are the tasks in your list:\n\t  (no tasks yet)");
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < tasks.size(); i++) {
                sb.append("\t").append(i + 1).append(". ").append(tasks.get(i).toString());
                if (i < tasks.size() - 1) {
                    sb.append("\n");
                }
            }
            echo(sb.toString());
        }
    }

    public static void main(String[] args) throws IOException {
        Jack app = new Jack();
        Scanner scanner = new Scanner(System.in);

        printWelcomeMessage();

        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.isEmpty()) {
                echo("\tEnter a valid task\n\t");
                continue;
            }

            if (userInput.equals("blah")) {
                echo("\tEnter a valid task\n\t");
                continue;
            }

            String[] part = userInput.split("\\s+", 2);
            String cmd = part[0];                         // e.g., "todo"
            String argument = (part.length > 1) ? part[1].trim() : ""; // e.g., "buy milk"

            List<Task> tasks = app.tasks;

            switch (cmd) {
                case "list": {
                    handleList(tasks);
                    break;
                }
                case "bye": {
                    echo("\t" + "Bye. Hope to see you again soon!");
                    scanner.close();
                    return;
                }
                case "mark": {
                    int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    tasks.get(index).completed();
                    STORAGE.saveFile(tasks);
                    markCompleted("\t" + tasks.get(index).toString());
                    break;
                }
                case "unmark": {
                    int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    tasks.get(index).unmark();
                    STORAGE.saveFile(tasks);
                    unmarkCompleted("\t" + tasks.get(index).toString());
                    break;
                }
                case "todo": {
                    // Error handling for empty description of todo
                    handleToDo(tasks, argument);
                    STORAGE.saveFile(tasks);
                    break;
                }
                case "deadline": {
                    handleDeadlineTask(tasks, argument);
                    STORAGE.saveFile(tasks);
                    break;
                }
                case ("event"): {
                    handleEventTask(tasks, argument);
                    STORAGE.saveFile(tasks);
                    break;
                }
                case "delete": {
                    int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    handleDelete(tasks, index);
                    STORAGE.saveFile(tasks);
                    break;
                }
                default: {
                    tasks.add(new Todo(userInput));
                    STORAGE.saveFile(tasks);
                    echo("\t" + userInput);
                    break;
                }
            }
        }
    }
}