import java.util.ArrayList;
import java.util.Scanner;

public class Jack {
    private static final String LINE = "-----------------------------------------";

    private static void echo(String text) {
        System.out.println("\t" + LINE);
        System.out.println(text);
        System.out.println("\t" + LINE);
    }

    private static void markCompleted(String text) {
        System.out.println("\t" + LINE);
        System.out.println("\t" + "Nice! I've marked this task as done:");
        System.out.println(" " + text);
        System.out.println("\t" + LINE);
    }

    private static void unmarkCompleted(String text) {
        System.out.println("\t" + LINE);
        System.out.println("\t" + "OK, I've marked this task as not done yet:");
        System.out.println(" " + text);
        System.out.println("\t" + LINE);
    }

    private static void printWelcomeMessage() {
        String name = "Jack";
        System.out.println(LINE);
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    private static void handleDelete(ArrayList<Task> tasks, int index) {
        if (tasks.isEmpty()) {
            throw new IllegalArgumentException("No tasks to delete");
        }
        Task removed = tasks.remove(index);

        echo("\tNoted. I've removed this task:\n\t  "
                + removed
                + "\n\tNow you have " + tasks.size() + " tasks in the list.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        printWelcomeMessage();

        ArrayList<Task> tasks = new ArrayList<>();

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

            switch (cmd) {
                case "list": {
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
                    markCompleted("\t" + tasks.get(index).toString());
                    break;
                }
                case "unmark": {
                    int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    tasks.get(index).unmark();
                    unmarkCompleted("\t" + tasks.get(index).toString());
                    break;
                }
                case "todo": {
                    // Error handling for empty description of todo
                    if (userInput.length() == 4) {
                        echo("\tOOPS!!! The description of a todo cannot be empty");
                        continue;
                    }
                    String desc = userInput.substring(5).trim(); // everything after "todo"
                    tasks.add(new Todo(desc));

                    echo("\tGot it. I've added this task:\n\t  "
                            + tasks.get(tasks.size() - 1)
                            + "\n\tNow you have " + tasks.size() + " tasks in the list.");
                    break;
                }
                case "deadline": {
                    String[] parts = argument.split("/by", 2);
                    String desc = parts[0].trim();
                    String by = parts[1].trim();
                    tasks.add(new Deadline(desc, by));
                    int numberOfTasks = tasks.size();

                    echo("\tGot it. I've added this task:\n\t  "
                            + tasks.get(numberOfTasks - 1)
                            + "\n\tNow you have " + numberOfTasks + " tasks in the list.");
                    break;
                }
                case ("event"): {
                    String[] parts = argument.split("/from|/to", 3);
                    String desc = parts[0].trim();
                    String from = parts[1].trim();
                    String to = parts[2].trim();
                    tasks.add(new Event(desc, from, to));
                    int numberOfTasks = tasks.size();

                    echo("\tGot it. I've added this task:\n\t  "
                            + tasks.get(numberOfTasks - 1)
                            + "\n\tNow you have " + numberOfTasks + " tasks in the list.");
                    break;
                }
                case "delete": {
                    int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    handleDelete(tasks, index);
                    break;
                }
                default: {

                    tasks.add(new Task(userInput));
                    echo("\t" + userInput);
                    break;
                }
            }
        }
    }
}