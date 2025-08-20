import java.util.Scanner;

public class Jack {
    private static final int MAX = 100;
    private static final String LINE = "-----------------------------------------";;

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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        printWelcomeMessage();

        Task[] tasks = new Task[MAX];
        int numberOfTasks = 0;

        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("")) {
                echo("\tEnter a valid task\n\t");
                continue;
            }

            if (userInput.equals("list")) {
                if (numberOfTasks == 0) {
                    echo("\tHere are the tasks in your list:\n\t  (no tasks yet)");
                } else {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < numberOfTasks; i++) {
                        sb.append("\t").append(i + 1).append(". ").append(tasks[i].toString());
                        if (i < numberOfTasks - 1) {
                            sb.append("\n");
                        }
                    }
                    echo(sb.toString());
                }

            } else if (userInput.equals("bye")) {
                echo("\t" + "Bye. Hope to see you again soon!");
                break;
            } else if (userInput.startsWith("mark")) {
                int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
                tasks[index].completed();
                markCompleted("\t" + tasks[index].toString());
            } else if (userInput.startsWith("unmark")) {
                int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
                tasks[index].unmark();
                unmarkCompleted("\t" + tasks[index].toString());
            } else if (userInput.startsWith("todo")) {
                String desc = userInput.substring(5).trim(); // everything after "todo "
                tasks[numberOfTasks] = new Todo(desc);
                numberOfTasks++;

                echo("\tGot it. I've added this task:\n\t  "
                        + tasks[numberOfTasks - 1]
                        + "\n\tNow you have " + numberOfTasks + " tasks in the list.");
            } else if (userInput.startsWith("deadline")) {
                String[] parts = userInput.substring(9).trim().split("/by", 2);
                String desc = parts[0].trim();
                String by = parts[1].trim();
                tasks[numberOfTasks] = new Deadline(desc, by);
                numberOfTasks++;

                echo("\tGot it. I've added this task:\n\t  "
                        + tasks[numberOfTasks - 1]
                        + "\n\tNow you have " + numberOfTasks + " tasks in the list.");

            } else if (userInput.startsWith("event")) {
                // expected format: event <description> /from <start> /to <end>
                String[] parts = userInput.substring(6).trim().split("/from|/to", 3);
                String desc = parts[0].trim();
                String from = parts[1].trim();
                String to = parts[2].trim();
                tasks[numberOfTasks] = new Event(desc, from, to);
                numberOfTasks++;

                echo("\tGot it. I've added this task:\n\t  "
                        + tasks[numberOfTasks - 1]
                        + "\n\tNow you have " + numberOfTasks + " tasks in the list.");

            } else {
                echo("\t" + userInput);
                tasks[numberOfTasks] = new Task(userInput);
                numberOfTasks++;
            }
        }
        scanner.close();
    }
}
