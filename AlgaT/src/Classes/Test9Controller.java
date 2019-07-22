package Classes;

/* IMPORTS */
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class Test9Controller implements Initializable {

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

                Parent nextLayout = FXMLLoader.load(getClass().getResource("/UI/Test10.fxml"));
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

            Parent prevLayout = FXMLLoader.load(getClass().getResource("/UI/Test8.fxml"));
            Scene toSetUp = new Scene(prevLayout);
            Stage window = (Stage) (((Node) event.getSource()).getScene()).getWindow();
            window.setScene(toSetUp);
            window.show();

        } catch(Exception e) {

            new AlertBox("Error loading the third test exercise");
            e.printStackTrace();

        }

    }

    //Check all the possible correct inputs
    private boolean checkAnswer() {
        errorMessage.setText(" ");
        return(
                (text.getCharacters().toString().equals("maxHeapRestore")) ||
                (text.getCharacters().toString().equals("MAXHEAPRESTORE")) ||
                (text.getCharacters().toString().equals("maxheaprestore")) ||
                (text.getCharacters().toString().equals("MaxHeapRestore"))
        );
    }

    private void clearTextFields() {
        text.clear();
    }
}
