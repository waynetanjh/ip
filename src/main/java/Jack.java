import javax.swing.plaf.metal.MetalFileChooserUI;
import java.util.Objects;
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = "Jack";
        System.out.println(LINE);
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        System.out.println(LINE);

        Task[] tasks = new Task[MAX];
        int numberOfTasks = 0;

        String userInput = scanner.nextLine();
        while (true) {
            if (userInput.equals("list") && numberOfTasks != 0) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < numberOfTasks; i++) {
                    sb.append("\t").append(i + 1).append(". ").append(tasks[i].toString());
                    if (i < numberOfTasks - 1) {
                        sb.append("\n");
                    }
                }
                echo(sb.toString());
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
            } else {
                echo("\t" + userInput);
                tasks[numberOfTasks] = new Task(userInput);
                numberOfTasks++;
            }
            userInput = scanner.nextLine();
        }
        scanner.close();
    }
}
