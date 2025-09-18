package jack;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main window.
 * Wires FXML controls, keeps the scroll pinned to the latest message,
 * and bridges user input to Jack to produce replies.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Jack jack;
    private Parser parser;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image jackImage = new Image(this.getClass().getResourceAsStream("/images/JackSparrow.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Jack instance */
    public void setJack(Jack j) {
        this.jack = j;
        this.parser = new Parser();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response;
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(input, userImage)
        );

        try {
            response = jack.getResponse(input);
        } catch (Exception e) {
            response = e.getMessage();

        }
        dialogContainer.getChildren().add(
                DialogBox.getJackDialog(response, jackImage)
        );
        userInput.clear();
    }
}
