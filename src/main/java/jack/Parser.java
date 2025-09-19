package jack;

import java.util.List;
import java.util.Scanner;

/**
 * Handles parsing and executing user commands.
 */
public class Parser {
    /**
     * Handles the deletion of a task from the task list.
     *
     * @param tasks The list to delete from
     * @param index The index of the task to delete
     * @param storage storage for saving tasks
     * @return response message
     */
    private static String handleDelete(List<Task> tasks, int index, Storage storage) {
        Task removed = TaskList.handleDelete(tasks, index);
        storage.saveFile(tasks);
        String confirmationMessage = Ui.getDeletedMessage();
        return confirmationMessage + "\n" + TaskList.formatList(tasks);
    }

    /**
     * Handles the addition of a todo task to the task list.
     *
     * @param tasks list that todo tasks are added to
     * @param argument description of the todo task
     * @param storage storage for saving tasks
     * @return response message
     */
    private static String handleToDo(List<Task> tasks, String argument, Storage storage) {
        boolean isNull = argument == null;
        boolean isBlank = argument != null && argument.isBlank();

        if (isNull || isBlank) {
            return "OOPS!!! The description of a todo cannot be empty";
        }
        String desc = argument.trim();
        tasks.add(new Todo(desc));
        storage.saveFile(tasks);

        String confirmationMessage = Ui.getToDoMessage();
        return confirmationMessage + "\n" + TaskList.formatList(tasks);
    }

    /**
     * Handles the addition of a deadline task to the task list.
     *
     * @param tasks new deadline tasks are added to this list of tasks
     * @param argument description of the deadline task
     * @param storage storage for saving tasks
     * @return response message
     */
    private static String handleDeadlineTask(List<Task> tasks, String argument, Storage storage) {
        if (argument == null || argument.isBlank()) {
            return "OOPS!!! The description of a deadline cannot be empty";
        }
        String[] parts = argument.split("/by", 2);
        if (parts.length != 2) {
            return "OOPS!!! Please use the format: deadline <description> /by <date>\nExample: deadline return book /by 19/02/2003 1800";
        }
        String desc = parts[0].trim();
        String by = parts[1].trim();
        
        try {
            tasks.add(new Deadline(desc, by));
            storage.saveFile(tasks);
            String confirmationMessage = Ui.getDeadlineTaskMessage();
            return confirmationMessage + "\n" + TaskList.formatList(tasks);
        } catch (Exception e) {
            return "OOPS!!! Invalid date format. Please use: d/M/yyyy HHmm or yyyy-MM-dd\n";
        }
    }

    /**
     * Handles the addition of an event task to the task list.
     * Parses the event description and time from the user input.
     *
     * @param tasks The list to add the new event to
     * @param argument The description and timing of the event
     * @param storage storage for saving tasks
     * @return response message
     */
    private static String handleEventTask(List<Task> tasks, String argument, Storage storage) {
        assert argument != null && !argument.isBlank() : "Event argument cannot be null or empty";
        if (argument == null || argument.isBlank()) {
            return "OOPS!!! The description of an event cannot be empty";
        }
        String[] parts = argument.split("/from|/to", 3);
        if (parts.length != 3) {
            return "OOPS!!! Please use the format: event <description> /from <start-time> /to <end-time>\nExample: event project meeting /from 2/12/2019 1400 /to 2/12/2019 1600";
        }
        String description = parts[0].trim();
        String fromTime = parts[1].trim();
        String toTime = parts[2].trim();
        try {
            tasks.add(new Event(description, fromTime, toTime));
            storage.saveFile(tasks);
            String confirmationMessage = Ui.getEventTaskMessage();
            return confirmationMessage + "\n" + TaskList.formatList(tasks);
        } catch (Exception e) {
            return "OOPS!!! Invalid date format. Please use: d/M/yyyy HHmm or yyyy-MM-dd\nExample: 2/12/2019 1400 or 2019-12-02";
        }
    }

    /** 
     * Handles marking of tasks as done
     * 
     * @param tasks The list to add the new event to
     * @param userInput The user input
     * @param storage storage for saving tasks
     * @return response message
     */
    private static String handleMark(List<Task> tasks, String userInput, Storage storage) {
        try {
            String[] parts = userInput.split(" ");
            if (parts.length < 2) {
                return "OOPS!!! Please specify a task number.\nExample: mark 1";
            }
            int index = Integer.parseInt(parts[1]) - 1;
            if (index < 0 || index >= tasks.size()) {
                return "OOPS!!! Task number " + (index + 1) + " does not exist. You have " + tasks.size() + " tasks.";
            }
            tasks.get(index).markAsDone();
            storage.saveFile(tasks);
            String confirmationMessage = Ui.getMarkCompletedMessage();
            return confirmationMessage + "\n" + TaskList.formatList(tasks);
        } catch (NumberFormatException e) {
            return "OOPS!!! Please provide a valid task number.\nExample: mark 1";
        }
    }

