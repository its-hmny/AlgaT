package Classes;

/* IMPORTS */
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class Test1Controller implements Test_Interface {

    /* FIELDS */
    @FXML private Label text;
    @FXML private Label wrongSolution;
    @FXML private ChoiceBox<Integer> box1;
    @FXML private ChoiceBox<Integer> box2;
    @FXML private ChoiceBox<Integer> box3;
    @FXML private ChoiceBox<Integer> box4;
    @FXML private ChoiceBox<Integer> box5;
    @FXML private ChoiceBox<Integer> box6;

    /* METHODS */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupChoiceBoxes();
        text.setText("Based on the shown picture, determine how the HeapTree has been memorized in the array");
        wrongSolution.setText("");
    }

    //Sets up all five the ChoiceBox
    private void setupChoiceBoxes() {
        Integer toChoose[] = {1, 3, 5, 6, 8, 9};
        for (Integer i : toChoose) {
            box1.getItems().add(i);
            box2.getItems().add(i);
            box3.getItems().add(i);
            box4.getItems().add(i);
            box5.getItems().add(i);
            box6.getItems().add(i);
        }
    }

    //Checks if the answer is correct
    public boolean checkAnswer() {
        return(
                (box1.getValue() == 1) &&
                (box2.getValue() == 3) &&
                (box3.getValue() == 6) &&
                (box4.getValue() == 5) &&
                (box5.getValue() == 9) &&
                (box6.getValue() == 8)
        );
    }

    //Load the Welcome.fxml screen
    public void moveBack(ActionEvent event) {

        try {

            Parent prevLayout = FXMLLoader.load(getClass().getResource("/UI/Welcome.fxml"));
            Scene toSetUp = new Scene(prevLayout);
            Stage window = (Stage) (((Node) event.getSource()).getScene()).getWindow();
            window.setScene(toSetUp);
            window.show();

        } catch (Exception e) {

            new AlertBox("Error loading the Welcome screen");
            e.printStackTrace();

        }
    }

    //Load the next test layout, only if the answer is correct
    public void moveForward(ActionEvent event) {
        if(checkAnswer()) {
            try {

                Parent nextLayout = FXMLLoader.load(getClass().getResource("/UI/Test2.fxml"));
                Scene toSetUp = new Scene(nextLayout);
                Stage window = (Stage) (((Node) event.getSource()).getScene()).getWindow();
                window.setScene(toSetUp);
                window.show();

            } catch (Exception e) {

                new AlertBox("Error loading the next test quiz");
                e.printStackTrace();

            }

        } else {
            wrongSolution.setText("Incorrect! Please try again");
        }
    }
}