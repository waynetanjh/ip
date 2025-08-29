package jack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
            String cmd = part[0]; // e.g., "todo"
            String argument = (part.length > 1) ? part[1].trim() : "";

            if (Parser.parseAndExecute(cmd, app.tasks, STORAGE,
                                       scanner, argument, userInput)) {
                return;
            }
        }
    }
}
