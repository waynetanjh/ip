package jack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a list of tasks that can be managed by the application.
 * Extends ArrayList to provide task-specific operations.
 */
public class TaskList extends ArrayList<Task> {
    /**
     * Creates a new empty task list.
     */
    public TaskList() {
        super();
    }

    /**
     * Formats the list of tasks for display.
     *
     * @param tasks The list of tasks to format
     * @return A formatted string representation of the task list
     */
    public static String formatList(List<Task> tasks) {
        assert tasks != null : "Tasks cannot be null";

        if (tasks.isEmpty()) {
            return "Argh: no tasks yet";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(".").append(tasks.get(i).toString());
            if (i < tasks.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Handles deletion of a task from the list.
     *
     * @param tasks list of tasks
     * @param index index of the task to delete
     * @return the removed task
     */
    public static Task handleDelete(List<Task> tasks, int index) {
        // Assert that tasks list is not null before any operation
        assert tasks != null : "Tasks list cannot be null";

        if (tasks.isEmpty()) {
            throw new IllegalArgumentException("No tasks to delete");
        }
        if (index < 0 || index >= tasks.size()) {
            throw new IllegalArgumentException("Index out of range: " + (index + 1));
        }
        Task removedTask = tasks.remove(index);
        // Assert that the task was actually removed
        assert !tasks.contains(removedTask) : "Task was not properly removed";
        return removedTask;
    }


    /**
     * Finds tasks containing the specified keyword (case-insensitive).
     *
     * @param tasks   The list of tasks to search
     * @param keyword The keyword to search for
     * @return A string listing the matching tasks or a message if none found
     */
    public static String findString(List<Task> tasks, String keyword) {
        String q = keyword == null ? "" : keyword.trim().toLowerCase();
        if (q.isEmpty()) {
            return "Usage: find <keyword>";
        }

        List<Task> matched = tasks.stream()
                .filter(t -> t.getDescription().toLowerCase().contains(q))
                .toList();

        if (matched.isEmpty()) {
            return "No matching tasks found.";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matched.size(); i++) {
            sb.append(i + 1).append(".").append(matched.get(i).toString());
            if (i < matched.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

}
