package jack;

import java.util.List;
import java.util.Scanner;

public class Parser {
    private static void handleDelete(List<Task> tasks, int index) {
        Task removed = TaskList.handleDelete(tasks, index);
        Ui.showDeleted(removed, tasks.size());
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
            Ui.echo("\tOOPS!!! The description of a todo cannot be empty");
            return;
        }
        String desc = argument.trim();
        tasks.add(new Todo(desc));

        Ui.handleToDo(tasks);
    }

    /**
     * Handles the addition of a deadline task to the task list.
     * @param tasks new deadline tasks are added to this list of tasks
     * @param argument description of the deadline task
     */
    private static void handleDeadlineTask(List<Task> tasks, String argument) {
        if (argument == null || argument.isBlank()) {
            Ui.echo("\tOOPS!!! The description of a todo cannot be empty");
            return;
        }
        String[] parts = argument.split("/by", 2);
        String desc = parts[0].trim();
        String by = parts[1].trim();
        tasks.add(new Deadline(desc, by));
        int numberOfTasks = tasks.size();

        Ui.handleDeadlineTask(tasks, numberOfTasks);
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

        Ui.handleEventTask(tasks, numberOfTasks);
    }

    private static void handleList(List<Task> tasks) {
        Ui.echo(TaskList.formatList(tasks));
    }

    public static boolean parseAndExecute(String input, List<Task> tasks, Storage STORAGE,
                                          Scanner scanner, String argument, String userInput) {
        switch (input) {
            case "list": {
                handleList(tasks);
                break;
            }
            case "bye": {
                Ui.showBye();
                scanner.close();
                return true;
            }
            case "mark": {
                int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
                tasks.get(index).completed();
                STORAGE.saveFile(tasks);
                Ui.markCompleted("\t" + tasks.get(index).toString());
                break;
            }
            case "unmark": {
                int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
                tasks.get(index).unmark();
                STORAGE.saveFile(tasks);
                Ui.unmarkCompleted("\t" + tasks.get(index).toString());
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
                Ui.echo("\t" + userInput);
                break;
            }
        }
        return false;
    }
}
