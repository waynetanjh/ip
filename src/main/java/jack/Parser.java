package jack;

import java.util.List;
import java.util.Scanner;

public class Parser {
    private static void handleDelete(List<Task> tasks, int index) {
        Task removed = TaskList.handleDelete(tasks, index);
        Ui.showDeleted(removed, tasks.size());
    }

    /**
     * Handles the addition of a todo task to the task list.
     *
     * @param tasks
     * @param argument
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
     *
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
     * Handles the addition of an event task to the task list.
     * Parses the event description and time from the user input.
     *
     * @param tasks The list to add the new event to
     * @param argument The description and timing of the event
     */
    private static void handleEventTask(List<Task> tasks, String argument) {
        String[] parts = argument.split("/from|/to", 3);
        String description = parts[0].trim();
        String fromTime = parts[1].trim();
        String toTime = parts[2].trim();
        tasks.add(new Event(description, fromTime, toTime));
        int numberOfTasks = tasks.size();

        Ui.handleEventTask(tasks, numberOfTasks);
    }

    private static void handleList(List<Task> tasks) {
        Ui.echo(TaskList.formatList(tasks));
    }

    public static boolean parseAndExecute(String input, List<Task> tasks, Storage storage,
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
            storage.saveFile(tasks);
            Ui.markCompleted("\t" + tasks.get(index).toString());
            break;
        }
        case "unmark": {
            int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
            tasks.get(index).unmark();
            storage.saveFile(tasks);
            Ui.unmarkCompleted("\t" + tasks.get(index).toString());
            break;
        }
        case "todo": {
            // Error handling for empty description of todo
            handleToDo(tasks, argument);
            storage.saveFile(tasks);
            break;
        }
        case "deadline": {
            handleDeadlineTask(tasks, argument);
            storage.saveFile(tasks);
            break;
        }
        case ("event"): {
            handleEventTask(tasks, argument);
            storage.saveFile(tasks);
            break;
        }
        case "delete": {
            int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
            handleDelete(tasks, index);
            storage.saveFile(tasks);
            break;
        }
        case "find": {
            String keyword = userInput.split(" ", 2)[1].trim();

            TaskList.find(tasks, keyword);
            break;
        }
        default: {
            tasks.add(new Todo(userInput));
            storage.saveFile(tasks);
            Ui.echo("\t" + userInput);
            break;
        }
        }
        return false;
    }
}
