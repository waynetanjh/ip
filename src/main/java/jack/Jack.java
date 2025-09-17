package jack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main class for the Jack task management application.
 * Handles user interaction and command processing.
 */
public class Jack {
    /** The storage file for saving and loading tasks */
    private static final Storage STORAGE = new Storage("data/duke.txt");
    /** The list of tasks managed by the application */
    private final TaskList tasks = new TaskList();

    /**
     * Initializes the application by loading existing tasks from storage.
     * If there's an error reading the file, it will be reported to the user.
     */
    public Jack() {
        try {
            List<Task> loaded = new ArrayList<>();
            STORAGE.loadFile(loaded);
            for (Task task : loaded) {
                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("Error reading file");
        }
    }

    /**
     * Displays help information about available commands.
     */
    public void showHelp() {
        Ui.showMessage(
            "Commands available:",
            "  list - show all tasks",
            "  todo <description> - add a todo",
            "  deadline <description> /by <date> - add a deadline",
            "  event <description> /at <date> - add an event",
            "  done <task number> - mark task as done",
            "  delete <task number> - delete a task",
            "  help - show this help message"
        );
    }

    /**
     * Main entry point of the application.
     * Handles the command loop for user interaction and task management.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        Jack app = new Jack();
        Scanner scanner = new Scanner(System.in);
        Ui.printWelcomeMessage();

        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.isEmpty()) {
                Ui.echo("\tEnter a valid task\n\t");
                continue;
            }

            if (userInput.equals("blah")) {
                Ui.echo("\tEnter a valid task\n\t");
                continue;
            }

            String[] part = userInput.split("\\s+", 2);
            System.out.println("before");
            System.out.println("after");
            String cmd = part[0]; // e.g., "todo"
            String argument = (part.length > 1) ? part[1].trim() : "";

            if (Parser.parseAndExecute(cmd, app.tasks, STORAGE,
                                       scanner, argument, userInput)) {
                return;
            }
        }
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String userInput) {
        if (userInput.isEmpty()) {
            Ui.echo("\tEnter a valid task\n\t");
        }

        if (userInput.equals("blah")) {
            Ui.echo("\tEnter a valid task\n\t");
        }

        String[] part = userInput.split("\\s+", 2);
        String cmd = part[0]; // e.g., "todo"
        String argument = (part.length > 1) ? part[1].trim() : "";

        if (cmd.equals("bye")) {
            return "Bye. Hope to see you again soon!";
        }

        if (cmd.equals("help")) {
            return String.join("\n",
                "Commands available:",
                "  list - show all tasks",
                "  todo <description> - add a todo",
                "  deadline <description> /by <date> - add a deadline",
                "  event <description> /from <start-date> /to <end-date> - add an event",
                "  mark <task number> - mark task as done",
                "  unmark <task number> - mark task as not done",
                "  done <task number> - mark task as done",
                "  delete <task number> - delete a task",
                "  find <keyword> - find tasks containing keyword",
                "  help - show this help message",
                "  bye - exit the program"
            );
        }

        // Use the same parser logic as main
        Parser.parseAndExecute(cmd, tasks, STORAGE, null, argument, userInput);

        StringBuilder response = new StringBuilder();
        // Get the result from parser and format it for display
        for (int i = 0; i < tasks.size(); i++) {
            response.append(String.format("%d. %s\n", i + 1, tasks.get(i)));
        }

        return response.toString();
    }
}
