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

public class TestLayout2Controller implements Initializable {

    /* FIELDS */
    @FXML private TextField root;
    @FXML private TextField leftSon;
    @FXML private TextField rightSon;
    @FXML private TextField leftNephew;
    @FXML private TextField rightNephew;
    @FXML private Label errorMessage;

    /* METHODS */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        errorMessage.setText(" ");
        clearTextFields();
    }

    public void moveForward(ActionEvent event) {
        if(checkAnswer()) {
            try{
                Parent nextLayout = FXMLLoader.load(getClass().getResource("../UI/TestLayout3.fxml"));
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
            Parent prevLayout = FXMLLoader.load(getClass().getResource("../UI/TestLayout1.fxml"));
            Scene toSetUp = new Scene(prevLayout);
            Stage window = (Stage) (((Node) event.getSource()).getScene()).getWindow();
            window.setScene(toSetUp);
            window.show();
        } catch(Exception e) {
            new AlertBox("Error loading the third test exercise");
            e.printStackTrace();
        }
    }

    private boolean checkAnswer() {
        errorMessage.setText(" ");
        return(
                //Check all the possible correct inputs
                (root.getCharacters().toString().equals("1")) &&
                (leftSon.getCharacters().toString().equals("2") || leftSon.getCharacters().toString().equals("5")) &&
                (rightSon.getCharacters().toString().equals("2") || rightSon.getCharacters().toString().equals("5")) &&
                (leftNephew.getCharacters().toString().equals("7") || leftNephew.getCharacters().toString().equals("10")) &&
                (rightNephew.getCharacters().toString().equals("7") || rightNephew.getCharacters().toString().equals("10")) &&
                //This last two checks that the user has not written the same value in the same level node
                (! rightNephew.getCharacters().toString().equals(leftNephew.getCharacters().toString())) &&
                (! rightSon.getCharacters().toString().equals(leftSon.getCharacters().toString()))
        );
    }

    private void clearTextFields() {
        root.clear();
        leftSon.clear();
        rightSon.clear();
        leftNephew.clear();
        rightNephew.clear();
    }
}
