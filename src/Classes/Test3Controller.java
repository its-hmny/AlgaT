package Classes;

/* IMPORTS */
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class Test3Controller implements Test_Interface {

    /* FIELDS */
    @FXML private RadioButton true1;
    @FXML private RadioButton true2;
    @FXML private RadioButton true3;
    @FXML private RadioButton FALSE;
    @FXML private Label errorMessage;

    /* METHODS */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        errorMessage.setText(" ");

        //Link the toggles group
        ToggleGroup t = new ToggleGroup();
        true1.setToggleGroup(t);
        true2.setToggleGroup(t);
        FALSE.setToggleGroup(t);
        true3.setToggleGroup(t);

    }

    public void moveForward(ActionEvent event) {
        if(checkAnswer()) {

            try {

                Parent nextLayout = FXMLLoader.load(getClass().getResource("/UI/Test4.fxml"));
                Scene toSetUp = new Scene(nextLayout);
                Stage window = (Stage) (((Node) event.getSource()).getScene()).getWindow();
                window.setScene(toSetUp);
                window.show();

            } catch (Exception e) {
                new AlertBox("Error loading the next test");
                e.printStackTrace();
            }

        } else errorMessage.setText("Incorrect! Please try again");

    }

    public void moveBack(ActionEvent event) {

        try {

            Parent prevLayout = FXMLLoader.load(getClass().getResource("/UI/Test2.fxml"));
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
        return(FALSE.isSelected());
    }
}

