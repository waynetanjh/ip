package jack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Main class for the Jack task management application.
 * Handles user interaction and command processing.
 */
public class Jack {
    /** The storage file for saving and loading tasks */
    private static final Storage STORAGE = new Storage("data/jack.txt");
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
     * Generates a response for the user's chat message.
     */
    public String getResponse(String userInput) {
        // Handle specific invalid inputs like empty input or "blah"
        String invalidMessage = handleInvalid(userInput);
        if (invalidMessage != null) {
            return invalidMessage;
        }

        String[] part = processInput(userInput);
        String cmd = part[0]; // e.g., "todo"
        String argument = part[1];

        // For bye, help or find commands
        String uniqueMessage = handleUnique(cmd, tasks, argument);

        if (uniqueMessage != null) {
            return uniqueMessage;
        }

        // Use the parser that returns messages
        return Parser.parseAndExecute(cmd, tasks, STORAGE, argument, userInput);
    }

    /**
     * Splits the user input into command and argument parts.
     *
     * @param userInput the user's input string
     * @return an array where the first element is the command and the second is the argument
     */
    private String[] processInput(String userInput) {
        // Split input into command and argument
        String[] part = userInput.split("\\s+", 2);

        // Create an array to hold command and argument
        String[] arrayOutput = new String[2];

        arrayOutput[0] = part[0];
        arrayOutput[1] = (part.length > 1) ? part[1].trim() : "";

        return arrayOutput;
    }

    /**
     * Handles specific invalid inputs and returns appropriate messages.
     *
     * @param userInput the user's input string
     * @return response string for specific invalid inputs
     */
    public static String handleInvalid(String userInput) {
        if (userInput.isEmpty()) {
            return "Argh, stop wasting my time";
        }

        if (userInput.equals("blah")) {
            return "blah, blah, blah";
        }
        return null;
    }

    /**
     * Handles simple commands like "bye" and "help".
     *
     * @param cmd the command string
     * @return response string or null if not a simple command
     */
    private static String handleUnique(String cmd, TaskList tasks, String argument) {
        if ("bye".equals(cmd)) {
            return "Bye. Hope to see you again soon!";
        }
        if ("help".equals(cmd)) {
            return Ui.showHelp(); // or Ui.helpMessage()
        }
        return null;
    }

    /**
     * Renders the list of tasks into a formatted string.
     * @param tasks list of tasks
     * @return formatted string of tasks
     */
    public static String getTasks(List<Task> tasks) {
        StringBuilder response = new StringBuilder();
        // Get the result from parser and format it for display
        for (int i = 0; i < tasks.size(); i++) {
            response.append(String.format("%d. %s\n", i + 1, tasks.get(i)));
        }
        return response.toString();
    }
}