    /**
     * Handles unmarking of tasks
     * 
     * @param tasks The list to add the new event to
     * @param userInput The user input
     * @param storage storage for saving tasks
     * @return response message
     */
    private static String handleUnmark(List<Task> tasks, String userInput, Storage storage) {
        try {
            String[] parts = userInput.split(" ");
            if (parts.length < 2) {
                return "OOPS!!! Please specify a task number.\nExample: unmark 1";
            }
            int index = Integer.parseInt(parts[1]) - 1;
            if (index < 0 || index >= tasks.size()) {
                return "OOPS!!! Task number " + (index + 1) + " does not exist. You have " + tasks.size() + " tasks.";
            }
            tasks.get(index).unmark();
            storage.saveFile(tasks);
            String confirmationMessage = Ui.getUnmarkCompletedMessage();
            return confirmationMessage + "\n" + TaskList.formatList(tasks);
        } catch (NumberFormatException e) {
            return "OOPS!!! Please provide a valid task number.\nExample: unmark 1";
        }
    }

    /**
     * Handles the deletion of a task from the task list
     * 
     * @param tasks The list to delete from
     * @param userInput The user input
     * @param storage storage for saving tasks
     * @return response message
     */
    private static String handleDelete(List<Task> tasks, String userInput, Storage storage) {
        try {
            String[] parts = userInput.split(" ");
            if (parts.length < 2) {
                return "OOPS!!! Please specify a task number.\nExample: delete 1";
            }
            int index = Integer.parseInt(parts[1]) - 1;
            if (index < 0 || index >= tasks.size()) {
                return "OOPS!!! Task number " + (index + 1) + " does not exist. You have " + tasks.size() + " tasks.";
            }
            return handleDelete(tasks, index, storage);
        } catch (NumberFormatException e) {
            return "OOPS!!! Please provide a valid task number.\nExample: delete 1";
        }
    }

    /**
     * Handles the finding of a task from the task list
     * 
     * @param tasks The list to find from
     * @param userInput The user input
     * @param storage storage for saving tasks
     * @return response message
     */
    private static String handleFind(List<Task> tasks, String userInput, Storage storage) {
        try {
            String[] parts = userInput.split(" ", 2);
            if (parts.length < 2) {
                return "OOPS!!! Please specify a keyword to search for.\nExample: find book";
            }
            String keyword = parts[1].trim();
            return TaskList.findString(tasks, keyword);
        } catch (Exception e) {
            return "OOPS!!! Please specify a keyword to search for.\nExample: find book";
        }
    }

    /**
     * Parses the user input and executes the corresponding command.
     * Returns the response message.
     *
     * @param input The command input by the user
     * @param tasks The list of tasks to operate on
     * @param storage The storage for saving/loading tasks
     * @param argument The argument part of the user input (if any)
     * @param userInput The full user input string
     * @return response message for the command
     */
    public static String parseAndExecute(String input, List<Task> tasks, Storage storage,
                                         String argument, String userInput) {
        switch (input) {
        case "list": {
            return TaskList.formatList(tasks);
        }
        case "mark": {
            String handleMark = handleMark(tasks, userInput, storage);
            return handleMark;
        }
        case "unmark": {
            String handleUnmark = handleUnmark(tasks, userInput, storage);
            return handleUnmark;
        }
        case "todo": {
            // Error handling for empty description of todo
            return handleToDo(tasks, argument, storage);
        }
        case "deadline": {
            return handleDeadlineTask(tasks, argument, storage);
        }
        case ("event"): {
            return handleEventTask(tasks, argument, storage);
        }
        case "delete": {
            String handleDelete = handleDelete(tasks, userInput, storage);
            return handleDelete;
        }
        case "find": {
            String handleFind = handleFind(tasks, userInput, storage);
            return handleFind;
        }
        default: {
            tasks.add(new Todo(userInput));
            storage.saveFile(tasks);
            String confirmationMessage = "Got it. I've added this task.";
            return confirmationMessage + "\n" + TaskList.formatList(tasks);
        }
        }
    }
}

