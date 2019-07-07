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

public class Test5Controller implements Initializable {
    /* FIELDS */
    @FXML private TextField root; //radice
    @FXML private TextField leftSon; //figlio sinistro (parte SINISTRA dalla radice)
    @FXML private TextField rightSon; //figlio destro (parte DESTRA dalla radice)
    @FXML private TextField leftLeftNephew; //nipote sinistro del figlio sinistro (parte SINISTRA dalla radice)
    @FXML private TextField leftRightNephew; //nipote destro del figlio sinistro (parte SINISTRA dalla radice)
    @FXML private TextField rightLeftNephew; //nipote sinistro del figlio destro (parte DESTRA dalla radice)
    @FXML private TextField rightRightNephew; //nipote destro del figlio destro (parte DESTRA dalla radice)
    @FXML private TextField leftOneGreat_grandson; //figlio sinistro del nipote sinistro (parte SINISTRA dalla radice)
    @FXML private TextField leftTwoGreat_grandson; //figlio destro del nipote sinistro (parte SINISTRA dalla radice)
    @FXML private TextField leftThreeGreat_grandson; //figlio sinistro del nipote sinistro (parte SINISTRA dalla radice)
    @FXML private TextField leftFourGreat_grandson; //figlio destro del nipote sinistro (parte SINISTRA dalla radice)
    @FXML private TextField rightOneGreat_grandson; //figlio sinistro del nipote sinistro (parte DESTRA dalla radice)
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

                Parent nextLayout = FXMLLoader.load(getClass().getResource("../UI/TestDispatcher.fxml"));
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
            Parent prevLayout = FXMLLoader.load(getClass().getResource("../UI/Test4.fxml"));
            Scene toSetUp = new Scene(prevLayout);
            Stage window = (Stage) (((Node) event.getSource()).getScene()).getWindow();
            window.setScene(toSetUp);
            window.show();
        } catch(Exception e) {
            new AlertBox("Error loading the third test exercise");
        }
    }

    private boolean checkAnswer() {

        errorMessage.setText(" ");
        return(
                //Check all the possible correct inputs
                (root.getCharacters().toString().equals("45")) &&
                (leftSon.getCharacters().toString().equals("34")) &&
                (leftLeftNephew.getCharacters().toString().equals("30")) &&
                (leftRightNephew.getCharacters().toString().equals("25")) &&
                (leftOneGreat_grandson.getCharacters().toString().equals("14")) &&
                (leftTwoGreat_grandson.getCharacters().toString().equals("21")) &&
                (leftThreeGreat_grandson.getCharacters().toString().equals("15")) &&
                (leftFourGreat_grandson.getCharacters().toString().equals("16")) &&
                (rightSon.getCharacters().toString().equals("28")) &&
                (rightLeftNephew.getCharacters().toString().equals("22")) &&
                (rightRightNephew.getCharacters().toString().equals("12")) &&
                (rightOneGreat_grandson.getCharacters().toString().equals("20"))
        );

    }

    private void clearTextFields() {
        root.clear();
        leftSon.clear();
        rightSon.clear();
        leftLeftNephew.clear();
        rightRightNephew.clear();
        leftOneGreat_grandson.clear();
        leftTwoGreat_grandson.clear();
        leftThreeGreat_grandson.clear();
        leftFourGreat_grandson.clear();
        rightOneGreat_grandson.clear();
        leftRightNephew.clear();
        rightLeftNephew.clear();
    }
}