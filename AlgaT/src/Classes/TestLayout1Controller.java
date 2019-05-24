package Classes;

/* IMPORTS */
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class TestLayout1Controller implements Initializable {

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
        text.setText("In base all'immagine data, determinare come è memorizzato l'albero minHeap nel vettore");
        wrongSolution.setText("");
        //IMPORTANTE
        /* Io pensavo di mettere come consegna una cosa del tipo: diamo uan foto di un' albero
        e il giocatore deve riordinare i nodi in base a come sono memorizzati nell'array. Oppure si può
        dare in input un albero non proprio min heap e chiedere di rimettere in ordine i valori nell'array
        come facendo un MinHeapRestore */
    }

    //This method setup all five the ChoiceBox
    private void setupChoiceBoxes() {
        Integer toChoose[] = {1,3,5,6,8,9};
        for (Integer i : toChoose) {
            box1.getItems().add(i);
            box2.getItems().add(i);
            box3.getItems().add(i);
            box4.getItems().add(i);
            box5.getItems().add(i);
            box6.getItems().add(i);
        }
    }

    //This method checks if the answer is correct
    private boolean checkAnswer() {
        //TODO valutare i valori nei ChoiceBox e ritornare true o false
        return(
                (box1.getValue() == 1) &&
                (box2.getValue() == 3) &&
                (box3.getValue() == 6) &&
                (box4.getValue() == 5) &&
                (box5.getValue() == 9) &&
                (box6.getValue() == 8)
        );
    }

    public void moveBack(ActionEvent event) {
        //Load the welcome screen
        try {
            Parent prevLayout = FXMLLoader.load(getClass().getResource("../UI/Welcome.fxml"));
            Scene toSetUp = new Scene(prevLayout);
            Stage window = (Stage) (((Node) event.getSource()).getScene()).getWindow();
            window.setScene(toSetUp);
            window.show();
        } catch (Exception e) {
            new AlertBox("Error loading the Welcome screen");
            e.printStackTrace();
        }
    }

    public void moveForward(ActionEvent event) {
        if(checkAnswer()) {
          //Load the TextLayout2.fxml
            try {
                Parent nextLayout = FXMLLoader.load(getClass().getResource("../UI/TestLayout2.fxml"));
                Scene toSetUp = new Scene(nextLayout);
                Stage window = (Stage) (((Node) event.getSource()).getScene()).getWindow();
                window.setScene(toSetUp);
                window.show();
            } catch (Exception e) {
                new AlertBox("Error loading the next test quiz");
                e.printStackTrace();
            }
        } else {
            //Set an error label
            wrongSolution.setText("Incorrect! Please try again");
        }
    }
}
