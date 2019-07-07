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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class Test7Controller implements Initializable {

    /* FIELDS */
    @FXML private RadioButton FALSE1;
    @FXML private RadioButton TRUE;
    @FXML private RadioButton FALSE2;
    @FXML private RadioButton FALSE3;
    @FXML private Label errorMessage;

    /* METHODS */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        errorMessage.setText(" ");

        ToggleGroup t=new ToggleGroup();
        FALSE1.setToggleGroup(t);
        TRUE.setToggleGroup(t);
        FALSE2.setToggleGroup(t);
        FALSE3.setToggleGroup(t);
    }

    public void moveForward(ActionEvent event) {

        if(checkAnswer()) {
            try {

                Parent nextLayout = FXMLLoader.load(getClass().getResource("../UI/Test8.fxml"));
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

            Parent prevLayout = FXMLLoader.load(getClass().getResource("../UI/Test6.fxml"));
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
        return(TRUE.isSelected());
    }
}

