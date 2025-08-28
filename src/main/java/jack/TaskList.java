package jack;

import java.util.ArrayList;
import java.util.List;

public class TaskList extends ArrayList<Task> {
    public TaskList()  {
        super();
    }

    /** Pretty-prints the list the same way your current jack.jack.Ui expects. */
    public static String formatList(List<Task> tasks) {
        if (tasks.isEmpty()) {
            Ui.echo("\tHere are the tasks in your list:\n\t  (no tasks yet)");
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            sb.append("\t").append(i + 1).append(". ").append(tasks.get(i).toString());
            if (i < tasks.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public static Task handleDelete(List<Task> tasks, int index) {
        if (tasks.isEmpty()) {
            throw new IllegalArgumentException("No tasks to delete");
        }
        if (index < 0 || index >= tasks.size()) {
            throw new IllegalArgumentException("Index out of range: " + (index + 1));
        }

        return tasks.remove(index);
    }
}
