package jack;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Launches the JavaFX UI for Jack.
 * Loads the main window FXML, injects the {@link Jack} backend into the controller,
 * and shows the primary stage.
 */
public class Main extends Application {
    private static final String DEFAULT_FILE_PATH = "jack";
    private Jack jack = new Jack();

    /**
     * Creates an application that uses the given storage profile or file path.
     * Note: JavaFX does not use this constructor when launching via {@code Application.launch}.
     *
     * @param filePath the storage profile/file path to use for this session
     */
    public Main(String filePath) {
        // initialize using filePath if applicable
    }

    /**
     * Creates an application that uses the default storage profile.
     */
    public Main() {
        this(DEFAULT_FILE_PATH);
    }

    /**
     * Starts the JavaFX application: loads {@code /view/MainWindow.fxml},
     * sets the controller's {@code Jack} instance, and shows the primary stage.
     *
     * @param stage the primary stage for this application
     */
    @Override
    public void start(Stage stage) {
        try {
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Jack");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setJack(jack);
            fxmlLoader.<MainWindow>getController().showGreeting();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
