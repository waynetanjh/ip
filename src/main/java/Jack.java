import javax.swing.plaf.metal.MetalFileChooserUI;
import java.util.Objects;
import java.util.Scanner;

public class Jack {
    private static void echo(String text) {
        String line = "-----------------------------------------";
        System.out.println("\t" + line);
        System.out.println("\t" + text);
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

        String userInput = scanner.nextLine();
        while (!userInput.equals("bye")) {
            echo(userInput);
            userInput = scanner.nextLine();
        }
        echo("Bye. Hope to see you again soon!");

    }
}
