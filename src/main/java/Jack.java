import javax.swing.plaf.metal.MetalFileChooserUI;
import java.util.Objects;
import java.util.Scanner;

public class Jack {
    private static final int MAX = 100;

    private static void echo(String text) {
        String line = "-----------------------------------------";
        System.out.println("\t" + line);
        System.out.println(text);
        System.out.println("\t" + line);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = "Jack";
        String line = "-----------------------------------------";
        System.out.println(line);
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        System.out.println(line);

        String[] tasks = new String[MAX];
        int size = 0;

        String userInput = scanner.nextLine();
        while (true) {
            if (userInput.equals("list") && size != 0) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < size; i++) {
                    sb.append("\t").append((i + 1)).append(". ").append(tasks[i]);
                    if (i < size - 1 ) {
                        sb.append("\n");
                    }
                }
                echo(sb.toString());
            } else if (userInput.equals("bye")) {
                echo("\t" + "Bye. Hope to see you again soon!");
                break;
            } else {
                echo("\t" + userInput);
                tasks[size] = userInput;
                size++;
            }
            userInput = scanner.nextLine();
        }
    }
}
