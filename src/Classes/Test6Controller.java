package Classes;

/* IMPORTS */
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class Test6Controller implements Test_Interface {

    /* FIELDS */
    @FXML private TextField text;
    @FXML private Label errorMessage;


    /* METHODS */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        errorMessage.setText(" ");
        clearTextFields();
    }

    public void moveForward(ActionEvent event) {

        if(checkAnswer()) {
            try {

                Parent nextLayout = FXMLLoader.load(getClass().getResource("/UI/Finished.fxml"));
                Scene toSetUp = new Scene(nextLayout);
                Stage window = (Stage) (((Node) event.getSource()).getScene()).getWindow();
                window.setScene(toSetUp);
                window.show();

            } catch (Exception e) {

                new AlertBox("Error loading the next test");
                e.printStackTrace();

            }

        } else {
            errorMessage.setText("Incorrect! Please try again");
            clearTextFields();
        }
    }

    public void moveBack(ActionEvent event) {
        try {

            Parent prevLayout = FXMLLoader.load(getClass().getResource("/UI/Test5.fxml"));
            Scene toSetUp = new Scene(prevLayout);
            Stage window = (Stage) (((Node) event.getSource()).getScene()).getWindow();
            window.setScene(toSetUp);
            window.show();

        } catch(Exception e) {

            new AlertBox("Error loading the third test exercise");
            e.printStackTrace();

        }

    }

    public boolean checkAnswer() {
        errorMessage.setText(" ");
        return(text.getCharacters().toString().equalsIgnoreCase("maxheaprestore")); //TODO normalize and check strings
    }

    private void clearTextFields() {
        text.clear();
    }
}
