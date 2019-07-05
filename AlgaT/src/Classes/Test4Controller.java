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
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class Test4Controller implements Initializable {

    /* FIELDS */
    @FXML private RadioButton TRUE; //prima risposta giusta
    @FXML private RadioButton true1;
    @FXML private RadioButton true2;
    @FXML private RadioButton true3;
    @FXML private RadioButton FALSE; //seconda risposta giusta
    @FXML private RadioButton false1;
    @FXML private RadioButton false2;
    @FXML private RadioButton false3;
    @FXML private Label errorMessage;

    /* METHODS */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        errorMessage.setText(" ");
        setToggleGroup();
    }

    private void setToggleGroup() {
        ToggleGroup toAdd1 = new ToggleGroup();
        false1.setToggleGroup(toAdd1);
        TRUE.setToggleGroup(toAdd1);
        false2.setToggleGroup(toAdd1);
        false3.setToggleGroup(toAdd1);
    }

    public void moveForward(ActionEvent event) {
        if(checkAnswer()) {
            try{
                Parent nextLayout = FXMLLoader.load(getClass().getResource("../UI/Test5.fxml"));
                Scene toSetUp = new Scene(nextLayout);
                Stage window = (Stage) (((Node) event.getSource()).getScene()).getWindow();
                window.setScene(toSetUp);
                window.show();
            } catch (Exception e) { //QUI HO LEVATO IL MESSAGGIO DI ERRORE PER FARLO PIU' FIGO, L'HO MESSO IN checkAnswer()
                new AlertBox("Error loading the next test");
                e.printStackTrace();
            }
        }
    }

    public void moveBack(ActionEvent event) {
        try {
            Parent prevLayout = FXMLLoader.load(getClass().getResource("../UI/Test3.fxml"));
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

        if(TRUE.isSelected() && !FALSE.isSelected())
            errorMessage.setText("La prima risposta è sbagliata, riprova!");
        else if(!TRUE.isSelected() && FALSE.isSelected())
            errorMessage.setText("La seconda risposta è sbagliata, riprova!");
        else if(!TRUE.isSelected() && !FALSE.isSelected())
            errorMessage.setText("Incorrect! Please try again");

        return(TRUE.isSelected() && FALSE.isSelected());
    }
}

